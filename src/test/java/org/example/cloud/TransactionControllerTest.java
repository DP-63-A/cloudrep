package org.example.cloud;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.cloud.dto.TransactionDto;
import org.example.cloud.entity.User;
import org.example.cloud.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    private Long userId;

    @BeforeEach
    void setup() {
        User user = User.builder().fullName("Test User").email("test@ex.com").build();
        userId = userRepository.save(user).getId();
    }

    @Test
    void testCreateTransaction() throws Exception {
        TransactionDto dto = TransactionDto.builder()
                .userId(userId)
                .amount(BigDecimal.valueOf(99.99))
                .type("INCOME")
                .build();

        mockMvc.perform(post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(99.99))
                .andExpect(jsonPath("$.type").value("INCOME"));
    }

    @Test
    void testGetAllTransactions() throws Exception {
        mockMvc.perform(get("/api/transactions"))
                .andExpect(status().isOk());
    }
}
