package library.exercise.User;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class UserInfoDto {
    private String userName;
    private String first_name;
    private String last_name;
    private Boolean can_rent;
    private LocalDateTime createdOn;
    private String email;
}
