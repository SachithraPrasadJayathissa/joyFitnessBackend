package com.campus.joy_gym.service.impl;


import com.campus.joy_gym.payload.dto.GymMemberDto;
import com.campus.joy_gym.payload.dto.MessageResponse;
import com.campus.joy_gym.payload.dto.WorkoutResponseDTO;
import com.campus.joy_gym.payload.entity.GymMember;
import com.campus.joy_gym.payload.entity.UserInfo;
import com.campus.joy_gym.payload.entity.Workouts;
import com.campus.joy_gym.repository.GymMemberRepository;
import com.campus.joy_gym.repository.UserInfoRepository;
import com.campus.joy_gym.repository.WorkoutsRepository;
import com.campus.joy_gym.service.GymMemberService;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@Service
@CrossOrigin(origins = "*")
public class GymMemberServiceImpl implements GymMemberService {
    @Autowired
    private GymMemberRepository gymMemberRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private WorkoutsRepository workoutsRepository;

    Gson gson = new Gson();

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

    @Override
    public ResponseEntity getUserDetails(GymMember member) {
        System.out.printf(member.getNic());
        GymMemberDto gymMemberDto = new GymMemberDto();
        GymMember details = gymMemberRepository.getDetails(member.getNic());
        gymMemberDto.setAge(details.getAge());
        gymMemberDto.setGender(details.getGender());
        gymMemberDto.setBMI(details.getBMI());
        gymMemberDto.setFitness_goal(details.getFitness_goal());
        gymMemberDto.setHeight(details.getHeight());
        gymMemberDto.setWeight(details.getWeight());
        gymMemberDto.setPhone(details.getPhone());
        gymMemberDto.setWorkout_experience(details.getWorkout_experience());
        gymMemberDto.setWorkout_time(details.getWorkout_time());
        gymMemberDto.setFitness_goal(details.getFitness_goal());


        LOGGER.info("GymMemberController | GymMemberService | getUserDetails | "+ new Gson().toJson(gymMemberDto));
//        return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), "Success",
//                "Successfully get details", gymMemberDto), HttpStatus.OK);

        return new ResponseEntity<>(gymMemberDto,HttpStatus.OK);

    }
    @Override
    public ResponseEntity getMemberSchedule(String username) {
        try{
            Optional<GymMember> byUsername = gymMemberRepository.getDetailsByUsername(username);
            if (byUsername.isPresent()) {
                GymMember gymMember = byUsername.get();
                String schedule = gymMember.getSchedule();
                Workouts workoutsBySchedule = workoutsRepository.getWorkoutsBySchedule(schedule);
                String workouts = workoutsBySchedule.getWorkouts();
                WorkoutResponseDTO s = gson.fromJson(workouts, WorkoutResponseDTO.class);
                JSONArray array = new JSONArray();
                int j = 1;
                for (int i = 0; i < s.getWorkouts().size(); i++) {
                    JSONObject object = new JSONObject();
                    object.put("WorkOut - " + j , s.getWorkouts().get(i));
                    array.add(object);
                    j++;
                }
//                return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), "Success",
//                        "get Member Workout Plans.", array), HttpStatus.OK);
                return new ResponseEntity<>(array,HttpStatus.OK);
            }else {
                return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), "Faield",
                        "Username Not Found", username), HttpStatus.OK);
            }
        }catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), "Error",
                    e.getMessage()), HttpStatus.OK);
        }
    }

}
