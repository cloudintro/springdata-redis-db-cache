package io.github.smallintro.redis.service.impl;

import io.github.smallintro.redis.exception.NoDataFoundException;
import io.github.smallintro.redis.model.Leave;
import io.github.smallintro.redis.repository.LeaveRepository;
import io.github.smallintro.redis.service.LeaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class LeaveServiceImpl implements LeaveService {

    private final String HASH_KEY = "Leave";

    @Autowired
    private LeaveRepository leaveRepo;

    @Override
    // @CachePut(value = HASH_KEY, key = "#empId")
    public Leave saveLeave(Leave leave) {
        log.info("saveLeave to db");
        leaveRepo.save(leave);
        return leave;
    }

    @Override
    @CachePut(value = HASH_KEY, key = "#empId")
    public Leave updateLeave(String empId, Leave leave) {
        log.info("updateLeave in db");
        leaveRepo.findById(empId).orElseThrow(() -> new NoDataFoundException("No record for employee: " + empId));
        return leaveRepo.save(leave);
    }

    @Override
    @CacheEvict(value = HASH_KEY, key = "#empId")
    public Leave deleteLeave(String empId) {
        log.info("deleteLeave from db");
        Leave leave = leaveRepo.findById(empId).orElseThrow(() -> new NoDataFoundException("No record for employee: " + empId));
        leaveRepo.deleteById(empId);
        return leave;
    }

    @Override
    @Cacheable(value = HASH_KEY, key = "#empId", unless = "#result.withoutPay == 0.0")
    public Leave getLeave(String empId) {
        log.info("getLeave from db");
        return leaveRepo.findById(empId).orElseThrow(() -> new NoDataFoundException("No record for employee: " + empId));
    }

    @Override
    @CacheEvict(value = HASH_KEY, allEntries = true)
    public String deleteAllLeavesFromCache() {
        log.info("deleteAllLeavesFromCache");
        return "Cleared";
    }

    @Override
    public List<Leave> getAllLeaves() {
        log.info("getAllLeaves from db");
        return leaveRepo.findAll();
    }
}
