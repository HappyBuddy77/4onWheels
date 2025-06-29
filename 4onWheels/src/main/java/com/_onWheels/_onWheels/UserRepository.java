package com._onWheels._onWheels;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;



public interface UserRepository extends JpaRepository<User, Long> {
boolean existsByEmail(String email);
User findByEmail(String email);
}