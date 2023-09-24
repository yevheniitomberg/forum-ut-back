package tech.tomberg.forumut.entity.auth;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    @Enumerated(value = EnumType.STRING)
    private Authority authority;

    @Override
    public String getAuthority() {
        return String.format(authority.name());
    }
}
