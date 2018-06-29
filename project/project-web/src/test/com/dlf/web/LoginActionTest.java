package com.dlf.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginActionTest {

    private MockMvc mvc;

    @Test
    public void loginAjax() throws Exception{
        mvc.perform(MockMvcRequestBuilders.post("/login/loginAjax")).
                andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void logoutAjax() {
    }
}