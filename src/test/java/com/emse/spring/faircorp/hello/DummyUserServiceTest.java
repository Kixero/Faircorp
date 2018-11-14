package com.emse.spring.faircorp.hello;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@Service
@RunWith(SpringRunner.class)
public class DummyUserServiceTest
{
    @Configuration
    @ComponentScan("com.emse.spring.faircorp.hello")
    public static class DummyUserServiceConfig {}
    
    @Autowired
    public DummyUserService dummyUserService;
    
    @Rule
    public OutputCapture outputCapture = new OutputCapture();
    
    @Test
    public void testGreetingAll()
    {
        dummyUserService.greetAll();
        outputCapture.expect(Matchers.stringContainsInOrder(
                Arrays.asList("Hello, Elodie!", "Hello, Charles!")));
    }
    
}
