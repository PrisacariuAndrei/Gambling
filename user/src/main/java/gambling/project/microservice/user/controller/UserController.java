package gambling.project.microservice.user.controller;

import gambling.project.microservice.user.dto.UserWalletDTO;
import gambling.project.microservice.user.dto.request.CreateUserDTO;
import gambling.project.microservice.user.dto.response.UserDTO;
import gambling.project.microservice.user.entity.User;
import gambling.project.microservice.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    ResponseEntity<UserDTO> saveUser(@RequestBody CreateUserDTO user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/user-wallet/")
    ResponseEntity<UserWalletDTO> saveUserWithWallet(@RequestBody User user) {
        return new ResponseEntity<>(userService.saveUserWithWallet(user), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    ResponseEntity<UserDTO> getUserById(@PathVariable("userId") final Long userId) {
        return ResponseEntity.ok(userService.getUserDTOById(userId));
    }

    @GetMapping("/")
    ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/userWithWallet/{userId}")
    private UserWalletDTO getUserWithWallet(@PathVariable("userId") Long userId){
        return userService.getUserWithWallet(userId);
    }

    @PatchMapping("/{userId}")
    ResponseEntity<User> updateUser(@PathVariable("userId") Long userId, @RequestBody User updatedUser) {
        return ResponseEntity.ok(userService.updateUser(userId, updatedUser));
    }

    @DeleteMapping("/{userId}")
    public void deleteById(@PathVariable("userId") Long userId) {
        userService.deleteUserById(userId);
    }
}
