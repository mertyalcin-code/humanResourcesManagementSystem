package com.example.hrms.business.concrete;

import com.example.hrms.business.abstracts.Logger;
import com.example.hrms.core.entities.User;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

@Service
public class FileLogger extends Logger {


    public FileLogger() {
        try {
            File logBook = new File("logBook.txt");
            if (logBook.createNewFile()) {
                System.out.println("File created: " + logBook.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public void log(User user, String message, String logType) {

        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter("logBook.txt", true));
            writer.newLine();
            writer.write(message + " " + new Timestamp(System.currentTimeMillis()) + " logType: " + logType);
            writer.close();
            System.out.println(message + ": " + new Timestamp(System.currentTimeMillis()));
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void log(String message, String logType) {

        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter("logBook.txt", true));
            writer.newLine();
            writer.write(message + " " + new Timestamp(System.currentTimeMillis()) + " logType: " + logType);
            writer.close();
            System.out.println(message + "; " + new Timestamp(System.currentTimeMillis()));
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


}

