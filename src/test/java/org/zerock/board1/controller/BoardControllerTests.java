package org.zerock.board1.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
public class BoardControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webContext; // WebApplicationContext 주입

    @BeforeEach
    public void ready(){
        System.out.println("Ready..................................");
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
    }

    @Test
    public void testList1()throws Exception{
        System.out.println("test1.....................");

        mockMvc.perform(
                MockMvcRequestBuilders.get("/board/list")
                .param("page", "1")
        );
    }

    @Test
    public void testRegisterPost()throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/board/register")
                        .param("title", "Test Title")
                .param("content","Test Content")
                .param("writer","user00")
        );
    }

    @Test
    public void tetReadGET()throws Exception {
        Object boardDTO =
                mockMvc.perform(
                    MockMvcRequestBuilders.get("/board/read")
                    .param("bno", "300")
                ).andReturn().getModelAndView().getModel().get("board");

        System.out.println(boardDTO);
    }



}
