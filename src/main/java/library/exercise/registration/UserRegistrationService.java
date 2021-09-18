package library.exercise.registration;

import library.exercise.User.User;
import library.exercise.User.UserDto;
import library.exercise.User.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserRegistrationService {

    private final UserService userService;

    @Transactional
    public ResponseEntity<String> register(final UserDto userDto) {
        if (validateNewUser(userDto)) {
            User newUserRegister = userService.createNewUser(userDto);

            return ResponseEntity.status(HttpStatus.OK).body("User register.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is already taken or is invalid.");
    }

    private boolean validateNewUser(UserDto userDto) {
        return userService.doesEmailExists(userDto.getEmail())
                && userService.validateEmail(userDto.getEmail())
                && userService.doesUserNameExist(userDto.getUserName());
    }

}
