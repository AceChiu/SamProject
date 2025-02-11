package com.ace.entity;

import com.ace.common.TaskStatusEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


/**
 * @author: ACE.CHIU
 * @create: 2025-02-10
 */
@Entity
@Data
public class DailyTask extends BaseEntity {
    private String description;
    private Date date;
    private TaskStatusEnum status;
    private int coin;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", unique = true, nullable = false)
    private Student student;
}
