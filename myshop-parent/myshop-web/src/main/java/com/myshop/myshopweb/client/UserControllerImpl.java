package com.myshop.myshopweb.client;

import com.shop.myshopuser.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class UserControllerImpl implements UserController {
    @Override
    public User findById(Integer id) {
        System.out.println("执行了熔断类...");
        return null;
    }
}
