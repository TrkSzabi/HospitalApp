package com.hospital.builder;

import com.hospital.model.MedicalEmployee;
import com.hospital.service.IOService;
import com.hospital.util.Type;

import java.util.Locale;

public class MedicalEmployeeBuilder {
    private IOService ioService;

    public MedicalEmployeeBuilder(IOService ioService) {
        this.ioService = ioService;
    }

    public MedicalEmployee createMedicalEmployee() {
        String userName = ioService.getRegistrationData("username");
        String password = ioService.getRegistrationData("password");
        String firstName = ioService.getRegistrationData("first name");
        String lastName = ioService.getRegistrationData("last name");
        String email = ioService.getRegistrationData("email");
        String type = ioService.getMedicalStatus();
        return MedicalEmployee.builder()
                .userName(userName)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .type(convert(type))
                .build();

    }

    private Type convert(String type) {
        if (type.equalsIgnoreCase("doctor")) {
            return Type.DOCTOR;
        } else if (type.equalsIgnoreCase("nurse")) {
            return Type.NURSE;
        }
        return Type.OTHER;
    }
}
