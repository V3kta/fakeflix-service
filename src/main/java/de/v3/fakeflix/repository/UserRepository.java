package de.v3.fakeflix.repository;

import de.v3.fakeflix.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Boolean existsByUsernameOrEmailAndPassword(String username, String email, String password);
    Boolean existsByUsername(String username);

    User findByUsernameOrEmailAndPassword(String username, String email, String password);
}
