package gambling.project.microservice.user.dto.response;

import gambling.project.microservice.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String userName;
    private String password;

    public UserDTO(User user) {
        this.id = user.getId();
        this.password = user.getPassword();
        this.userName = user.getUserName();
    }
}
