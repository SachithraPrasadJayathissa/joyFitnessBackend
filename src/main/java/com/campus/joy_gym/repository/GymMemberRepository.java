package com.campus.joy_gym.repository;

import com.campus.joy_gym.payload.entity.GymMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GymMemberRepository extends JpaRepository<GymMember, Integer> {

    @Query(value = "SELECT * FROM gymmember where nic = ?1", nativeQuery = true)
    Optional<GymMember> exitsByNIC(String nic);

    @Query(value = "SELECT * FROM gymmember where nic = ?1", nativeQuery = true)
    GymMember getDetails(String nic);

    @Query(value = "SELECT * FROM gymmember where username = ?1" , nativeQuery = true)
    Optional<GymMember> getDetailsByUsername(String username);

}
