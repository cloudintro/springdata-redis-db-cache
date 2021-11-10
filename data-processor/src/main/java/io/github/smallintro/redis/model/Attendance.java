package io.github.smallintro.redis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor()
@NoArgsConstructor
@RedisHash("Attendance")
public class Attendance implements Serializable {

    public enum PunchType {
        IN, OUT
    }

    @Id
    private String recordId;
    private String empId;
    private Date punchTime;
    private PunchType punchType;
}
