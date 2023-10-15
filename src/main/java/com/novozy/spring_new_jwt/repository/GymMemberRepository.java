package com.novozy.spring_new_jwt.repository;

import com.novozy.spring_new_jwt.payload.entity.GymMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GymMemberRepository extends JpaRepository<GymMember, Integer> {

    @Query(value = "SELECT * FROM gymmember where nic = ?1", nativeQuery = true)
    Optional<GymMember> exitsByNIC(String nic);

}
