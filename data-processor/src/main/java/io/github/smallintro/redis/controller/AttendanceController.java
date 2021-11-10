package io.github.smallintro.redis.controller;

import io.github.smallintro.redis.model.Attendance;
import io.github.smallintro.redis.service.AttendanceService;
import io.github.smallintro.redis.service.MessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private MessagePublisher messagePublisher;

    @PostMapping("/save/{employee_id}/{punch_type}")
    public Attendance addData(@PathVariable("employee_id") String empId, @PathVariable("punch_type") String punchType) {
        Attendance attendance = attendanceService.saveAttendance(empId, punchType);
        messagePublisher.publishMessage(String.format("An attendance record %s created for employee %s",
                attendance.getRecordId(), attendance.getEmpId()));
        return attendance;
    }

    @PutMapping("/update/{record_id}/{employee_id}/{punch_type}")
    public Attendance updateData(@PathVariable("record_id") String recordId, @PathVariable("employee_id") String empId, @PathVariable("punch_type") String punchType) {
        Attendance attendance = attendanceService.updateAttendance(recordId, empId, punchType);
        messagePublisher.publishMessage(String.format("An attendance record %s updated for employee %s",
                attendance.getRecordId(), attendance.getEmpId()));
        return attendance;
    }

    @DeleteMapping("/delete/{record_id}")
    public Attendance deleteData(@PathVariable("record_id") String recordId) {
        Attendance attendance = attendanceService.deleteAttendance(recordId);
        messagePublisher.publishMessage(String.format("An attendance record %s deleted for employee %s",
                attendance.getRecordId(), attendance.getEmpId()));
        return attendance;
    }

    @GetMapping("/get/{record_id}")
    public Attendance getData(@PathVariable("record_id") String recordId) {
        return attendanceService.getAttendance(recordId);
    }

    @PostMapping("/save-all")
    public String addAllData(@RequestBody List<Attendance> attendanceList) {
        Map<String, Attendance> dataMap = new HashMap<>();
        attendanceList.forEach(attendance -> dataMap.put(attendance.getRecordId(), attendance));
        return attendanceService.saveAllAttendance(dataMap);
    }

    @GetMapping("/get-all")
    public Map<String, Attendance> getAllData() {
        return attendanceService.getAllAttendance();
    }

}
