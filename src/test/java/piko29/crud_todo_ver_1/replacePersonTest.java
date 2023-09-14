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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class replacePersonTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void replacePerson() throws Exception{
        String requestBody ="{\"name\":\"Rob\",\"position\":\"Tester\"," +
                "\"telephone\":\"+4812333789\",\"email\":\"rob@email.com\"}";

        mvc.perform(MockMvcRequestBuilders
                .put("/persons/{id}",2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andDo(print())
                .andExpect(status().isNoContent());

        mvc.perform(MockMvcRequestBuilders
                .get("/persons/{id}",2))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(2)))
                .andExpect(jsonPath("$.name", Matchers.is("Rob")));
    }
}
