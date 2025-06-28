package com.negrea.csf.model.mapper.schedule;

import com.negrea.csf.model.dto.schedule.request.ScheduleWishDtoRequest;
import com.negrea.csf.model.dto.schedule.response.ScheduleWishDtoResponse;
import com.negrea.csf.model.schedule.ScheduleWish;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ScheduleWishMapper {

    ScheduleWishMapper INSTANCE = Mappers.getMapper(ScheduleWishMapper.class);

    ScheduleWish toEntity(ScheduleWishDtoRequest request);

    ScheduleWishDtoResponse toDto(ScheduleWish wish);
}
