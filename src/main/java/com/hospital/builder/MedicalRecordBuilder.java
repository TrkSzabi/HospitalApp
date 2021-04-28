package com.hospital.builder;

import com.hospital.dao.MedicalEmployeeDao;
import com.hospital.dao.PatientDao;
import com.hospital.model.MedicalEmployee;
import com.hospital.model.MedicalRecords;
import com.hospital.model.Patient;
import com.hospital.service.IOService;

import java.time.LocalDateTime;

public class MedicalRecordBuilder {

    private MedicalEmployeeDao medicalEmployeeDao;
    private PatientDao patientDao;
    IOService ioService;

    public MedicalRecordBuilder(MedicalEmployeeDao medicalEmployeeDao, PatientDao patientDao, IOService ioService) {
        this.medicalEmployeeDao = medicalEmployeeDao;
        this.patientDao = patientDao;
        this.ioService = ioService;
    }

    public MedicalRecords create(Integer medicalEmployeeId) {
        String cnp = ioService.getGenericPatientData("cnp");
        String information = ioService.getGenericPatientData("information");

        MedicalEmployee medicalEmployee = medicalEmployeeDao.findById(MedicalEmployee.class,medicalEmployeeId);

        Patient patient = patientDao.findByCnp(cnp);


       return MedicalRecords.builder()
                .information(information)
                .localDateTime(LocalDateTime.now())
                .patient(patient)
                .medicalEmployee(medicalEmployee)
                .build();
    }
}
