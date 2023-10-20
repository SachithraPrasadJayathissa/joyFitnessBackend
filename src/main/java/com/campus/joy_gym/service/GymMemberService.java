package com.campus.joy_gym.service;

import com.campus.joy_gym.payload.entity.GymMember;
import org.springframework.http.ResponseEntity;

public interface GymMemberService {

    ResponseEntity addMember(GymMember user);

    ResponseEntity getMembers();

    GymMember getMember(Integer id);

    ResponseEntity updateMember(GymMember user);

    ResponseEntity deleteMember(GymMember member);

    long countUsers();

    ResponseEntity getUserDetails(GymMember member);
    ResponseEntity getMemberSchedule(String username);

}
