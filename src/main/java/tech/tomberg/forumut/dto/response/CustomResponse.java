package tech.tomberg.forumut.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.tomberg.forumut.exception.InvalidArgsCountException;

import java.util.HashMap;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomResponse {
    private String message;
    private boolean error;
    private HashMap<Object, Object> data;
    public CustomResponse(String message, boolean error) {
        this.message = message;
        this.error = error;
        this.data = new HashMap<>();
    }
    public CustomResponse(String message, Object... args) {
        this.message = message;
        this.data = new HashMap<>();
        if (args.length % 2 != 0) {
            throw new InvalidArgsCountException("The number of arguments must be in pairs");
        }
        for (int i = 0; i < args.length; i+=2) {
            this.data.put(args[i], args[i+1]);
        }
    }
}
