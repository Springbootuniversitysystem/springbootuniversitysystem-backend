package com.smartcareer.service;

import com.smartcareer.dto.LearnerDTO;
import com.smartcareer.dto.LoginRequestDTO;
import com.smartcareer.dto.LoginResponseDTO;
import com.smartcareer.dto.RegisterRequestDTO;
import com.smartcareer.entity.Role;
import com.smartcareer.entity.User;
import com.smartcareer.exception.EmailAlreadyExistException;
import com.smartcareer.exception.InactiveAccountException;
import com.smartcareer.exception.InvalidCredentialsException;
import com.smartcareer.repository.RoleRepository;
import com.smartcareer.repository.UserRepository;
import com.smartcareer.response.Response;
import com.smartcareer.security.JwtSecurity;
import jakarta.transaction.Transactional;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class AuthServiceImp  implements  AuthService{

    private  final  JwtSecurity jwtSecurity;
    private final  PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private  final  LearnerService learnerService;

    @Transactional
    @Override
    public Response<Void> register(RegisterRequestDTO registerRequestDTO) {

        // Check email
        if (userRepository.existsByEmail(registerRequestDTO.getEmail())) {
            throw new EmailAlreadyExistException("Email is already registered");
        }

        // Check passwords
        if (!registerRequestDTO.getPassword().equals(registerRequestDTO.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        // Get LEARNER role
        Role learnerRole = roleRepository.findByName("LEARNER")
                .orElseThrow(() -> new RuntimeException("LEARNER role not found"));

        // Create User
        // Create User
        User user = new User();
        user.setEmail(registerRequestDTO.getEmail());
        user.setUsername(registerRequestDTO.getEmail()); // <-- THIS IS THE MISSING MAGIC LINE
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setPhone_number(registerRequestDTO.getPhoneNumber());
        user.setActive(true);
        user.setCreatedAt(LocalDateTime.now());
        user.setRoles(List.of(learnerRole));



        // Save User first
        user = userRepository.save(user);

        // Create Learner
        LearnerDTO learnerDTO = Helper.mapRegisterRequestToLearnerDTO(registerRequestDTO);

         learnerService.createLearnerEntity(learnerDTO, user);



        return Response.success(null, "Account successfully created.");
    }

    @Override
    public Response<LoginResponseDTO> login(LoginRequestDTO requestDTO) {

        // Find user by email
        Optional<User> user= userRepository.findByEmail(requestDTO.getEmail());
        User foundUser=  user.orElseThrow(()-> new InvalidCredentialsException("Invalid email or password"));

        // Verify password
        if(!passwordEncoder.matches(requestDTO.getPassword(),foundUser.getPassword()))
        {
            throw  new InvalidCredentialsException("Invalid email or password");
        }

        //Check if the account is active
        if(!foundUser.isActive())
        {
            throw  new InactiveAccountException("Your account is not active. Please contact support.");
        }


        String token = jwtSecurity.generateJwTFromEmail(foundUser.getEmail());

        // Convert roles to strings
        List<String> roles = foundUser.getRoles()
                .stream()
                .map(Role::getName)
                .toList();

        LoginResponseDTO loginResponseDTO=
                new LoginResponseDTO(token,foundUser.getEmail(),roles);

        return Response.success( loginResponseDTO, "Login successful");

    }
}
