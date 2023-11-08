package com.group06.sakila.services;

import com.group06.sakila.entities.Staff;
import com.group06.sakila.request_models.StaffRegisterRequest;

public interface StaffService {
    Staff createStaff(StaffRegisterRequest staffRequest);
    Staff login(String username, String password) throws Exception;
}
