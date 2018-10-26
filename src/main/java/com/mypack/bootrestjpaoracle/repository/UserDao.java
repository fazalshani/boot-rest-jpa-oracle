package com.mypack.bootrestjpaoracle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mypack.bootrestjpaoracle.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
	//here user is table and integer is primary key
}
