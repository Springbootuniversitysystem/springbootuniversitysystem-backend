package com.smartcareer.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contact_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfo {
    @Id
    private Long id = 1L; // Hardcoded to 1 so there is only ever one active record

    private String supportEmail;
    private String supportPhone;
    private String officeLocation;
}