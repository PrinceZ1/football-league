package com.princez1.football_league.mapper;

import com.princez1.football_league.dto.UserDto;
import com.princez1.football_league.entity.User;
import org.springframework.stereotype.Component;

@Component
public class EntityDtoMapper {
    public UserDto mapUserToDtoBasic(User user){
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setPhone(user.getPhone());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole().name());
        userDto.setFullName(user.getFullName());
        return userDto;

    }
}
