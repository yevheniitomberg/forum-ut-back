package tech.tomberg.forumut.exception;

public class InvalidArgsCountException extends RuntimeException {
    public InvalidArgsCountException(String message) {
        super(message);
    }
}
