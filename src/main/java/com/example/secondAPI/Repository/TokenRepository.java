package com.example.secondAPI.Repository;

import java.sql.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.secondAPI.Model.Token;

public interface TokenRepository extends JpaRepository<Token,Long>{

    Token save(Token token);

    @Query("Select t from Token t where t.value = :tokenValue and t.expiryAt > :nowDate")
    Optional<Token> CheckTokenHasNotExpiredOrDeleted(@Param("tokenValue") String tokenValue,
                                                    @Param("nowDate") Date nowDate);

    Optional<Token> findByValue(String value);
}
