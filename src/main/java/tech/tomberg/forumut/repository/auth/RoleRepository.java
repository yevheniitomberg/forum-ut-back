package tech.tomberg.forumut.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.tomberg.forumut.entity.auth.Authority;
import tech.tomberg.forumut.entity.auth.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByAuthority(Authority authority);
}