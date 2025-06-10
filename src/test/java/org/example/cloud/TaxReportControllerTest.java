package org.example.cloud;

import org.example.cloud.entity.TaxReport;
import org.example.cloud.entity.User;
import org.example.cloud.repository.TaxReportRepository;
import org.example.cloud.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class TaxReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaxReportRepository taxReportRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testGetAllReports() throws Exception {
        User user = userRepository.save(
                User.builder().fullName("Billy Harrington").email("billy@example.com").build()
        );

        TaxReport report = TaxReport.builder()
                .reportYear("2023")
                .status("SUBMITTED")
                .submissionDate(LocalDate.now())
                .user(user)
                .build();

        taxReportRepository.save(report);

        mockMvc.perform(get("/api/tax-reports"))
                .andExpect(status().isOk())
                /*.andExpect(jsonPath("$", hasSize(1)));*/
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").exists());
    }

    @Test
    void testGetReportById_NotFound() throws Exception {
        mockMvc.perform(get("/api/tax-reports/999"))
                .andExpect(status().isNotFound());
    }
}
