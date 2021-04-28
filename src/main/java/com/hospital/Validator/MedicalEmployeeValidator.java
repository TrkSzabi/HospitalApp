package com.hospital.Validator;

import com.hospital.dao.MedicalEmployeeDao;
import com.hospital.model.MedicalEmployee;
import com.hospital.service.IOService;

import java.util.List;


public class MedicalEmployeeValidator {

    private MedicalEmployeeDao medicalEmployeeDao;
    private IOService ioService;

    public MedicalEmployeeValidator(MedicalEmployeeDao medicalEmployeeDao, IOService ioService) {
        this.medicalEmployeeDao = medicalEmployeeDao;
        this.ioService = ioService;
    }

    public boolean isNotValid(MedicalEmployee medicalEmployee) {

        List<MedicalEmployee> medicalEmployeeList = medicalEmployeeDao.findByUsername(medicalEmployee.getUserName());
        if (medicalEmployeeList.size() > 0) {
            ioService.displayError("Username already exist");
            return true;
        }
        return false;

    }
}
// mai multe verificari cu IF IF IF IF