package com.campus.joy_gym.controller;

import com.campus.joy_gym.service.GymMemberService;
import com.campus.joy_gym.payload.entity.GymMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/member")

public class GymMemberController {

    @Autowired
    private GymMemberService gymMemberService;

    @PostMapping("/add")
    public ResponseEntity addMember(@RequestBody GymMember member) {
        return gymMemberService.addMember(member);
    }

    @GetMapping
    public ResponseEntity getAllMembers() {
        return gymMemberService.getMembers();
    }


    @GetMapping(value = "/getMember")
    public ResponseEntity getMembersById(@RequestParam String id) {
        GymMember gymMember = new GymMember();
        gymMember.setNic(id);
        return gymMemberService.getUserDetails(gymMember);
    }

    @PostMapping(value = "/update",produces = "application/json")
    public ResponseEntity<?> updateMemberById(@RequestBody GymMember member) {
        return gymMemberService.updateMember(member);

    }


    @DeleteMapping("/delete")
    public ResponseEntity deleteMember(@RequestBody GymMember member){
        return gymMemberService.deleteMember(member);
    }

    @GetMapping("/countMembers")
    public long countUsers() {
        return gymMemberService.countUsers();
    }

    @GetMapping("/getSchedule")
    public ResponseEntity getMemberSchedule(@RequestParam String username) {
        GymMember gymMember = new GymMember();
        gymMember.setUsername(username);
        return gymMemberService.getMemberSchedule(username);
    }
}
