package com.Andrey.CustomPizza;

import com.Andrey.CustomPizza.controller.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class CustomPizzaApplicationTests {

    private final UserController userController;

    CustomPizzaApplicationTests(UserController userController) {
        this.userController = userController;
    }

    @Test
    public void test() throws Exception{
        
    }

}
