package com.campus.joy_gym.controller;

import com.campus.joy_gym.service.MLApiService;
import com.campus.joy_gym.payload.dto.ApiRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/workout")
public class MLApiController {

    @Autowired
    private MLApiService mlApiService;

    @PostMapping("/getWorkOutSchedule")
    public ResponseEntity getWorkOutSchedule(@RequestBody ApiRequestDTO apiRequestDTO) {
        return mlApiService.getWorkOutSchedule(apiRequestDTO);
    }
}
