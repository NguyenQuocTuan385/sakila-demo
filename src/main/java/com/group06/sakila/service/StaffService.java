package com.group06.sakila.service;

import com.group06.sakila.entity.Staff;
import com.group06.sakila.request_model.StaffRegisterRequest;

public interface StaffService {
    Staff createStaff(StaffRegisterRequest staffRequest);
    String login(String email, String password) throws Exception;
}
