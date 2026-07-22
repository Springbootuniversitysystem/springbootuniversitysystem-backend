package com.smartcareer.controller;

import com.smartcareer.entity.ContactInfo;
import com.smartcareer.entity.ContactMessage;
import com.smartcareer.repository.ContactInfoRepository;
import com.smartcareer.repository.ContactMessageRepository;
import com.smartcareer.response.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/contact")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ContactController {

    private final ContactMessageRepository messageRepository;
    private final ContactInfoRepository infoRepository;

    // Public: User submitting the form
    @PostMapping("/message")
    public ResponseEntity<Response<ContactMessage>> sendMessage(@Valid @RequestBody ContactMessage message) {
        ContactMessage saved = messageRepository.save(message);
        return ResponseEntity.ok(Response.success(saved, "Message sent successfully to PathFinder support."));
    }

    // Public: Frontend fetching the email/phone to display
    @GetMapping("/info")
    public ResponseEntity<Response<ContactInfo>> getContactInfo() {
        ContactInfo info = infoRepository.findById(1L).orElse(new ContactInfo(1L, "support@pathfinder.co.za", "0800 PATH FIND", "Johannesburg"));
        return ResponseEntity.ok(Response.success(info, "Contact info loaded."));
    }

    // Admin Only: Updating the contact details
    @PutMapping("/info")
    @PreAuthorize("hasAuthority('ADMIN')") // <-- THIS IS THE LOCK!
    public ResponseEntity<Response<ContactInfo>> updateContactInfo(@RequestBody ContactInfo newInfo) {
        newInfo.setId(1L); // Force ID to 1 to overwrite existing
        ContactInfo updated = infoRepository.save(newInfo);
        return ResponseEntity.ok(Response.success(updated, "Contact information updated by Admin."));
    }
}