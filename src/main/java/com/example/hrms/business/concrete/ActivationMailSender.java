package com.example.hrms.business.concrete;

import com.example.hrms.dataAccess.abstracts.ActivationCodeDao;
import com.example.hrms.entities.abstracts.User;
import com.example.hrms.entities.concrete.ActivationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.stream.IntStream;

@Service
public class ActivationMailSender {

    private ActivationCodeDao activationCodeDao;



    public ActivationMailSender(){}
    @Autowired
    public ActivationMailSender(ActivationCodeDao activationCodeDao) {
        this.activationCodeDao = activationCodeDao;
    }


    public String codeGenerator() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 30;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;

           }


    public void SendActivationMail(User user) {
        String code=codeGenerator();
        System.out.println("localhost:8085/api/users/activate/"+code);
        activationCodeDao.save(new ActivationCode(user.getUserId(),new Timestamp(System.currentTimeMillis()),code)
        );
    }

   /* public void codeGenerator2(){
        int randomNum = (int)(Math.random() * 10000001);  // 0 to 1000000
    }*/
}
