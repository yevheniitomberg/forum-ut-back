package tech.tomberg.forumut.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.tomberg.forumut.entity.auth.User;
import tech.tomberg.forumut.repository.auth.UserRepository;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUtEmail(username).orElse(null);
        if (user != null && user.getUsername().equals(username)) {
            return user;
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}