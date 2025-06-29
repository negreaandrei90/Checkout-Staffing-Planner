package com.negrea.csf.service;

import com.negrea.csf.config.security.CurrentUser;
import com.negrea.csf.dto.schedule.request.ScheduleWishDtoRequest;
import com.negrea.csf.dto.schedule.response.ScheduleWishDtoResponse;
import com.negrea.csf.dto.user.response.UserDtoResponse;
import com.negrea.csf.mapper.schedule.ScheduleWishMapper;
import com.negrea.csf.mapper.user.UserMapper;
import com.negrea.csf.model.schedule.ScheduleWish;
import com.negrea.csf.model.schedule.Shift;
import com.negrea.csf.model.user.User;
import com.negrea.csf.repository.ScheduleWishRepository;
import com.negrea.csf.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class WishbookServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private ScheduleWishRepository wishRepository;

    @Mock
    private ScheduleWishMapper wishMapper;

    @Mock
    private UserMapper userMapper;

    @Mock
    private CurrentUser currentUser;

    @InjectMocks
    private WishbookService wishbookService;

    private User mockUser;
    private ScheduleWishDtoRequest request;
    private ScheduleWish mappedWish;
    private ScheduleWish savedWish;
    private LocalDate date;

    @BeforeEach
    void setUp() {
        date = LocalDate.of(2025, 7, 15);

        mockUser = new User();
        mockUser.setId(1L);
        mockUser.setWishes(new ArrayList<>());

        request = new ScheduleWishDtoRequest();
        request.setDate(date);
        request.setShift(Shift.EARLY);

        mappedWish = ScheduleWish.builder()
                .date(date)
                .shift(Shift.EARLY)
                .user(null) // will be set in service
                .build();

        savedWish = ScheduleWish.builder()
                .id(10L)
                .date(date)
                .shift(Shift.EARLY)
                .user(mockUser)
                .build();
    }

    @Test
    void createWish_shouldReturnResponse_whenWishIsCreated() {
        // Arrange
        Mockito.when(currentUser.getCurrentUser()).thenReturn(mockUser);
        Mockito.when(wishMapper.toEntity(request)).thenReturn(mappedWish);
        Mockito.when(wishRepository.findByUserAndDate(mockUser, date)).thenReturn(Optional.of(savedWish));
        Mockito.when(wishMapper.toDto(savedWish)).thenReturn(new ScheduleWishDtoResponse());
        Mockito.when(userMapper.toDto(mockUser)).thenReturn(new UserDtoResponse());

        // Act
        ScheduleWishDtoResponse response = wishbookService.createWish(request);

        // Assert
        Assertions.assertNotNull(response);
        Mockito.verify(userRepository).saveAndFlush(mockUser);
        Mockito.verify(wishMapper).toDto(savedWish);
        Mockito.verify(userMapper).toDto(mockUser);
    }

    @Test
    void createWish_shouldThrow_whenWishNotCreated() {
        // Arrange
        Mockito.when(currentUser.getCurrentUser()).thenReturn(mockUser);
        Mockito.when(wishMapper.toEntity(request)).thenReturn(mappedWish);
        Mockito.when(wishRepository.findByUserAndDate(mockUser, date)).thenReturn(Optional.empty());

        // Assert
        RuntimeException exception = Assertions.assertThrows(
                RuntimeException.class,
                () -> wishbookService.createWish(request)
        );

        Assertions.assertEquals("Could not create wish", exception.getMessage());
    }
}
