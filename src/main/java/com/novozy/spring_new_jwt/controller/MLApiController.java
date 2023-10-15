package com.novozy.spring_new_jwt.controller;

import com.novozy.spring_new_jwt.payload.dto.ApiRequestDTO;
import com.novozy.spring_new_jwt.service.MLApiService;
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
