package com.negrea.csf.model.schedule;

import com.negrea.csf.model.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Table(name = "schedule_assigned")
@Entity
public class ScheduleAssigned extends Schedule {
    @ManyToOne
    @JoinColumn(name = "assigned_by")
    private User assignedBy;
}
