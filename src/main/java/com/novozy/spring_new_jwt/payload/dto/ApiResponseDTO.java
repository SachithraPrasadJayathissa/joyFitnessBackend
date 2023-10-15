package com.novozy.spring_new_jwt.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDTO {
    private List<List<Integer>> prediction;
}
