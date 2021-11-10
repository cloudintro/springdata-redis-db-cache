package io.github.smallintro.redis.service;

import io.github.smallintro.redis.model.Leave;

import java.util.List;

public interface LeaveService {
    Leave saveLeave(Leave leave);

    Leave updateLeave(String empId, Leave leave);

    Leave deleteLeave(String empId);

    Leave getLeave(String empId);

    List<Leave> getAllLeaves();

    String deleteAllLeavesFromCache();
}
