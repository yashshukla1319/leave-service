package com.ifour.leaveservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave,Integer> {

    Leave findByName(String name);

    List<Leave> getLeaveByIdIn(List<Integer> id);

    Leave getLeaveById(Integer id);

}
