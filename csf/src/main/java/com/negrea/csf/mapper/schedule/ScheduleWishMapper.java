package com.negrea.csf.mapper.schedule;

import com.negrea.csf.dto.schedule.request.ScheduleWishDtoRequest;
import com.negrea.csf.dto.schedule.response.ScheduleWishDtoResponse;
import com.negrea.csf.model.schedule.ScheduleWish;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ScheduleWishMapper {

    ScheduleWishMapper INSTANCE = Mappers.getMapper(ScheduleWishMapper.class);

    ScheduleWish toEntity(ScheduleWishDtoRequest request);

    ScheduleWishDtoResponse toDto(ScheduleWish wish);
}
