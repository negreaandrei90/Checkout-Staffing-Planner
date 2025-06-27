package com.negrea.csf.model.schedule;

import lombok.Getter;

import java.time.LocalTime;

@Getter
public enum Shift {
    EARLY(LocalTime.of(7, 0), LocalTime.of(15, 30)),
    LATE(LocalTime.of(13, 30), LocalTime.of(18, 0));

    private final LocalTime startTime;
    private final LocalTime endTime;

    Shift(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
