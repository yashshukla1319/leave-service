package com.ifour.leaveservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    LeaveService leaveService;


    @GetMapping
    @RequestMapping("/getAllLeaves")
    public List<Leave> getLeave(){
        return leaveService.getLeave();
    }

    @GetMapping
    @RequestMapping("/getLeaveByListId")
    public List<Leave> getLeaveByIdIn(@RequestParam("id")String id){
        List<String> ids = Arrays.asList((id.split(",")));
        List<Integer> intIds =  ids.stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        return leaveService.getLeaveByIdIn(intIds);
    }

    @GetMapping
    @RequestMapping("/getLeaveByEmployeeId/{id}")
    public Leave getLeaveById(@PathVariable("id")Integer id){
        return leaveService.getLeaveById(id);
    }

    @GetMapping
    @RequestMapping("/getLeaveByEmployeeName")
    public Leave getByName(@RequestParam("name")String name){
        return leaveService.getByName(name);
    }

    @PutMapping
    @RequestMapping("/updateExistingLeaveById/{id}")
    public Leave updateLeave(@RequestBody Leave leave,@PathVariable("id")Integer id)
                             //@RequestParam(required = false)String name,
                             //@RequestParam(required = false)java.sql.Date startDate,
                             //@RequestParam(required = false)java.sql.Date endDate,
                             //@RequestParam(required = false)Integer totalLeave,
                             //@RequestParam(required = false)String status,
                             //@RequestParam(required = false)String type)
                             {
        return leaveService.updateLeave(leave);
    }

    @DeleteMapping
    @RequestMapping("/deleteLeaveById")
    public String deleteLeave(@RequestParam("id")Integer id){
        return leaveService.deleteLeave(id);
    }

    @PostMapping
    @RequestMapping(value = "/addNewLeave",method = RequestMethod.POST,consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Leave addLeave(@RequestBody Leave leave){
        return leaveService.addLeaveIn(leave);
    }
}
