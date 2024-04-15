package org.example.djxt.controller;

import org.example.djxt.common.GlobalExceptionHandler;
import org.example.djxt.common.PageResult;
import org.example.djxt.domain.SysBranch;
import org.example.djxt.service.SysBranchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BranchControllerTest {

    private MockMvc mockMvc;
    private SysBranchService branchService;

    @BeforeEach
    void setUp() {
        branchService = Mockito.mock(SysBranchService.class);
        mockMvc = MockMvcBuilders
                .standaloneSetup(new BranchController(branchService))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void shouldReturnPagedBranches() throws Exception {
        SysBranch branch = new SysBranch();
        branch.setId(1);
        branch.setPartybranchname("女子学院党委");
        branch.setEstablishtime(LocalDate.of(2021, 11, 11));

        PageResult<SysBranch> page = new PageResult<>(Collections.singletonList(branch), 1, 1, 10);
        Mockito.when(branchService.list(null, null, null, 1, 10)).thenReturn(page);

        mockMvc.perform(get("/api/branches"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("OK"))
                .andExpect(jsonPath("$.data.items[0].partybranchname").value("女子学院党委"));
    }

    @Test
    void shouldCreateBranch() throws Exception {
        SysBranch branch = new SysBranch();
        branch.setId(99);
        branch.setPartybranchname("测试支部");

        Mockito.when(branchService.create(Mockito.any(SysBranch.class))).thenReturn(branch);

        mockMvc.perform(post("/api/branches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"partybranchname\":\"测试支部\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("OK"))
                .andExpect(jsonPath("$.data.id").value(99));
    }
}
