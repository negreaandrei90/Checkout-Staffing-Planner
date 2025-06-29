package com.negrea.csf.controller;

import com.negrea.csf.model.schedule.ScheduleWish;
import com.negrea.csf.model.schedule.Shift;
import com.negrea.csf.model.user.Role;
import com.negrea.csf.model.user.User;
import com.negrea.csf.repository.RoleRepository;
import com.negrea.csf.repository.ScheduleWishRepository;
import com.negrea.csf.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class PlanningControllerINTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ScheduleWishRepository wishRepository;

    @Test
    @WithMockUser(username = "admin@admin.com", roles = {"ADMIN"})
    void assignWish_shouldReturnShiftDto() throws Exception {
        Optional<Role> employeeRole = roleRepository.findById(2L);

        if(employeeRole.isEmpty()){
            Assertions.fail("Expected 'Employee' role with ID 2, but was not found");
        }

        User user1 = User.builder()
                .name("Alice")
                .email("alice@test.com")
                .password("test123")
                .role(employeeRole.get())
                .build();

        User user2 = User.builder()
                .name("Bob")
                .email("bob@test.com")
                .password("test123")
                .role(employeeRole.get())
                .build();

        userRepository.saveAll(List.of(user1, user2));

        ScheduleWish wish1 = ScheduleWish.builder()
                .date(LocalDate.now())
                .shift(Shift.EARLY)
                .user(user1)
                .build();

        ScheduleWish wish2 = ScheduleWish.builder()
                .date(LocalDate.now())
                .shift(Shift.EARLY)
                .user(user2)
                .build();

        wishRepository.saveAll(List.of(wish1, wish2));

        mockMvc.perform(post("/csf/planning")
                        .param("wish1", wish1.getId().toString())
                        .param("wish2", wish2.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employees.length()").value(2));
    }

    @Test
    @WithMockUser(username = "admin@admin.com", roles = {"ADMIN"})
    void assignWish_shouldReturnNotFound_whenWishMissing() throws Exception {
        mockMvc.perform(post("/csf/planning")
                        .param("wish1", "1")
                        .param("wish2", String.valueOf(Integer.MAX_VALUE)))
                .andExpect(status().isNotFound());
    }
}
