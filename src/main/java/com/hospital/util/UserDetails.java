package com.hospital.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@ToString
public class UserDetails {

    private Integer medicalEmployeeId;
    private String friendlyName;
    private boolean isLoggedIn;
    private boolean isDoctor;
    private boolean hasJustBeenLoggedOut;

}
