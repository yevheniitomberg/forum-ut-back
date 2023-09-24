package tech.tomberg.forumut.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.tomberg.forumut.entity.auth.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUtEmail(String email);
    boolean existsByUtEmail(String email);
}