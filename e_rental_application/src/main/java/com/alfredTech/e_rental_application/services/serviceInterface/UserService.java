package com.alfredTech.e_rental_application.services.serviceInterface;

import com.alfredTech.e_rental_application.data.models.User;
import com.alfredTech.e_rental_application.dtos.reponse.Response;
import com.alfredTech.e_rental_application.dtos.requests.LoginRequest;

public interface UserService {
    Response registerUser(User user);
    Response loginUser(LoginRequest loginRequest);
    Response getAllUsers();
    Response getUserBookingHistory(Long userId);
    Response deleteUser(Long userId);
    Response getUserById(Long userId);
    Response getMyInfo(String email);

}
