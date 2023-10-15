package com.novozy.spring_new_jwt.service;

import com.novozy.spring_new_jwt.payload.dto.ApiRequestDTO;
import org.springframework.http.ResponseEntity;

public interface MLApiService {
    ResponseEntity getWorkOutSchedule(ApiRequestDTO apiRequestDTO);

}
