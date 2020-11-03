package com.in28mins.rest.webservices.restfulwerbservices.dao;

import com.in28mins.rest.webservices.restfulwerbservices.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
}
