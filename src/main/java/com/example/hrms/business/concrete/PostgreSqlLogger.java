package com.example.hrms.business.concrete;

import com.example.hrms.business.abstracts.Logger;
import com.example.hrms.dataAccess.abstracts.LoggerDao;
import com.example.hrms.entities.abstracts.User;
import com.example.hrms.entities.concrete.Log;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;


@Service
public class PostgreSqlLogger extends Logger {
    LoggerDao loggerDao;

    public PostgreSqlLogger(LoggerDao loggerDao) {
        this.loggerDao = loggerDao;
    }


    public void log(User user, String message,String logType) {

      loggerDao.save(new Log(user.getUserId(),logType,new Timestamp(System.currentTimeMillis()),message));

    }
    public void log(String message,String logType) {

        loggerDao.save(new Log(logType,new Timestamp(System.currentTimeMillis()),message));

    }

}
