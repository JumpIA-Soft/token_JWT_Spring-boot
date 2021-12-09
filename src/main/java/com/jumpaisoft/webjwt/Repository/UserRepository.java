package com.jumpaisoft.webjwt.Repository;

import java.util.Optional;

import com.jumpaisoft.webjwt.entities.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<user, Integer> {

    public Optional<user> findByNameUser(String user_name);

}
