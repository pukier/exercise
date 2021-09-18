package library.exercise.User;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public UserInfoDto userInfo(Principal principal) { return userService.findUserByEmail(principal.getName()); }
}
