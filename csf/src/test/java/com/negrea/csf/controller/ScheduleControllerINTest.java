package com.negrea.csf.controller;

import com.negrea.csf.model.schedule.ScheduleAssigned;
import com.negrea.csf.model.schedule.Shift;
import com.negrea.csf.model.user.Role;
import com.negrea.csf.model.user.User;
import com.negrea.csf.repository.RoleRepository;
import com.negrea.csf.repository.ScheduleAssignedRepository;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class ScheduleControllerINTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScheduleAssignedRepository scheduleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    @WithMockUser(username = "admin@admin.com", roles = {"ADMIN"})
    void getScheduleOfDay_shouldReturnResponse() throws Exception {
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

        User admin = User.builder()
                .name("admin10")
                .email("admin10@test.com")
                .password("admin")
                .role(employeeRole.get())
                .build();
        userRepository.saveAll(List.of(user1, user2, admin));

        LocalDate date = LocalDate.now();
        ScheduleAssigned schedule1 = ScheduleAssigned.builder()
                .date(LocalDate.now())
                .shift(Shift.EARLY)
                .user(user1)
                .assignedBy(admin)
                .build();

        ScheduleAssigned schedule2 = ScheduleAssigned.builder()
                .date(LocalDate.now())
                .shift(Shift.EARLY)
                .user(user2)
                .assignedBy(admin)
                .build();

        scheduleRepository.saveAll(List.of(schedule1, schedule2));

        mockMvc.perform(get("/csf/schedule")
                        .param("date", String.valueOf(date)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.earlyShift.employees.length()").value(2));
    }

    @Test
    @WithMockUser(username = "admin@admin.com", roles = {"ADMIN"})
    void getScheduleOfDay_shouldReturnError_whenEmpty() throws Exception {
        mockMvc.perform(get("/csf/schedule")
                        .param("date", "2099-01-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.earlyShift").doesNotExist())
                .andExpect(jsonPath("$.lateShift").doesNotExist());
    }
}
