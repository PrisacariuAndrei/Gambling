package gambling.project.microservice.user.dto;

import gambling.project.microservice.user.entity.User;
import lombok.Data;

@Data
public class UserWalletDTO {
    private User user;
    private Wallet wallet;
}
