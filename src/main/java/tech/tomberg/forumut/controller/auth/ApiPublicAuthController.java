package tech.tomberg.forumut.controller.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import tech.tomberg.forumut.dto.auth.LoginDto;
import tech.tomberg.forumut.dto.auth.TokenUserDto;
import tech.tomberg.forumut.dto.response.CustomResponse;
import tech.tomberg.forumut.entity.auth.User;
import tech.tomberg.forumut.jwt.JwtUserDetailsService;
import tech.tomberg.forumut.jwt.TokenManager;

@RestController
@RequestMapping("api/public")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ApiPublicAuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUserDetailsService userDetailsService;
    private final TokenManager tokenManager;
    @PostMapping("/login")
    public ResponseEntity<CustomResponse> createToken(@RequestBody LoginDto request) {
        try {
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUtEmail(), request.getPassword()));
        } catch (DisabledException e) {
            return ResponseEntity.ok(new CustomResponse("User is disabled!", true));
        } catch (BadCredentialsException e) {
            return ResponseEntity.ok(new CustomResponse("Invalid credentials!", true));
        } catch (LockedException e) {
            return ResponseEntity.ok(new CustomResponse("User is locked!", true));
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUtEmail());
        final String jwtToken = tokenManager.generateJwtToken(userDetails);
        return ResponseEntity.ok(new CustomResponse("Successfully logged in!", "token", jwtToken, "user", userDetails));
    }

    @PostMapping("/authenticated")
    public ResponseEntity<CustomResponse> isAuthenticated(@RequestBody TokenUserDto dto) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getUtEmail());
        if (tokenManager.validateJwtToken(dto.getToken(), userDetails)) {
            return ResponseEntity.ok(new CustomResponse("You are authenticated!", "authenticated",  true, "user", userDetails));
        }
        return ResponseEntity.ok(new CustomResponse("You are not authenticated!", "authenticated",  false, "user", new User()));
    }

    @PostMapping("/loadUser")
    public ResponseEntity<CustomResponse> loadUser(@RequestBody TokenUserDto dto) {
        return ResponseEntity.ok(new CustomResponse("User received",  "user",  userDetailsService.loadUserByUsername(dto.getUtEmail())));
    }
}
