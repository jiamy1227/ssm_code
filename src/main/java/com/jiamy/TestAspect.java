package com.jiamy;

import com.jiamy.pojo.User;
import com.jiamy.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author jiamy
 * @Description :
 * @Create on : 2020/8/22 15:25
 **/
@ComponentScan
@Configuration
@EnableAspectJAutoProxy
public class TestAspect {
    public static void main(String[] args) throws InterruptedException {
//        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
//        UserService userService = (UserService) context.getBean("userService");

        //BeanFactory factory = new XmlBeanFactory(new ClassPathResource("application.xml"));
        //UserService userService = (UserService) factory.getBean("userService");

        ApplicationContext context = new AnnotationConfigApplicationContext(TestAspect.class);

        UserService userService = (UserService) context.getBean("userService");

        userService.sleepTime();

    }
}
