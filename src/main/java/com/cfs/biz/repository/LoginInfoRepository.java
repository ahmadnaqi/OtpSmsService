package com.cfs.biz.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cfs.biz.entity.LoginInfo;

@Repository
public interface LoginInfoRepository extends JpaRepository<LoginInfo,Integer> {
	
	Optional<LoginInfo> findByUserNameAndUserPassword(String UserName,String UserPassword);
	Optional<LoginInfo> findByUserName(String UserName);
	Optional<LoginInfo> findByMobileNumberAndUserPassword(String UserPhoneName,String UserPassword);
	
	
}
