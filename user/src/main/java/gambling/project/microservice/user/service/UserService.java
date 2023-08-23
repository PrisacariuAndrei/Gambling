package gambling.project.microservice.user.service;

import gambling.project.microservice.user.dto.UserWalletDTO;
import gambling.project.microservice.user.dto.request.CreateUserDTO;
import gambling.project.microservice.user.dto.response.UserDTO;
import gambling.project.microservice.user.entity.User;

import java.util.List;

public interface UserService {
    public UserDTO saveUser(CreateUserDTO createUserDTO);
    public UserDTO getUserDTOById(Long id);
    public List<UserDTO> getAllUsers();
    public void deleteUserById(Long id);
    public User updateUser(Long id, User user);
    public UserWalletDTO saveUserWithWallet(User user);
    public UserWalletDTO getUserWithWallet(Long id);
}
