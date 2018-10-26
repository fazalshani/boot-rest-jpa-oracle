package com.mypack.bootrestjpaoracle.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.mypack.bootrestjpaoracle.entity.Post;

public interface PostDao extends JpaRepository<Post, Integer>{

}
