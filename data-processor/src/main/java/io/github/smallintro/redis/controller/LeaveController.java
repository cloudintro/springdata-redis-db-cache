package io.github.smallintro.redis.controller;

import io.github.smallintro.redis.model.Leave;
import io.github.smallintro.redis.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @PostMapping("/save")
    public Leave addData(@RequestBody Leave leave) {
        return leaveService.saveLeave(leave);
    }

    @PutMapping("/update")
    public Leave updateData(@RequestBody Leave leave) {
        return leaveService.updateLeave(leave.getEmpId(), leave);
    }

    @DeleteMapping("/delete/{employee_id}")
    public Leave deleteData(@PathVariable("employee_id") String empId) {
        return leaveService.deleteLeave(empId);
    }

    @GetMapping("/get/{employee_id}")
    public Leave getData(@PathVariable("employee_id") String empId) {
        return leaveService.getLeave(empId);
    }

    @GetMapping("/get-all")
    public List<Leave> getAllData() {
        return leaveService.getAllLeaves();
    }

    @DeleteMapping("/clear-cache")
    public String deleteAllData() {
        return leaveService.deleteAllLeavesFromCache();
    }
}
