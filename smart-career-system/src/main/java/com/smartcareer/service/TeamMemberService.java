package com.smartcareer.service;

import com.smartcareer.dto.TeamMemberDTO;
import com.smartcareer.entity.TeamMember;
import com.smartcareer.repository.TeamMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamMemberService {

    private final TeamMemberRepository repository;

    public TeamMemberService(TeamMemberRepository repository) {
        this.repository = repository;
    }

    public List<TeamMember> getAllTeamMembers() {
        return repository.findAll();
    }

    public TeamMember addTeamMember(TeamMemberDTO dto) {
        TeamMember member = new TeamMember();
        member.setFullName(dto.getFullName());
        member.setRole(dto.getRole());
        member.setDescription(dto.getDescription());

        return repository.save(member);
    }

    public TeamMember updateTeamMember(Long id, TeamMemberDTO dto) {
        TeamMember member = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team member not found with ID: " + id));

        member.setFullName(dto.getFullName());
        member.setRole(dto.getRole());
        member.setDescription(dto.getDescription());

        return repository.save(member);
    }
}