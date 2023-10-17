package com.campus.joy_gym;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.campus.joy_gym.controller.GymMemberController;
import com.campus.joy_gym.payload.entity.GymMember;
import com.campus.joy_gym.service.GymMemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
public class GymMemberControllerTes {


    @WebMvcTest(GymMemberController.class)
    public class YourControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private GymMemberService gymMemberService;


        @MockBean
        private GymMember gymMember;

        @Test
        public void testGetAllMembers() throws Exception {
            ResponseEntity expectedResponse = ResponseEntity.ok(gymMember);
            when(gymMemberService.getMembers()).thenReturn(expectedResponse);

            mockMvc.perform(get("localhost:8080/member")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());

        }
    }

}
