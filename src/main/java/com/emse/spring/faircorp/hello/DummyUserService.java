package com.emse.spring.faircorp.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DummyUserService implements UserService
{
    @Autowired
    GreetingService greetingService;
    
    String[] names = {"Elodie", "Charles"};
    
    public void greetAll()
    {
        for (String name : names)
        {
            greetingService.greet(name);
        }
    }
}
