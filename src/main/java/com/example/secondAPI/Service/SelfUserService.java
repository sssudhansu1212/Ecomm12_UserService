package com.example.secondAPI.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.secondAPI.Exception.InvalidTokenException;
import com.example.secondAPI.Exception.UserAlreadyExsistsException;
import com.example.secondAPI.Exception.UserNameNotFoundException;
import com.example.secondAPI.Model.Role;
import com.example.secondAPI.Model.Token;
import com.example.secondAPI.Model.User;
import com.example.secondAPI.Repository.RoleRepository;
import com.example.secondAPI.Repository.TokenRepository;
import com.example.secondAPI.Repository.UserRepository;


@Service
public class SelfUserService implements UserService{

    private UserRepository userRepository;
    private BCryptPasswordEncoder bcryptEncoder;
    private RoleRepository roleRepository;
    private TokenRepository tokenRepository;

    @Autowired
    public SelfUserService(UserRepository userRepository,BCryptPasswordEncoder bcryptEncoder,
                            RoleRepository roleRepository,TokenRepository tokenRepository){
        this.userRepository = userRepository;
        this.bcryptEncoder = bcryptEncoder;
        this.roleRepository = roleRepository;
        this.tokenRepository = tokenRepository;
    }
    
    public User SignUp(String name,String email,String pwd,List<Role> role)throws UserAlreadyExsistsException{

        List<Role> userRole = new ArrayList<>();
        Role roleSaved;

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent()){
            throw new UserAlreadyExsistsException(" User Already there !! ");
        }

        for(Role r:role){
            Optional<Role> optionalRole = roleRepository.findByName(r.getName());
            roleSaved = optionalRole.isEmpty() ? roleRepository.save(r) : optionalRole.get();
            userRole.add(roleSaved);
        }
        
        User user = new User();
        user.setName(name);
        user.setPassword(bcryptEncoder.encode(pwd));
        user.setRoles(userRole);
        user.setEmail(email);

        return userRepository.save(user);

    }

    @SuppressWarnings("deprecation")
    public Token login(String email,String password)throws UserNameNotFoundException{

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()){
            throw new UserNameNotFoundException(" Please check the user again !! ");
        }

        User saveduser = optionalUser.get();
        bcryptEncoder.matches(password,saveduser.getPassword());

        Token token = new Token();
        token.setUser(saveduser);
        
        token.setValue(RandomStringUtils.randomAlphanumeric(15));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date()); // Set current date
        calendar.add(Calendar.DAY_OF_MONTH, 30); // Add 30 days
        Date expiryDate = calendar.getTime(); // Get the updated date

        // Convert java.util.Date to java.sql.Date
        java.sql.Date sqlExpiryDate = new java.sql.Date(expiryDate.getTime());
        token.setExpiryAt(sqlExpiryDate);

        Token savedToken = tokenRepository.save(token);

        return savedToken;
    }

    public String signOut(String tokenVal)throws InvalidTokenException{

        Optional<Token> optionalToken = tokenRepository.findByValue(tokenVal);
        if(optionalToken.isEmpty()){
            throw new InvalidTokenException(" Given token is invalid ");
        }

        Token savedtoken = optionalToken.get();
        savedtoken.setDeleted(true);
        tokenRepository.save(savedtoken);

        return " Token Deleted ";
    }

    public Boolean validateToken(String tokenVal)throws InvalidTokenException{

        long millis = System.currentTimeMillis();
        java.sql.Date sqlDate = new java.sql.Date(millis);
        Optional<Token> optionalToken = tokenRepository.CheckTokenHasNotExpiredOrDeleted(tokenVal,sqlDate);
        if(optionalToken.isEmpty()){
            throw new InvalidTokenException(" Given token is invalid ");
        }

        return true;
    }

}
