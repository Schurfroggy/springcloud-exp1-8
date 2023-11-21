package com.shop.myshopuser.dao;

import com.shop.myshopuser.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
}
