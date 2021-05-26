package com.example.hrms.business.concrete;

import com.example.hrms.business.abstracts.Logger;
import com.example.hrms.entities.abstracts.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class Loggers {
    FileLogger fileLogger;
    PostgreSqlLogger postgreSqlLogger;

    public Loggers(FileLogger fileLogger, PostgreSqlLogger postgreSqlLogger) {
        this.fileLogger = fileLogger;
        this.postgreSqlLogger = postgreSqlLogger;
    }
    public void log(User user, String message, String logType){
        ArrayList<Logger> loggers =new ArrayList();
        loggers.add(postgreSqlLogger);
        loggers.add(fileLogger);
        for (Logger logger:loggers
             ) {
            logger.log(user,message,logType);
        }
    }
    public void log(String message, String logType){
        ArrayList<Logger> loggers =new ArrayList();
        loggers.add(postgreSqlLogger);
        loggers.add(fileLogger);
        for (Logger logger:loggers
        ) {
            logger.log(message,logType);
        }
    }
}
