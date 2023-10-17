package com.campus.joy_gym.service;

import com.campus.joy_gym.payload.dto.ApiRequestDTO;
import org.springframework.http.ResponseEntity;

public interface MLApiService {
    ResponseEntity getWorkOutSchedule(ApiRequestDTO apiRequestDTO);

}
