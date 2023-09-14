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
public class savePersonTest {
    @Autowired
    private MockMvc mvc;
    @Test
    void savePerson() throws Exception{
        String requestBody = "{\"name\":\"Robert\",\"position\":\"Tester\"," +
            "\"telephone\":\"+48123654789\",\"email\":\"robert@email.com\"}";

        mvc.perform(MockMvcRequestBuilders
                .post("/persons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}
