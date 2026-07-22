package com.smartcareer.controller;

import com.smartcareer.dto.TeamMemberDTO;
import com.smartcareer.entity.TeamMember;
import com.smartcareer.response.Response;
import com.smartcareer.service.TeamMemberService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/about/team")
public class TeamMemberController {

    private final TeamMemberService teamMemberService;

    public TeamMemberController(TeamMemberService teamMemberService) {
        this.teamMemberService = teamMemberService;
    }

    // PUBLIC: Fetch team for the frontend
    @GetMapping
    public ResponseEntity<Response<List<TeamMember>>> getTeam() {
        return ResponseEntity.ok(Response.success(teamMemberService.getAllTeamMembers(), "Team fetched successfully"));
    }

    // ADMIN ONLY: Add a member
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response<TeamMember>> addMember(@Valid @RequestBody TeamMemberDTO dto) {
        return ResponseEntity.ok(Response.success(teamMemberService.addTeamMember(dto), "Team member added successfully"));
    }

    // ADMIN ONLY: Update a member
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response<TeamMember>> updateMember(@PathVariable Long id, @Valid @RequestBody TeamMemberDTO dto) {
        return ResponseEntity.ok(Response.success(teamMemberService.updateTeamMember(id, dto), "Team member updated successfully"));
    }
}