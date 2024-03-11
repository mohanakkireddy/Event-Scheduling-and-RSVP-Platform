package com.rsvp.authentication.response;
import org.springframework.http.HttpStatus;


public class SignUpResponse {
    private String username;
    private String message;
    private HttpStatus status;

    public SignUpResponse() {
    }

    public SignUpResponse(String username, String message, HttpStatus status) {
        this.username = username;
        this.message = message;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SignUpResponse{" +
                "username='" + username + '\'' +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
