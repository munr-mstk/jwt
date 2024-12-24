package com.munirmustakoglu.jwt.repository;


import com.munirmustakoglu.jwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<org.springframework.security.core.userdetails.User> findByUsername(String username);
}
