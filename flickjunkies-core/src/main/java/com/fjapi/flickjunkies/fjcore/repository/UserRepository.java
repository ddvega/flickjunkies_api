package com.fjapi.flickjunkies.fjcore.repository;

import com.fjapi.flickjunkies.fjcore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    User findUserByUsername(String username);
}
