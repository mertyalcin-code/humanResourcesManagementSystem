package com.example.hrms.business.abstracts;

import com.example.hrms.entities.abstracts.User;

public interface Logger {
    void log(User user, String message,String logType);

}
