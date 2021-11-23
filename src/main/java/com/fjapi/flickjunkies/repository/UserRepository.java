package com.fjapi.flickjunkies.repository;

import com.fjapi.flickjunkies.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
    User getUserByStringId(String stringId);
}
