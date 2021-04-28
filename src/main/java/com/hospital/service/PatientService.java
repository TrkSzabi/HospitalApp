package com.hospital.service;

import com.hospital.builder.MedicalRecordBuilder;
import com.hospital.builder.PatientBuilder;
import com.hospital.dao.MedicalEmployeeDao;
import com.hospital.dao.MedicalRecordDao;
import com.hospital.dao.PatientDao;
import com.hospital.model.MedicalEmployee;
import com.hospital.model.MedicalRecords;
import com.hospital.model.Patient;
import com.hospital.util.UserDetails;

import java.util.List;

public class PatientService {

    private IOService ioService;
    private PatientBuilder patientBuilder;
    private PatientDao patientDao;
    private MedicalRecordBuilder medicalRecordBuilder;
    private MedicalRecordDao medicalRecordDao;



    public PatientService(IOService ioService, MedicalEmployeeDao medicalEmployeeDao) {
        this.ioService = ioService;
        this.patientBuilder = new PatientBuilder(ioService, medicalEmployeeDao);
        this.patientDao = new PatientDao();
        this.medicalRecordBuilder = new MedicalRecordBuilder(medicalEmployeeDao,patientDao,ioService);
        this.medicalRecordDao = new MedicalRecordDao();

    }

    public void addPatient(UserDetails userDetails) {
        Patient patient = patientBuilder.createPatient(userDetails);
        patientDao.save(patient);
    }

    public void displayAllPatients() {
        List<Patient> patientList = patientDao.findAll();
        ioService.display(patientList);

    }

    public void findPatientByLastName() {
        String lastName = ioService.getGenericPatientData("last name");
        List<Patient> patientList = patientDao.findByLastName(lastName);
        ioService.display(patientList);

    }


    public void displayMyPatients(UserDetails userDetails) {
        List<Patient> patientList = patientDao.findPatientsFor(userDetails.getMedicalEmployeeId());
        ioService.display(patientList);
    }


    public void addMedicalRecords(UserDetails userDetails) {
       MedicalRecords medicalRecords = medicalRecordBuilder.create(userDetails.getMedicalEmployeeId());
       medicalRecordDao.save(medicalRecords);
    }
}