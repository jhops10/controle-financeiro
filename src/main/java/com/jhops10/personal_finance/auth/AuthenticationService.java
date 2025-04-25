package com.jhops10.personal_finance.auth;

import com.jhops10.personal_finance.exceptions.IncorrectPasswordException;
import com.jhops10.personal_finance.exceptions.UserNotFoundException;
import com.jhops10.personal_finance.security.JwtService;
import com.jhops10.personal_finance.user.User;
import com.jhops10.personal_finance.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {
       User user = userRepository.findByEmail(request.email())
               .orElseThrow(() -> new UserNotFoundException("Usuário com o email: " + request.email() + " não encontrado"));

       if (!passwordEncoder.matches(request.password(), user.getPassword())) {
           throw new IncorrectPasswordException("Senha Incorreta!");
       }

       String token = jwtService.generateToken(user.getEmail());

       return new AuthenticationResponseDTO(token);
    }
}
