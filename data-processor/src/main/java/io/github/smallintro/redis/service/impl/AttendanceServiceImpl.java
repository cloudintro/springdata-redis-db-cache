package io.github.smallintro.redis.service.impl;

import io.github.smallintro.redis.exception.NoDataFoundException;
import io.github.smallintro.redis.model.Attendance;
import io.github.smallintro.redis.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final String HASH_KEY = "Attendance";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private HashOperations<String, String, Attendance> hashOperations;

    private SetOperations<String, Object> SetOperations;

    private ListOperations<String, Object> listOperations;

    @PostConstruct
    private void init() {
        // from template can get the operations for different supported data types for value
        hashOperations = redisTemplate.opsForHash();
        SetOperations = redisTemplate.opsForSet();
        listOperations = redisTemplate.opsForList();
    }

    @Override
    public Attendance saveAttendance(String empId, String punchType) {
        String recordId = UUID.randomUUID().toString();
        Attendance attendance = new Attendance(recordId,
                empId, new Date(), Attendance.PunchType.valueOf(punchType.toUpperCase(Locale.ENGLISH)));
        hashOperations.putIfAbsent(HASH_KEY, recordId, attendance);
        return attendance;
    }

    @Override
    public Attendance updateAttendance(String recordId, String empId, String punchType) {
        Attendance attendance = hashOperations.get(HASH_KEY, recordId);
        if (null != attendance) {
            attendance.setEmpId(empId);
            attendance.setPunchTime(new Date());
            attendance.setPunchType(Attendance.PunchType.valueOf(punchType.toUpperCase(Locale.ENGLISH)));
            hashOperations.put(HASH_KEY, recordId, attendance);
            return attendance;
        } else {
            throw new NoDataFoundException("No attendance found for record_id: " + recordId);
        }
    }

    @Override
    public Attendance deleteAttendance(String recordId) {
        Attendance attendance = hashOperations.get(HASH_KEY, recordId);
        if (null != attendance) {
            hashOperations.delete(HASH_KEY, recordId);
            return attendance;
        } else {
            throw new NoDataFoundException("No attendance found for record_id: " + recordId);
        }
    }

    @Override
    public Attendance getAttendance(String recordId) {
        Attendance attendance = hashOperations.get(HASH_KEY, recordId);
        if (null != attendance) {
            return attendance;
        } else {
            throw new NoDataFoundException("No attendance found for record_id: " + recordId);
        }
    }

    @Override
    public String saveAllAttendance(Map<String, Attendance> dataMap) {
        hashOperations.putAll(HASH_KEY, dataMap);
        return dataMap.size() + " records added";
    }

    @Override
    public Map<String, Attendance> getAllAttendance() {
        return hashOperations.entries(HASH_KEY);
    }

}
