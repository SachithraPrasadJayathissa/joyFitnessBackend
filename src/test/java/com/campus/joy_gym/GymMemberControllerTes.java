package com.campus.joy_gym;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.campus.joy_gym.controller.GymMemberController;
import com.campus.joy_gym.payload.entity.GymMember;
import com.campus.joy_gym.repository.GymMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class GymMemberControllerTest {

    private GymMemberController gymMemberController;

    private GymMemberRepository gymMemberRepository;

    @BeforeEach
    public void setUp() {
        gymMemberRepository = mock(GymMemberRepository.class);
        gymMemberController = new GymMemberController();
    }

    @Test
    public void testUpdateMemberSuccess() {
        GymMember user = new GymMember();

        when(gymMemberRepository.exitsByNIC(user.getNic()));

        when(gymMemberRepository.save(any(GymMember.class))).thenReturn(user);

        ResponseEntity<?> responseEntity = gymMemberController.updateMemberById(user);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Success", responseEntity.getBody().toString());
        assertEquals("Successfully Update gym member.", responseEntity.getBody().toString());
    }

    @Test
    public void testUpdateMemberNotFound() {

        GymMember user = new GymMember();


        OngoingStubbing<Optional<GymMember>> optionalOngoingStubbing = when(gymMemberRepository.exitsByNIC(user.getNic()));

        ResponseEntity<?> responseEntity = gymMemberController.updateMemberById(user);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Failed", responseEntity.getBody().toString());
        assertEquals("There are no user in this ID : " + user.getNic(), responseEntity.getBody().toString());
    }

    @Test
    public void testUpdateMemberError() {
        GymMember user = new GymMember();

        OngoingStubbing<Optional<GymMember>> optionalOngoingStubbing = when(gymMemberRepository.exitsByNIC(user.getNic()));

        when(gymMemberRepository.save(any(GymMember.class))).thenThrow(RuntimeException.class);

        ResponseEntity<?> responseEntity = gymMemberController.updateMemberById(user);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Error", responseEntity.getBody().toString());
        assertEquals("An error occurred while updating gym member.", responseEntity.getBody().toString());
    }
}
