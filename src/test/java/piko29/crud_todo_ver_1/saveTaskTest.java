package piko29.crud_todo_ver_1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class saveTaskTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void saveTask() throws Exception{
        String requestBody = "{\"title\":\"Testing\",\"description\":\"Testing\"," +
            "\"category\":\"Testing\",\"dateAdded\":\"2023-09-04T16:20:40.2053522\"," +
            "\"dateDeadline\":\"2023-09-01T12:00:00\",\"taskFinished\":false,\"personId\":1}";

        mvc.perform(MockMvcRequestBuilders
                .post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}
