package com.negrea.csf.repository;

import com.negrea.csf.model.schedule.ScheduleAssigned;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleAssignedRepository extends JpaRepository<ScheduleAssigned, Long> {
}
