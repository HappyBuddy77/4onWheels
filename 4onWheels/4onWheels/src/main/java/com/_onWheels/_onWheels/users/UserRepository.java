package com._onWheels._onWheels.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    @Query("SELECT u from User u WHERE u.email = ?1")
    User findByEmail(String email);
}
