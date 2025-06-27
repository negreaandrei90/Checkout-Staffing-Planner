package com.negrea.csf.repository;

import com.negrea.csf.model.schedule.ScheduleWish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleWishRepository extends JpaRepository<ScheduleWish, Long> {
}
