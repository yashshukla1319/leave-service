package com.ifour.leaveservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.sql.Date;

@WebMvcTest
class LeaveControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    LeaveService leaveService;

    Leave leave = new Leave();

    @Test
    void getLeave() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/leave/getAllLeaves"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getLeaveByIdIn() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/leave/getLeaveByListId?id=101,102"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void getByName() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/leave/getLeaveByEmployeeName?name=yash"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void updateLeave() throws Exception {
        String uri = "/leave/updateExistingLeaveById/101";
        leave.setType("paid");
        leave.setTotalLeave(5);
        leave.setStatus("approved");
        leave.setEnd(Date.valueOf("2021-07-25"));
        leave.setStart((Date.valueOf("2021-07-25")));
        leave.setName("ronak");
        leave.setId(102);

        ObjectMapper objectMapper = new ObjectMapper();
        String inputJson = objectMapper.writeValueAsString(leave);
        this.mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void deleteLeave() throws Exception {
        String uri = "/leave/deleteLeaveById?id=102";
        this.mockMvc.perform(MockMvcRequestBuilders.delete(uri).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void addLeave() throws Exception {
        String uri = "/leave/addNewLeave";
        leave.setId(103);
        leave.setName("amit");
        leave.setStart(Date.valueOf("2021-07-22"));
        leave.setEnd(Date.valueOf("2021-07-28"));
        leave.setTotalLeave(6);
        leave.setStatus("not approved");
        leave.setType("unpaid");

        ObjectMapper objectMapper = new ObjectMapper();
        String inputJson = objectMapper.writeValueAsString(leave);

        this.mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).content(inputJson))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}