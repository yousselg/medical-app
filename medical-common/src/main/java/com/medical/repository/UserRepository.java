package com.medical.repository;

import com.medical.model.actors.AbstractUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AbstractUser, Long> {

    Optional<AbstractUser> findByEmail(String email);

    Boolean existsByEmail(String email);

}
