package com.negrea.csf.model.schedule;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "schedule_wish")
@Entity
public class ScheduleWish extends Schedule {
}
