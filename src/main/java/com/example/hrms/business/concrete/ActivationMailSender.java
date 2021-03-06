package com.example.hrms.business.concrete;

import com.example.hrms.core.entities.User;
import com.example.hrms.dataAccess.abstracts.ActivationCodeDao;
import com.example.hrms.entities.ActivationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Random;

@Service
public class ActivationMailSender {

    private ActivationCodeDao activationCodeDao;


    public ActivationMailSender() {
    }

    @Autowired
    public ActivationMailSender(ActivationCodeDao activationCodeDao) {
        this.activationCodeDao = activationCodeDao;
    }


    public String codeGenerator() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 30;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

    }


    public void SendActivationMail(User user) {
        String code = codeGenerator();
        System.out.println("localhost:8085/api/users/activate/" + code);
        activationCodeDao.save(new ActivationCode(user, new Timestamp(System.currentTimeMillis()), code)
        );
    }

   /* public void codeGenerator2(){
        int randomNum = (int)(Math.random() * 10000001);  // 0 to 1000000
    }*/
}
