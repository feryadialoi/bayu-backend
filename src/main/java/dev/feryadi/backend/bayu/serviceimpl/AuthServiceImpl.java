package dev.feryadi.backend.bayu.serviceimpl;

import dev.feryadi.backend.bayu.auth.JwtUtil;
import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.model.request.LoginRequest;
import dev.feryadi.backend.bayu.model.response.ApiResponse;
import dev.feryadi.backend.bayu.model.response.LoginResponse;
import dev.feryadi.backend.bayu.repository.UserRepository;
import dev.feryadi.backend.bayu.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

}
