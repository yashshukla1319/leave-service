package com.ifour.leaveservice;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LeaveRepositoryTest {

    @MockBean
    LeaveRepository leaveRepository;

    @Autowired
    LeaveService leaveService;

    Leave leave = new Leave();

    @Test
    void findByName() {
        Mockito.when(leaveRepository.findByName(Mockito.any())).thenReturn(leave);
        Leave output = leaveService.getByName("yash");
        assertEquals(leave,output);
    }

    @Test
    void getLeaveByIdIn() {
        List<Leave> leaveList = new ArrayList<>();
        leaveList.add(leave);
        Mockito.when(leaveRepository.getLeaveByIdIn(Mockito.any())).thenReturn(leaveList);
        List<Leave> output = leaveService.getLeaveByIdIn(List.of(101,102));
        assertEquals(leaveList,output);
    }

    @Test
    void getLeaveById() {
        Mockito.when(leaveRepository.getLeaveById(Mockito.any())).thenReturn(leave);
        Leave output = leaveService.getLeaveById(101);
        assertEquals(leave,output);
    }
}