package com.security.repository;

import com.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author heartccace
 * @create 2020-05-21 16:56
 * @Description TODO
 * @Version 1.0
 */
public interface UserRepository extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {
    User findUserByUsername(String userName);
}
