package com.example.hrms.entities.concrete;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "logs")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int logId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "log_type")
    private String logType;

    @Column(name = "log_time")
    private Timestamp logTime;

    @Column(name = "log")
    private String log;

    public Log(int userId, String logType, Timestamp logTime, String log) {
        this.userId = userId;
        this.logType = logType;
        this.logTime = logTime;
        this.log = log;
    }

    public Log(String logType, Timestamp logTime, String log) {
        this.logType = logType;
        this.logTime = logTime;
        this.log = log;
    }
}
