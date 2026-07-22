package com.smartcareer.service;


import com.smartcareer.dto.DashboardDTO;
import com.smartcareer.entity.Learner;
import com.smartcareer.entity.User;
import com.smartcareer.exception.LearnerNotFoundException;
import com.smartcareer.repository.LearnerRepository;
import com.smartcareer.repository.SavedCourseRepository;
import com.smartcareer.repository.UserRepository;
import com.smartcareer.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImp  implements  DashboardService{

    private final UserRepository userRepository;
    private final SavedCourseRepository savedCourseRepository;
    private  final LearnerRepository learnerRepository;

    @Override
    public Response<DashboardDTO> getDashboard(Authentication authentication) {

        User principal = (User) authentication.getPrincipal();

        User user = userRepository.findByEmail(principal.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Learner learner = learnerRepository.findByEmail(user.getEmail())
                .orElseThrow(() ->
                        new LearnerNotFoundException("Learner not found"));

        DashboardDTO dashboard = new DashboardDTO();

        // Temporary values
        dashboard.setGuidanceSessions(3);
        dashboard.setCvCompletion(80);

        long savedCourses = savedCourseRepository.countByLearner(learner);
        dashboard.setSavedCourses((int) savedCourses);

        return Response.success(dashboard, "Dashboard loaded successfully");
    }
}
