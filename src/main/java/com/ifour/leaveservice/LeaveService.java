package com.ifour.leaveservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LeaveService {

    @Autowired
    LeaveRepository leaveRepository;

    Leave leave = new Leave();

    public List<Leave> getLeave() {
        return leaveRepository.findAll();
    }

    public List<Leave> getLeaveByIdIn(List<Integer>id) {
        return leaveRepository.getLeaveByIdIn(id);
    }

    public Leave getLeaveById(Integer id) {
        return leaveRepository.getLeaveById(id);
    }

    public Leave getByName(String name) {
        return leaveRepository.findByName(name);
    }

    @Transactional
    public Leave updateLeave(Leave leave) {
       /* try {
            Leave leave = leaveRepository.getLeaveById(id);
        }catch (IllegalStateException e){
            throw e;
        }

        if (!Objects.equals(leave.getId(), id)) {
            leave.setId(id);
        }

        if (!Objects.equals(leave.getName(), name)) {
            leave.setName(name);
        }
        if (!Objects.equals(leave.getStart(), startDate)) {
            leave.setStart(startDate);
        }

        if (!Objects.equals(leave.getEnd(), endDate)) {
            leave.setEnd(endDate);
        }
        if (!Objects.equals(leave.getTotalLeave(), totalLeave)) {
            leave.setTotalLeave(totalLeave);
        }
        if (!Objects.equals(leave.getStatus(), status)) {
            leave.setStatus(status);
        }
        if (!Objects.equals(leave.getType(), type)) {
            leave.setType(type);
        }*/
        return leaveRepository.save(leave);
    }

    public String deleteLeave(Integer id) {
        if (!Objects.nonNull(leaveRepository.getLeaveById(id))) {
            leaveRepository.deleteById(id);
        }
        return "Leave deleted";
    }
    public Leave addLeaveIn(Leave leave) {
        Optional<Leave> leave1 = Optional.ofNullable(leaveRepository.getLeaveById(leave.getId()));
        if (leave1 != null && leave1.isPresent()) {
            throw new IllegalStateException("Leave with this ID already exists");
        }
        return leaveRepository.save(leave);
    }
}
