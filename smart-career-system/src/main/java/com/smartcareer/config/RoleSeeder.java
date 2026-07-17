package com.smartcareer.config;

import com.smartcareer.entity.Role;
import com.smartcareer.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        createRoleIfNotExists("ADMIN");
        createRoleIfNotExists("LEARNER");
        createRoleIfNotExists("UNIVERSITY");
        createRoleIfNotExists("CAREER_ADVISOR");
    }

    private void createRoleIfNotExists(String strRole) {

        if(roleRepository.findByName(strRole).isEmpty())
        {
            Role role = new Role();
            role.setName(strRole);

            roleRepository.save(role);
        }
    }
}
