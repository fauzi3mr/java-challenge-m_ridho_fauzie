package ist.chalenge.m_ridho_fauzie.service.impl;

import ist.chalenge.m_ridho_fauzie.dto.UserDto;
import ist.chalenge.m_ridho_fauzie.entity.User;
import ist.chalenge.m_ridho_fauzie.mapper.UserMapper;
import ist.chalenge.m_ridho_fauzie.repository.UserRepository;
import ist.chalenge.m_ridho_fauzie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<String> createUser(UserDto userDto) {
        User user = userRepository.findByUsername(userDto.getUsername());

        if(user == null){
            user = new User();
            user.setUsername(userDto.getUsername());
            user.setPassword(userDto.getPassword());

            userRepository.save(user);

            return new ResponseEntity<>("Sukses", HttpStatusCode.valueOf(201));
        } else {
            return new ResponseEntity<>("Username sudah terpakai", HttpStatusCode.valueOf(409));
        }
    }

    @Override
    public ResponseEntity<String> loginUser(UserDto userDto) {
        if (userDto.getUsername() == null || userDto.getUsername().equalsIgnoreCase("")
                || userDto.getPassword() == null || userDto.getPassword().equalsIgnoreCase("")){
            return new ResponseEntity<>("Username dan / atau password kosong", HttpStatusCode.valueOf(400));
        }

        User user = userRepository.findByUsername(userDto.getUsername());

        if(user == null || !user.getPassword().equalsIgnoreCase(userDto.getPassword())){
            return new ResponseEntity<>("Username / password tidak cocok", HttpStatusCode.valueOf(401));
        } else {
            return new ResponseEntity<>("Sukses login", HttpStatusCode.valueOf(200));
        }
    }

    @Override
    public ResponseEntity<List<UserDto>> getAllUser() {
        List<User> listUser = userRepository.findAll();
        List<UserDto> listUserDto = listUser.stream().map((user) ->  UserMapper.mapToUserDto(user)).collect(Collectors.toList());
        return ResponseEntity.ok(listUserDto);
    }

    @Override
    public ResponseEntity<String> updateUser(UserDto userDto) {
        Optional<User> user = userRepository.findById(userDto.getId());
        User savedUser = user.get();
        User userTemp = userRepository.findByUsername(userDto.getUsername());
        if(userTemp == null){
            if(savedUser.getPassword().equals(userDto.getPassword())){
                return new ResponseEntity<>("Password tidak boleh sama dengan password sebelumnya", HttpStatusCode.valueOf(400));
            } else {
                savedUser.setUsername(userDto.getUsername());
                savedUser.setPassword(userDto.getPassword());

                userRepository.save(savedUser);

                return new ResponseEntity<>("Sukses", HttpStatusCode.valueOf(201));
            }
        } else {
            return new ResponseEntity<>("Username sudah terpakai", HttpStatusCode.valueOf(409));
        }
    }
}
