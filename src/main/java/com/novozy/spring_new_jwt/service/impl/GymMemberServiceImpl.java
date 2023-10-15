package com.novozy.spring_new_jwt.service.impl;


import com.novozy.spring_new_jwt.payload.dto.MessageResponse;
import com.novozy.spring_new_jwt.payload.entity.GymMember;
import com.novozy.spring_new_jwt.payload.entity.UserInfo;
import com.novozy.spring_new_jwt.repository.GymMemberRepository;
import com.novozy.spring_new_jwt.repository.UserInfoRepository;
import com.novozy.spring_new_jwt.service.GymMemberService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GymMemberServiceImpl implements GymMemberService {
    @Autowired
    private GymMemberRepository gymMemberRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserInfoRepository userInfoRepository;


    private static Logger LOGGER = LogManager.getLogger("GymMemberServiceLogger");

    @Override
    public ResponseEntity addMember(GymMember member) {

        Optional<GymMember> gymMember = gymMemberRepository.exitsByNIC(member.getNic());
        if (!gymMember.isPresent()) {
            GymMember save = gymMemberRepository.save(member);
            UserInfo userInfo = new UserInfo();
            userInfo.setPassword(encoder.encode(member.getPassword()));
            userInfo.setName(member.getUsername());
            userInfo.setRoles("ROLE_MEMBER");
            userInfoRepository.save(userInfo);
            LOGGER.info("GymMemberController | GymMemberService | addMember | " + save);
            return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), "Success",
                    "Successfully Saved User.", save), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), "Failed",
                    "User Already Exists : " + member.getNic(), member.getNic()), HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity getMembers() {
        try {
            List<GymMember> getAllMembers = gymMemberRepository.findAll();
            if (!getAllMembers.isEmpty()) {
                LOGGER.info("GymMemberController | GymMemberService | getMembers | " + getAllMembers);
                return new ResponseEntity<>(getAllMembers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new MessageResponse(null), HttpStatus.OK);
            }

        } catch (Exception ex) {
            LOGGER.info("GymMemberController | GymMemberService | getMembers | " + ex);
            return new ResponseEntity<>(new MessageResponse(ex), HttpStatus.OK);
        }
    }

    @Override
    public GymMember getMember(Integer id) { // no need
        return null;
    }

    @Override
    public ResponseEntity updateMember(GymMember user) {
        Optional<GymMember> gymMember = gymMemberRepository.exitsByNIC(user.getNic());
        GymMember oldObj = gymMember.get();
        try {
            if (gymMember.isPresent()) {

                oldObj.setName(user.getName());
                oldObj.setUsername(user.getUsername());
                oldObj.setPassword(user.getPassword());
                oldObj.setPhone(user.getPhone());
                oldObj.setNic(user.getNic());
                oldObj.setAge(user.getAge());
                oldObj.setGender(user.getGender());
                oldObj.setHeight(user.getHeight());
                oldObj.setWeight(user.getWeight());
                oldObj.setBMI(user.getBMI());
                oldObj.setWorkout_time(user.getWorkout_time());
                oldObj.setWorkout_experience(user.getWorkout_experience());
                oldObj.setSchedule(user.getSchedule());

                GymMember save = gymMemberRepository.save(oldObj);
                LOGGER.info("GymMemberController | GymMemberService | addMember | " + save);

                return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), "Success",
                        "Successfully Update gym member.", save), HttpStatus.OK);

            } else {
                return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), "Failed",
                        "There are no user in this ID : " + user.getNic(), user.getNic()), HttpStatus.OK);
            }

        } catch (Exception ex) {
            LOGGER.info("GymMemberController | GymMemberService | addMember | " + ex);
            return new ResponseEntity<>(new MessageResponse(HttpStatus.BAD_REQUEST.value(), "Error",
                    "An error occurred while updating gym member."), HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity deleteMember(GymMember member) {

        Optional<GymMember> gymMember = gymMemberRepository.exitsByNIC(member.getNic());
        GymMember oldObj = gymMember.get();
        try {
            if (gymMember.isPresent()) {
                gymMemberRepository.delete(oldObj);

                LOGGER.info("GymMemberController | GymMemberService | deleteMember | " + oldObj.getNic());

                return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), "Success",
                        "Successfully Delete Gym member.", oldObj.getNic()), HttpStatus.OK);

            } else {
                return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), "Failed",
                        "There are no user in this ID : " + member.getNic(), member.getNic()), HttpStatus.OK);
            }

        } catch (Exception ex) {
            LOGGER.info("GymMemberController | GymMemberService | deleteMember | " + ex);
            return new ResponseEntity<>(new MessageResponse(HttpStatus.BAD_REQUEST.value(), "Error",
                    "An error occurred while updating gym member."), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public long countUsers() {
        try {
            return gymMemberRepository.count();
        } catch (Exception ex) {
            LOGGER.info("GymMemberController | GymMemberService | countMember | " + ex);
            return 0;
        }

    }


}
