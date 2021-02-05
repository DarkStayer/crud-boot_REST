package com.andrew.common.crudboot.crudboot.DAO;

import com.andrew.common.crudboot.crudboot.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
    User findUserByName (String name);
}
