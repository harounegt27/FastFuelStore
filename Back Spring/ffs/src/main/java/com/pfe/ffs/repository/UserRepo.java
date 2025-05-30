package com.pfe.ffs.repository;

import com.pfe.ffs.entity.User;
import com.pfe.ffs.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findFirstByEmail(String email);

    User findByRole(UserRole userRole);
}
