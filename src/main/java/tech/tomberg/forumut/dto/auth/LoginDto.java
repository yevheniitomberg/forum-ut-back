package tech.tomberg.forumut.dto.auth;

import lombok.Data;

@Data
public class LoginDto {
    private String utEmail;
    private String password;
}
