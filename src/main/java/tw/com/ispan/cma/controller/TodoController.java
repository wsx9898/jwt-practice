package tw.com.ispan.cma.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.com.ispan.cma.utils.JwtToken;

import javax.security.auth.message.AuthException;
import java.util.HashMap;

@RestController
@CrossOrigin
public class TodoController {

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody HashMap<String, String> user) {
        System.out.println("JWT controller called");
        JwtToken jwtToken = new JwtToken();
        String token = jwtToken.generateToken(user); // 取得token

        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @PostMapping("/hello")
    public ResponseEntity hello(@RequestHeader("Authorization") String au) {
        String token = au.substring(6);
        JwtToken jwtToken = new JwtToken();
        try {
            jwtToken.validateToken(token);
        } catch (AuthException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body("Hello CaiLi");
    }
}