package com.smartcareer.dto;

import com.smartcareer.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LoginResponseDTO {


    private  String token;
    private  String email;
    private List<String> roles;

}
