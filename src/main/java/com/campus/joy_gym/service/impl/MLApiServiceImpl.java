package com.campus.joy_gym.service.impl;

import com.google.gson.Gson;
import com.campus.joy_gym.payload.dto.ApiRequestDTO;
import com.campus.joy_gym.payload.dto.ApiResponseDTO;
import com.campus.joy_gym.service.MLApiService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MLApiServiceImpl implements MLApiService {

    @Autowired
    RestTemplate restTemplate;

    String url = "http://localhost:5000/predict";
    private static Logger LOGGER = LogManager.getLogger("mlLog");

    @Override
    public ResponseEntity getWorkOutSchedule(ApiRequestDTO apiRequestDTO) {
        try {

            String schedule = "";
            ResponseEntity<String> response = null;
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Accept", "application/json");
            httpHeaders.add("Content-Type", "application/json");
            HttpEntity<ApiRequestDTO> entity = new HttpEntity<>(apiRequestDTO, httpHeaders);
            response = restTemplate.postForEntity(url, entity, String.class);
            Gson gson = new Gson();
            ApiResponseDTO apiResponseDTO = gson.fromJson(response.getBody(), ApiResponseDTO.class);

            List<List<Integer>> prediction = apiResponseDTO.getPrediction();

            int rowPosition = -1;
            int colPosition = -1;

            for (int i = 0; i < prediction.size(); i++) {
                List<Integer> row = prediction.get(i);

                for (int j = 0; j < row.size(); j++) {
                    if (row.get(j) == 1) {

                        rowPosition = i;
                        colPosition = j;
                        break;
                    } else {

                    }
                }
                if (rowPosition != -1 && colPosition != -1) {
                    break;
                }
            }

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (colPosition == 0) {
                schedule = "Bulk Up Blueprint";
            } else if (colPosition == 1) {
                schedule = "Fat Burn Fiesta";
            } else if (colPosition == 2) {
                schedule = "Fit Life Routine";
            } else if (colPosition == 3) {
                schedule = "Gain Game Plan";
            } else if (colPosition == 4) {
                schedule = "Power Plan";
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("scheduleValue", schedule);
            return new ResponseEntity<>(jsonObject, HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.info("GymMemberController | GymMemberService | getMemberSchedule | " + ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
