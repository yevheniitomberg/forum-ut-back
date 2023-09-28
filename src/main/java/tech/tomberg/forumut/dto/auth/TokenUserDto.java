package tech.tomberg.forumut.dto.auth;

import lombok.Data;

@Data
public class TokenUserDto {
    private String token;
    private String utEmail;
}
