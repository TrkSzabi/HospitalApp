package com.hospital.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer medicalRecorId;
    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn
    private MedicalEmployee medicalEmployee;

    @ManyToOne
    @JoinColumn
    private Patient patient;

    private String information;
}
