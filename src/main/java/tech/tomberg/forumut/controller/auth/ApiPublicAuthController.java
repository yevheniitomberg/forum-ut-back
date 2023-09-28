package tech.tomberg.forumut.controller.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.tomberg.forumut.dto.auth.LoginDto;
import tech.tomberg.forumut.dto.response.CustomResponse;
import tech.tomberg.forumut.jwt.JwtUserDetailsService;
import tech.tomberg.forumut.jwt.TokenManager;

@Controller
@RequestMapping("api/public")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
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
}
