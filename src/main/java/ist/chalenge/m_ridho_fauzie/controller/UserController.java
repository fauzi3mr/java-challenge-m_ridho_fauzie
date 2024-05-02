package ist.chalenge.m_ridho_fauzie.controller;

import ist.chalenge.m_ridho_fauzie.dto.UserDto;
import ist.chalenge.m_ridho_fauzie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserDto userDto){
        return userService.loginUser(userDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDto>> getAllUser(){
        return userService.getAllUser();
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody UserDto userDto){
        return userService.updateUser(userDto);
    }
}
