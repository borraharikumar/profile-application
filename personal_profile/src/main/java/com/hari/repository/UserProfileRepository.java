package com.hari.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hari.entities.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {

}
