package com.andrew.common.crudboot.crudboot.repository;

import com.andrew.common.crudboot.crudboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
    User findUserByName (String name);
}
