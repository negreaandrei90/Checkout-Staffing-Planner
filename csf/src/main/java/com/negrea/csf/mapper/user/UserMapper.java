package com.negrea.csf.mapper.user;

import com.negrea.csf.dto.user.response.UserScheduleDto;
import com.negrea.csf.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserScheduleDto toUserScheduleDto(User user);

    List<UserScheduleDto> toUserScheduleDtoList(List<User> users);
}
