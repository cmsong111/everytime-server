package com.untouchable.everytime.User.Repository;

import com.untouchable.everytime.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query("select (count(u) > 0) from User u where u.userId = ?1")
    boolean existsByUserId(String userId);
}


