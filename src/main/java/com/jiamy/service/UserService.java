package com.jiamy.service;

import com.jiamy.annotation.MetricTime;
import com.jiamy.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jiamy
 * @Description :
 * @Create on : 2020/8/22 15:34
 **/
@Component
public class UserService {

    private MailService mailService;

    public UserService(@Autowired MailService mailService){
        this.mailService = mailService;
    }

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    private List<User> users = new ArrayList<>(
            Arrays.asList(new User(1, "bob@example.com", "password", "Bob"), // bob
                    new User(2, "alice@example.com", "password", "Alice"), // alice
                    new User(3, "tom@example.com", "password", "Tom")) // tom)
            );


    @Transactional(rollbackFor = {RuntimeException.class},propagation = Propagation.REQUIRED)
    public User login(String email, String password) {
        System.out.println(email+password);
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                mailService.sendLoginMail(user);
                return user;
            }
        }
        throw new RuntimeException("login failed.");
    }

    @MetricTime(value ="sleepTime")
    public void sleepTime() throws InterruptedException {
        System.out.println("sleepTime开始...");
        Thread.sleep(2000);
        System.out.println("sleepTime结束...");
    }

    public User getUser(long id) throws Exception {
        return this.users.stream().filter(user -> user.getId() == id).findFirst().orElseThrow(()->new Exception("没找到用户。。"));
    }

    public User register(String email, String password, String name) {
        users.forEach((user) -> {
            if (user.getEmail().equalsIgnoreCase(email)) {
                throw new RuntimeException("email exist.");
            }
        });
        User user = new User(users.stream().mapToLong(u -> u.getId()).max().getAsLong() + 1, email, password, name);
        users.add(user);
        mailService.sendRegistrationMail(user);
        return user;
    }
}
