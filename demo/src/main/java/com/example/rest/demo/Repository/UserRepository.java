package com.example.rest.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rest.demo.Model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
