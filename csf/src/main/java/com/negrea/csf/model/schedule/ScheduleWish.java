package com.negrea.csf.model.schedule;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Table(name = "schedule_wish")
@Entity
@SuperBuilder
@NoArgsConstructor
public class ScheduleWish extends Schedule {
}
