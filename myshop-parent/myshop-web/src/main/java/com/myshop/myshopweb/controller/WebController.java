package com.myshop.myshopweb.controller;


import com.myshop.myshopweb.client.UserController;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.shop.myshopuser.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@RequestMapping("/web")
@RestController
@Api(description = "购票web核心Api")
public class WebController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private LoadBalancerClient client;

    @Qualifier("com.myshop.myshopweb.client.UserController")
    @Autowired
    private UserController userController;

    private static final Log log = LogFactory.getLog(WebController.class);
    @ApiOperation(value="远程方法：根据用户ID查询用户的方法")
    @RequestMapping(value = "/order",method= RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "fallback")
    public String order(){
        log.info("开始调用order方法...");
        //模拟当前用户
        Integer id = 2;

        /*List<ServiceInstance> instances = discoveryClient.getInstances("myshop-user");
        ServiceInstance serviceInstance = instances.get(0);*/
        //User user = restTemplate.getForObject("http://localhost:9001/user/"+id,User.class);
        //ServiceInstance serviceInstance = client.choose("myshop-user");
        //User user=restTemplate.getForObject("http://myshop-user/user/"+id,User.class);

        User user=userController.findById(id);
        //User user = restTemplate.getForObject("http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/user/"+id,User.class);


        System.out.println(user+"==正在购票...");
        return "购票成功";
    }

    public String fallback(){
        return "服务暂时不可以，发生熔断...";
    }
}
