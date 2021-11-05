package io.github.smallintro.redis.service;

import io.github.smallintro.redis.model.Attendance;

import java.util.Map;

public interface AttendanceService {
    Attendance saveAttendance(String empId, String punchType);

    Attendance updateAttendance(String recordId, String empId, String punchType);

    Attendance deleteAttendance(String recordId);

    Attendance getAttendance(String recordId);

    String saveAllAttendance(Map<String, Attendance> dataMap);

    Map<String, Attendance> getAllAttendance();
}
