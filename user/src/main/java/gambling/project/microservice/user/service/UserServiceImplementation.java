package gambling.project.microservice.user.service;

import gambling.project.microservice.user.ApiException;
import gambling.project.microservice.user.ErrorCode;
import gambling.project.microservice.user.dto.UserWalletDTO;
import gambling.project.microservice.user.dto.Wallet;
import gambling.project.microservice.user.dto.request.CreateUserDTO;
import gambling.project.microservice.user.dto.response.UserDTO;
import gambling.project.microservice.user.entity.User;
import gambling.project.microservice.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private RestTemplate template;
    private final ModelMapper modelMapper;

    public UserServiceImplementation(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public User toUser(CreateUserDTO createUserDTO) {
        return modelMapper.map(createUserDTO, User.class);
    }
    public UserDTO toDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO saveUser(CreateUserDTO createUserDTO) {
        User savedUser;
        savedUser = toUser(createUserDTO);
        savedUser = userRepository.save(savedUser);

        return  toDTO(savedUser);
    }

    @Override
    public UserDTO getUserDTOById(Long id) {
        User userById = userRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));
        return toDTO(userById);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUserById(Long id) {
        User userDTO = userRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));

        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(Long id, User user) {
        User userById = userRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));
        userById.setUserName(user.getUserName());
        userById.setPassword(user.getPassword());

        User updatedUser = userRepository.save(userById);
        return updatedUser;
    }

    @Override
    public UserWalletDTO saveUserWithWallet(User user) {
        UserWalletDTO userWalletDTO = new UserWalletDTO();
        User savedUser;

        savedUser = userRepository.save(user);
        userWalletDTO.setUser(savedUser);

        Wallet savedWallet = new Wallet();
        savedWallet.setCustomerName(savedUser.getUserName());
        savedWallet.setId(savedUser.getId());

        ResponseEntity<Wallet> wallet = template.postForEntity("http://localhost:8081/wallet/", savedWallet, Wallet.class);
        userWalletDTO.setWallet(wallet.getBody());

        return userWalletDTO;
    }

    @Override
    public UserWalletDTO getUserWithWallet(Long id) {
        UserWalletDTO userWithWallet = new UserWalletDTO();

        User user = userRepository.findById(id).orElse(null);
        userWithWallet.setUser(user);

        ResponseEntity<Wallet> wallet = template.getForEntity("http://localhost:8081/wallet/" + user.getId(), Wallet.class);
        userWithWallet.setWallet(wallet.getBody());

        return userWithWallet;
    }
}
