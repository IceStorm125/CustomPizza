package com.Andrey.CustomPizza;

import com.Andrey.CustomPizza.api.controller.OrderController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomPizzaApplicationTests {


    public CustomPizzaApplicationTests() {
    }

    @Autowired
    public OrderController userController;

    @Autowired
    public MockMvc mockMvc;

    @Test
    public void testController() throws Exception{
        assertThat(userController).isNotNull();
    }

    @Test
    public void testGetIndexPage() throws Exception{
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Sign in")));
    }

    @Test
    public void loginTest() throws Exception{
        this.mockMvc.perform(get("/orderPizza"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/user/login"));
    }

}
