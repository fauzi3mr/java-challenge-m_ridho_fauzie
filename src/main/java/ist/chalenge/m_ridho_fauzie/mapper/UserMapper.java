package ist.chalenge.m_ridho_fauzie.mapper;

import ist.chalenge.m_ridho_fauzie.dto.UserDto;
import ist.chalenge.m_ridho_fauzie.entity.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user){
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword()
        );
    }

    public static User mapToUser(UserDto userDto){
        return new User(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getPassword()
        );
    }
}
