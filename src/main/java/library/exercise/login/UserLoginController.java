package library.exercise.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLoginController {

    @PostMapping("/login")
    public String login(@RequestBody UserLoginC c) {
        return "greetings!";
    }
}
