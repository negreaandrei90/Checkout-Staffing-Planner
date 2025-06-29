package com.negrea.csf.mapper.schedule;

import com.negrea.csf.dto.schedule.request.ScheduleAssignedDtoRequest;
import com.negrea.csf.dto.schedule.response.ScheduleAssignedDtoResponse;
import com.negrea.csf.model.schedule.ScheduleAssigned;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")

public interface ScheduleAssignedMapper {
    ScheduleAssignedMapper INSTANCE = Mappers.getMapper(ScheduleAssignedMapper.class);

    ScheduleAssigned toEntity(ScheduleAssignedDtoRequest request);

    ScheduleAssignedDtoResponse toDto(ScheduleAssigned schedule);
}
