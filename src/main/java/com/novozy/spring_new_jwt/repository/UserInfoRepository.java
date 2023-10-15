package com.novozy.spring_new_jwt.repository;

import com.novozy.spring_new_jwt.payload.entity.GymMember;
import com.novozy.spring_new_jwt.payload.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByName(String username);

}
