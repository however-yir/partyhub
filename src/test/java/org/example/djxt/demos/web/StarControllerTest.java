package org.example.djxt.demos.web;

import org.example.djxt.common.GlobalExceptionHandler;
import org.example.djxt.common.PageResult;
import org.example.djxt.domain.sysBranchStar;
import org.example.djxt.dto.StarStatsOverview;
import org.example.djxt.service.ISysBranchStarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StarControllerTest {

    private MockMvc mockMvc;
    private ISysBranchStarService starService;

    @BeforeEach
    void setUp() {
        starService = Mockito.mock(ISysBranchStarService.class);
        mockMvc = MockMvcBuilders
                .standaloneSetup(new StarController(starService))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void shouldReturnStarList() throws Exception {
        sysBranchStar item = new sysBranchStar();
        item.setId(1L);
        item.setPartybranchname("女子学院党委");
        item.setProcess(1);
        item.setProcessName("支部自评");

        PageResult<sysBranchStar> page = new PageResult<>(Collections.singletonList(item), 1, 1, 10);
        Mockito.when(starService.list(null, null, null, 1, 10)).thenReturn(page);

        mockMvc.perform(get("/api/stars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("OK"))
                .andExpect(jsonPath("$.data.items[0].processName").value("支部自评"));
    }

    @Test
    void shouldDeleteStar() throws Exception {
        mockMvc.perform(delete("/api/stars/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("deleted"));

        Mockito.verify(starService).delete(1L);
    }

    @Test
    void shouldSubmitStar() throws Exception {
        sysBranchStar submitted = new sysBranchStar();
        submitted.setId(1L);
        submitted.setProcess(2);
        submitted.setProcessName("评分专家评分");
        Mockito.when(starService.submit(1L, "branch-admin")).thenReturn(submitted);

        mockMvc.perform(put("/api/stars/1/submit").param("submitter", "branch-admin"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("submitted"))
                .andExpect(jsonPath("$.data.process").value(2));
    }

    @Test
    void shouldReturnOverviewStats() throws Exception {
        StarStatsOverview overview = new StarStatsOverview();
        overview.setYear("2024");
        overview.setTotalRecords(12);
        overview.setCompletedRecords(9);
        overview.setAvgScore(86.5);
        Mockito.when(starService.statsOverview("2024")).thenReturn(overview);

        mockMvc.perform(get("/api/stars/stats/overview").param("year", "2024"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("OK"))
                .andExpect(jsonPath("$.data.totalRecords").value(12))
                .andExpect(jsonPath("$.data.completedRecords").value(9));
    }
}
