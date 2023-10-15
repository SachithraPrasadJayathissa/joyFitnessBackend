package com.novozy.spring_new_jwt.service.impl;

import com.google.gson.Gson;
import com.novozy.spring_new_jwt.payload.dto.ApiRequestDTO;
import com.novozy.spring_new_jwt.payload.dto.ApiResponseDTO;
import com.novozy.spring_new_jwt.service.MLApiService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MLApiServiceImpl implements MLApiService {

    @Autowired
    RestTemplate restTemplate;

    Gson gson = new Gson();
    String url = "http://localhost:5000/predict";

    @Override
    public ResponseEntity getWorkOutSchedule(ApiRequestDTO apiRequestDTO) {
        try {
            String schedule="";
            ResponseEntity response = null;
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Accept", "application/json");
            httpHeaders.add("Content-Type", "application/json");
            HttpEntity<ApiRequestDTO> entity = new HttpEntity<>(apiRequestDTO, httpHeaders);
            response = restTemplate.postForEntity(url, entity, String.class);
            ApiResponseDTO apiResponseDTO = gson.fromJson((String) response.getBody(), ApiResponseDTO.class);

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
                    }
                }
                if (rowPosition != -1 && colPosition != -1) {
                    break;
                }
            }

//            if(colPosition==2){
//                schedule="test schedule";
//            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("scheduleValue",schedule);
            return new ResponseEntity<>(jsonObject, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
