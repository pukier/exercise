package library.exercise.registration;

import library.exercise.User.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserRegistrationController {

    private final UserRegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUserAccount(@RequestBody UserDto userDto) {
        return registrationService.register(userDto);
    }
}
