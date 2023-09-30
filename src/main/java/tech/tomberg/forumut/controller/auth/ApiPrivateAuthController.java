package tech.tomberg.forumut.controller.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.tomberg.forumut.dto.auth.TokenUserDto;
import tech.tomberg.forumut.dto.response.CustomResponse;
import tech.tomberg.forumut.jwt.JwtUserDetailsService;
import tech.tomberg.forumut.jwt.TokenManager;

@RestController
@RequestMapping("api/private")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ApiPrivateAuthController {
    private final JwtUserDetailsService userDetailsService;
    private final TokenManager tokenManager;
    @PostMapping("/prolong")
    public ResponseEntity<CustomResponse> prolongSession(@RequestBody TokenUserDto dto) {
        return ResponseEntity.ok(new CustomResponse("Session successfully prolonged!", "token",  tokenManager.generateJwtToken(userDetailsService.loadUserByUsername(dto.getUtEmail()))));
    }
}
