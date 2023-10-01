package tech.tomberg.forumut.controller.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.tomberg.forumut.dto.auth.TokenUserDto;
import tech.tomberg.forumut.dto.response.CustomResponse;
import tech.tomberg.forumut.entity.forum.Post;
import tech.tomberg.forumut.jwt.JwtUserDetailsService;
import tech.tomberg.forumut.jwt.TokenManager;
import tech.tomberg.forumut.repository.entity.PostRepository;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("api/private")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ApiPrivateAuthController {
    private final JwtUserDetailsService userDetailsService;
    private final TokenManager tokenManager;
    private final PostRepository postRepository;
    @PostMapping("/prolong")
    public ResponseEntity<CustomResponse> prolongSession(@RequestBody TokenUserDto dto) {
        return ResponseEntity.ok(new CustomResponse("Session successfully prolonged!", "token",  tokenManager.generateJwtToken(userDetailsService.loadUserByUsername(dto.getUtEmail()))));
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<CustomResponse> prolongSession(@PathVariable("id") Long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            return ResponseEntity.ok(new CustomResponse("Post received!", "post", post.get()));
        }
        return ResponseEntity.ok(CustomResponse.builder().message("Post was not found!").data(new HashMap<>()).error(true).build());
    }
}
