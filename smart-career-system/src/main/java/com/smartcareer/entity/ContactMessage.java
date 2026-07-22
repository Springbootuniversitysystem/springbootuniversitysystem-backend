package com.smartcareer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "contact_messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Valid email is required")
    @NotBlank
    private String emailAddress;

    @NotBlank(message = "Subject is required")
    private String subject;

    @NotBlank(message = "Message content cannot be blank")
    @Column(columnDefinition = "TEXT")
    private String message;

    private LocalDateTime submittedAt = LocalDateTime.now();
}