package com.novozy.spring_new_jwt.service;

import com.novozy.spring_new_jwt.payload.entity.GymMember;
import org.springframework.http.ResponseEntity;

public interface GymMemberService {

    ResponseEntity addMember(GymMember user);

    ResponseEntity getMembers();

    GymMember getMember(Integer id);

    ResponseEntity updateMember(GymMember user);

    ResponseEntity deleteMember(GymMember member);

    long countUsers();

}
