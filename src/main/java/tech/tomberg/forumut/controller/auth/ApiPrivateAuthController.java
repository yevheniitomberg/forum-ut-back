package tech.tomberg.forumut.controller.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.tomberg.forumut.dto.auth.TokenUserDto;
import tech.tomberg.forumut.dto.response.CustomResponse;
import tech.tomberg.forumut.entity.auth.User;
import tech.tomberg.forumut.jwt.JwtUserDetailsService;
import tech.tomberg.forumut.jwt.TokenManager;

@Controller
@RequestMapping("api/private")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ApiPrivateAuthController {
    private final JwtUserDetailsService userDetailsService;
    private final TokenManager tokenManager;
    @PostMapping("/prolong")
    public ResponseEntity<CustomResponse> prolongSession(@RequestBody TokenUserDto dto) {
        return ResponseEntity.ok(new CustomResponse("Session successfully prolonged!", "token",  tokenManager.generateJwtToken(userDetailsService.loadUserByUsername(dto.getUtEmail()))));
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
