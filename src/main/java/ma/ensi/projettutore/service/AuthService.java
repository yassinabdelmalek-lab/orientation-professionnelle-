package ma.ensi.projettutore.service;

import ma.ensi.projettutore.dto.request.LoginRequest;
import ma.ensi.projettutore.dto.request.RegisterRequest;
import ma.ensi.projettutore.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse login(LoginRequest loginRequest);
    AuthResponse register(RegisterRequest registerRequest);
    AuthResponse refreshToken(String refreshToken);
}