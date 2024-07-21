package com.untouchable.everytime.User.Repository;

import com.untouchable.everytime.User.Entity.Token;
import com.untouchable.everytime.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {


    long deleteByUser(User user);

    long deleteAllByUserId(String userId);

    boolean existsByRefreshToken(String refreshToken);

}
