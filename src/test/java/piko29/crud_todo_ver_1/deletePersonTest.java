package piko29.crud_todo_ver_1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class deletePersonTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void deletePerson() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                .delete("/persons/{id}", 2))
                .andDo(print())
                .andExpect(status().isNoContent());

        mvc.perform(MockMvcRequestBuilders
                .get("/persons/{id}", 2))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
