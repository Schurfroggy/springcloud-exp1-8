package com.example.myshopgateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.myshopgateway.Result.Result;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@EnableZuulProxy
@SpringBootApplication
public class MyshopGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyshopGatewayApplication.class, args);
    }

    /*@Component
    public class MyFilter1 extends ZuulFilter {
        //过滤器类型
        @Override
        public String filterType() {
            return FilterConstants.PRE_TYPE;
        }
        //过滤器执行顺序，数值越大优先级越低
        @Override
        public int filterOrder() {
            return 1;
        }
        //是否让该过滤器生效
        @Override
        public boolean shouldFilter() {
            return true;
        }
        //过滤逻辑代码
        @Override
        public Object run() throws ZuulException {
            System.out.println("执行MyFilter1过滤器");
            return null;
        }
    }*/
    /**
     * 第二个Zuul过滤器MyFilter2
     */
    /*@Component
    public class MyFilter2 extends ZuulFilter {
        //过滤器类型
        @Override
        public String filterType() {
            return FilterConstants.PRE_TYPE;
        }
        //过滤器执行顺序，数值越大优先级越低
        @Override
        public int filterOrder() {
            return 2;
        }
        @Override
        public boolean shouldFilter() {
            return true;
        }
        @Override
        public Object run() throws ZuulException {
            System.out.println("执行MyFilter2过滤器");
            return null;
        }
    }*/



    /*** 权限控制过滤器 */
    /*@Component
    public class AuthFilter extends ZuulFilter {
        @Override
        public String filterType() {
            return FilterConstants.PRE_TYPE;
        }
        @Override
        public int filterOrder() {
            return 0;
        }
        @Override
        public boolean shouldFilter() {
            return true;
        }
        @Override
        public Object run() throws ZuulException {
            //模拟执行异常
            //int i = 10/0;
            //1.获取当前请求的参数：token=user
            RequestContext currentContext = RequestContext.getCurrentContext();
            HttpServletRequest request = currentContext.getRequest();
            HttpServletResponse response = currentContext.getResponse();
            String token = request.getParameter("token");
            if(!"user".equals(token)){
                //不转发微服务，给用户响应
                currentContext.setSendZuulResponse(false);
                //设置401状态码
                response.setStatus(401);
                return null;
            }
            //继续转发
            return null;
        }
    }*/

    /*** 自定义错误类型的Zuul过滤器 */
    /*@Component
    public class MyErrorFilter extends ZuulFilter {
        @Override
        public String filterType() {
            return FilterConstants.ERROR_TYPE;
        }
        @Override
        public int filterOrder() {
            return 0;
        }
        @Override
        public boolean shouldFilter() {
            return true;
        }
        @Override
        public Object run() throws ZuulException {
            System.out.println("进入自定义异常过滤器");
            //1.捕获异常信息
            RequestContext currentContext = RequestContext.getCurrentContext();
            HttpServletResponse response = currentContext.getResponse();
            //ZuulException: 封装其他zuul过滤器执行过程中发现的异常信息
            ZuulException exception = (ZuulException)currentContext.get("throwable");
            //2.把异常信息以json格式输出给前端
            //2.1 构建错误信息
            Result result = new Result(false,"执行失败："+exception.getMessage());
            //2.2 转换Result为json字符串
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String jsonString = objectMapper.writeValueAsString(result);
                //2.3 把json字符串写回给用户
                response.setContentType("text/json;charset=utf-8");
                response.getWriter().write(jsonString);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }*/




}
