package ist.chalenge.m_ridho_fauzie.service;

import ist.chalenge.m_ridho_fauzie.dto.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<String> createUser(UserDto userDto);
    ResponseEntity<String> loginUser(UserDto userDto);
    ResponseEntity<List<UserDto>> getAllUser();
    ResponseEntity<String> updateUser(UserDto userDto);


}
