package com.negrea.csf.repository;

import com.negrea.csf.model.schedule.ScheduleWish;
import com.negrea.csf.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ScheduleWishRepository extends JpaRepository<ScheduleWish, Long> {
    Optional<ScheduleWish> findByUserAndDate(User user, LocalDate date);
}
