package org.example.evaexchange.Mapper;

import org.example.evaexchange.Dto.UserDto;
import org.example.evaexchange.Entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

        public UserDto toDto(User user) {
            UserDto dto = new UserDto();
            dto.setId(user.getId());
            dto.setUsername(user.getName());
            dto.setEmail(user.getEmail());
            return dto;
        }

        public User toEntity(UserDto dto) {
            User user = new User();
            user.setId(dto.getId());
            user.setName(dto.getUsername());
            user.setEmail(dto.getEmail());
            return user;
        }
}
