package com.jhops10.personal_finance.user;

import static com.jhops10.personal_finance.common.UserConstants.USER;
import static com.jhops10.personal_finance.common.UserConstants.USER_DTO;

import com.jhops10.personal_finance.common.UserConstants;
import com.jhops10.personal_finance.exceptions.EmailAlreadyInUseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;


    @Test
    void shouldCreateUserWithSuccess() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(USER);

        User sut = userService.createUser(USER_DTO);

        assertNotNull(sut);
        assertEquals(1L, sut.getId());
        assertEquals("Usuario Teste", sut.getName());
        assertEquals("email@teste.com", sut.getEmail());
        assertEquals("senha", sut.getPassword());
        assertEquals(LocalDateTime.of(2025, 4, 22, 12, 0), sut.getCreatedAt());

        verify(userRepository).findByEmail("email@teste.com");
        verify(userRepository).save(any(User.class));
    }

    @Test
    void shouldThrowExceptionWhenUserEmailAlreadyExists() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(USER));

        EmailAlreadyInUseException sut = assertThrows(
                EmailAlreadyInUseException.class,
                () -> userService.createUser(USER_DTO)
        );

        assertEquals("O email: " + USER_DTO.email() + " já está em uso.", sut.getMessage());

        verify(userRepository).findByEmail(USER_DTO.email());
        verifyNoMoreInteractions(userRepository);
    }
}