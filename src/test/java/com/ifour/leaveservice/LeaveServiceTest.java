package com.ifour.leaveservice;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LeaveServiceTest {

    @MockBean
    LeaveRepository leaveRepository;
    @Autowired
    LeaveService leaveService;

    Leave leave = new Leave();

    List<Leave> leaveList = new ArrayList<>();

    @Test
    void getLeave() {
        leaveList.add(leave);
        Mockito.when(leaveRepository.findAll()).thenReturn(leaveList);
        List<Leave> output = leaveRepository.findAll();
        assertEquals(leaveList,output);
    }

    @Test
    void getLeaveByIdIn() {
        leaveList.add(leave);
        Mockito.when(leaveRepository.getLeaveByIdIn((Mockito.any()))).thenReturn(leaveList);
        List<Leave> output = leaveService.getLeaveByIdIn(List.of(101,102));

        assertEquals(leaveList,output);
    }

    @Test
    void getByName() {
        Mockito.when(leaveRepository.findByName(Mockito.any())).thenReturn(leave);
        Leave output = leaveService.getByName("yash");

        assertEquals(leave,output);
    }

    @Test
    void updateLeave() {
        leave.setId(2);
        leave.setStart(Date.valueOf("2021-07-20"));
        leave.setEnd(Date.valueOf("2021-07-25"));
        leave.setTotalLeave(5);
        leave.setStatus("approved");
        leave.setType("paid");
        Mockito.when(leaveRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(leave));
        Mockito.when(leaveRepository.save(Mockito.any())).thenReturn(leave);
        Leave output = leaveService.updateLeave(leave);
        assertEquals(leave,output);
    }

    @Test
    void deleteLeave() {
        LeaveService leaveService1 = Mockito.mock(LeaveService.class);
        Mockito.when(leaveRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(leave));
        Mockito.when(leaveRepository.existsById(1)).thenReturn(true);
        leaveService1.deleteLeave(101);
        Mockito.verify(leaveService1,Mockito.times(1)).deleteLeave(101);
    }

    @Test
    void addLeaveIn() {
        leave.setType("unpaid");
        leave.setTotalLeave(10);
        leave.setEnd(Date.valueOf("2021-07-30"));
        leave.setStart(Date.valueOf("2021-07-20"));
        leave.setStatus("pending");
        leave.setId(102);
        Mockito.when(leaveRepository.save(Mockito.any())).thenReturn(leave);
        Leave output = leaveService.addLeaveIn(leave);
        assertEquals(leave,output);
    }
}