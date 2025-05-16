package com.tconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tconnect.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long>{

}
