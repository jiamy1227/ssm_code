package com.jiamy;

import com.jiamy.pojo.User;
import com.jiamy.service.UserService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @author jiamy
 * @Description :
 * @Create on : 2020/8/22 15:25
 **/

public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        UserService userService = (UserService) context.getBean("userService");

        //BeanFactory factory = new XmlBeanFactory(new ClassPathResource("application.xml"));
        //UserService userService = (UserService) factory.getBean("userService");

        User user = userService.login("bob@example.com", "password");
        System.out.println(user.getName());

    }
}
