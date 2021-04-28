package com.hospital.service;

import com.hospital.Validator.MedicalEmployeeValidator;
import com.hospital.builder.MedicalEmployeeBuilder;
import com.hospital.dao.MedicalEmployeeDao;
import com.hospital.model.MedicalEmployee;
import com.hospital.util.Type;
import com.hospital.util.UserDetails;

import java.util.List;

public class MedicalEmployeeService {

    private MedicalEmployeeBuilder medicalEmployeeBuilder;
    private MedicalEmployeeDao medicalEmployeeDao;
    private IOService ioService;
    private MedicalEmployeeValidator medicalEmployeeValidator;

    public MedicalEmployeeService(IOService ioService,MedicalEmployeeDao medicalEmployeeDao) {
        this.medicalEmployeeBuilder = new MedicalEmployeeBuilder(ioService);
        this.medicalEmployeeDao = medicalEmployeeDao;
        this.ioService = ioService;
        this.medicalEmployeeValidator = new MedicalEmployeeValidator(medicalEmployeeDao, ioService);
    }

    public void register() {
        MedicalEmployee medicalEmployee = medicalEmployeeBuilder.createMedicalEmployee();
        if (medicalEmployeeValidator.isNotValid(medicalEmployee)) {
            return;
        }
        medicalEmployeeDao.save(medicalEmployee);

    }

    public void login(UserDetails userDetails) {
        String username = ioService.getUserCredentials("username");
        String password = ioService.getUserCredentials("password");

        List<MedicalEmployee> userList = medicalEmployeeDao.findByUsername(username);
        if (userList.size() == 0) {
            ioService.displayError("Username not found , please register first");
            return;
        } else if (userList.size() > 1) {
            ioService.displayError("Please contact support service");
            return;
        }
        MedicalEmployee user = userList.get(0);
        if (!userList.get(0).getPassword().equals(password)) {
            ioService.displayError("Incorect password");
            return;
        }

        ioService.displaySuccessfulMessage("Login succesfull");
        userDetails.setLoggedIn(true);
        userDetails.setFriendlyName(user.getFirstName());
        if (user.getType() == Type.DOCTOR) {
            userDetails.setDoctor(true);

        }
        userDetails.setMedicalEmployeeId(user.getMedicalEmployeeId());
    }

    public void logOut(UserDetails userDetails) {
        ioService.displaySuccessfulMessage("You have successfully logged out");
        userDetails.setLoggedIn(false);
        userDetails.setHasJustBeenLoggedOut(true);
    }
}
