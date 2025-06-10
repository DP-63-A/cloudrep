package org.example.cloud;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.cloud.dto.UserRequestDto;
import org.example.cloud.entity.User;
import org.example.cloud.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllUsers() throws Exception {
        userRepository.save(User.builder().fullName("Test User").email("test@example.com").build());

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                /*.andExpect(jsonPath("$", hasSize(1)));*/
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").exists());
    }

    @Test
    void testCreateUser() throws Exception {
        UserRequestDto request = new UserRequestDto();
        request.setFullName("Maebara Keiichi");
        request.setEmail("kei@example.com");

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("Maebara Keiichi"))
                .andExpect(jsonPath("$.email").value("kei@example.com"));
    }
}
