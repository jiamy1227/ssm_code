package com.jiamy;

import com.jiamy.pojo.User;
import com.jiamy.service.UserService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;

/**
 * @author jiamy
 * @Description :
 * @Create on : 2020/8/22 15:25
 **/
@ComponentScan
@Configuration
public class Test {
    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
//        UserService userService = (UserService) context.getBean("userService");

        //BeanFactory factory = new XmlBeanFactory(new ClassPathResource("application.xml"));
        //UserService userService = (UserService) factory.getBean("userService");

        ApplicationContext context = new AnnotationConfigApplicationContext(Test.class);
        UserService userService = (UserService) context.getBean("userService");
        User user = userService.login("bob@example.com", "password");
        System.out.println(user.getName());

    }
}
