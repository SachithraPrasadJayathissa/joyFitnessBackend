package com.novozy.spring_new_jwt;


import com.novozy.spring_new_jwt.controller.TrainerController;
import com.novozy.spring_new_jwt.payload.dto.MessageResponse;
import com.novozy.spring_new_jwt.payload.entity.Trainer;
import com.novozy.spring_new_jwt.repository.TrainerRepository;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@MockitoSettings(strictness = Strictness.LENIENT)
public class TrainerControllerTest {

    @Mock
    private TrainerRepository trainerRepository;

    @InjectMocks
    private TrainerController trainerController;

    public TrainerControllerTest() {
    }

    @Test
   public void addTrainer_SuccessfulSave_ShouldReturnSuccessResponse() {
        // Given
        Trainer trainer = new Trainer();
        trainer.setNic("123");
        trainer.setPassword("pass");
        trainer.setUsername("name");
        trainer.setAge(12);
        trainer.setPhone("222222222");

        when(trainerRepository.exitsByNIC(trainer.getNic())).thenReturn(Optional.empty());
        when(trainerRepository.save(any(Trainer.class))).thenReturn(trainer);

        // When
        ResponseEntity<MessageResponse> response = trainerController.addTrainer(trainer);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Successfully Saved Trainer.", response.getBody().getMessage());
    }

}
