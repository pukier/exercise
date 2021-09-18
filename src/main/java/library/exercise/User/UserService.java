package library.exercise.User;


import library.exercise.security.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with mail %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return getByEmail(email);
    }

    public User createNewUser (UserDto userDto)
    {
        User newUser = new User(
                userDto.getUserName(),
                userDto.getFirst_name(),
                userDto.getLast_name(),
                encryptPassword(userDto),
                userDto.getEmail()
        );
        return userRepository.save(newUser);
    }
    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    @Transactional
    public UserInfoDto findUserByEmail(final String email) {
        try {
            User user = getByEmail(email);
            return new UserInfoDto(user.getEmail(), user.getFirst_name(), user.getLast_name(), user.getCan_rent(), user.getCreatedOn(), user.getEmail());
        } catch (UsernameNotFoundException e)
        {
            throw new IllegalStateException("User not found!");
        }
    }

    public boolean doesEmailExists(String email)
    {
        return !userRepository.findByEmail(email).isPresent();
    }

    public boolean doesUserNameExist (String userName) {
        return !userRepository.findByUserName(userName).isPresent();
    }

    public boolean validateEmail(String email)
    {
        return EmailValidator.isValid(email);

    }

    public String encryptPassword (UserDto userDto)
    {
        return bCryptPasswordEncoder.encode(userDto.getPassword());
    }
}
