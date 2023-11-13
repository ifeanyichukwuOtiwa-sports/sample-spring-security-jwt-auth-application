package com.gxstar.ssjwtauth.repository;

import com.gxstar.ssjwtauth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    @Query(
            value = """
            SELECT u.*
            FROM user u
            WHERE u.username = :username
            """,
            nativeQuery = true
    )
    Optional<User> findByUsername(String username);
}
