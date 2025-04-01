package com.example.secondAPI.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.secondAPI.Model.User;

public interface UserRepository extends JpaRepository<User,Long>{

    @SuppressWarnings("unchecked")
    User save(User user);

    Optional<User> findByEmail(String email);
}
