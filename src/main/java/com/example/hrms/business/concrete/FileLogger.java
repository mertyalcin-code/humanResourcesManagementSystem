package com.example.hrms.business.concrete;

import com.example.hrms.business.abstracts.Logger;
import com.example.hrms.entities.abstracts.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.Timestamp;

@Service
public class FileLogger implements Logger {


    public FileLogger() {
        try {
            File logBook = new File("logBook.txt");
            if (logBook.createNewFile()) {
                System.out.println("File created: " + logBook.getName());
            } else {
                System.out.println("File already exists.");
            }
        }   catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    @Override
    public void log(User user, String message,String logType) {

        try {

            BufferedWriter writer =new BufferedWriter(new FileWriter("logBook.txt",true));
            writer.newLine();
            writer.write(message+" "+ new Timestamp(System.currentTimeMillis()));
            writer.close();
            System.out.println(message+": "+ new Timestamp(System.currentTimeMillis()));
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    }

