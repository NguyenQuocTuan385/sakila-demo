package com.group06.sakila.services;

import com.group06.sakila.entities.Staff;
import com.group06.sakila.request_models.StaffRegisterRequest;

import java.util.HashMap;

public interface StaffService {
    Staff createStaff(StaffRegisterRequest staffRequest);
//    String login(String username, String password) throws Exception;

    HashMap<String, String> login(String username, String password) throws Exception;

    HashMap<String, String> refresh(String rfToken) throws Exception;
}
