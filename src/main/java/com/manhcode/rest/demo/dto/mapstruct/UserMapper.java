package com.manhcode.rest.demo.dto.mapstruct;

import com.manhcode.rest.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface UserMapper {

    // create instance
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    //User to User DTO
    @Mappings(
            {
                    @Mapping(source = "email", target = "emailaddress"),
                    @Mapping(source = "role", target = "rolename")
            }
    )
    UserMsDto userToUserDto(User user);

    //List<User> to List<UserMsDto>
    List<UserMsDto> usersToUserDtos(List<User> users);

}
