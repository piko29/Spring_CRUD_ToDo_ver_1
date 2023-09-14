package piko29.crud_todo_ver_1;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class updateTaskTest {
    @Autowired
    private MockMvc mvc;
    @Test
    void updateTask() throws Exception{
        String requestBody = "{\"title\":\"Testing\",\"description\":\"Testing\"}";

        mvc.perform(MockMvcRequestBuilders
                        .patch("/tasks/{id}",1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                        .andDo(print())
                        .andExpect(status().isNoContent());

        mvc.perform(MockMvcRequestBuilders
                        .get("/tasks/{id}", 1))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id", Matchers.is(1)))
                        .andExpect(jsonPath("$.title", Matchers.is("Testing")))
                        .andExpect(jsonPath("$.description", Matchers.is("Testing")));
    }
}
