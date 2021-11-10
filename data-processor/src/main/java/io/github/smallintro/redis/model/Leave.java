package io.github.smallintro.redis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@RedisHash("Leave")
public class Leave implements Serializable {
    @Id
    private String empId;
    private float privilegeLeave;
    private float casualLeave;
    private float withoutPay;
    private float compensatoryOff;
}
