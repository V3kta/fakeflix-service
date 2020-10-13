package de.v3.fakeflix.repository;

import de.v3.fakeflix.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByUsernameAndPassword(String username, String password);
    Boolean existsByEmailAndPassword(String email, String password);
    Boolean existsByUsernameOrEmail(String username, String email);

    Optional<User> findByUsernameAndPassword(String username, String password);
    Optional<User> findByEmailAndPassword(String email, String password);
}
