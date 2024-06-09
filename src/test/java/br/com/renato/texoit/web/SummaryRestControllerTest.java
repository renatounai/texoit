package br.com.renato.texoit.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SummaryRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testGetSummary() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/api/summary")
                .contentType(MediaType.APPLICATION_JSON));


        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.max", hasSize(2)))
                .andExpect(jsonPath("$.max[0].producer", is("Matthew Vaughn")))
                .andExpect(jsonPath("$.max[0].previousWin", is(1980)))
                .andExpect(jsonPath("$.max[0].followingWin", is(2002)))
                .andExpect(jsonPath("$.max[0].interval", is(22)))
                .andExpect(jsonPath("$.max[1].producer", is("Matthew Vaughn")))
                .andExpect(jsonPath("$.max[1].previousWin", is(2015)))
                .andExpect(jsonPath("$.max[1].followingWin", is(2037)))
                .andExpect(jsonPath("$.max[1].interval", is(22)))
                .andExpect(jsonPath("$.min", hasSize(2)))
                .andExpect(jsonPath("$.min[0].producer", is("Matthew Vaughn")))
                .andExpect(jsonPath("$.min[0].previousWin", is(2002)))
                .andExpect(jsonPath("$.min[0].followingWin", is(2003)))
                .andExpect(jsonPath("$.min[0].interval", is(1)))
                .andExpect(jsonPath("$.min[1].producer", is("Joel Silver")))
                .andExpect(jsonPath("$.min[1].previousWin", is(1990)))
                .andExpect(jsonPath("$.min[1].followingWin", is(1991)))
                .andExpect(jsonPath("$.min[1].interval", is(1)))
                .andDo(MockMvcResultHandlers.print());
    }

}