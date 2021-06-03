package com.example.etl;

import com.example.InsertDB.newInsertDB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EtlApplication {

    public static void main(String[] args) {
        SpringApplication.run(EtlApplication.class, args);
        //ReadFile(args[0], args[1]);
        //File2DB(args[0], args[1]);
        //CleanNew.CleanCsv();
        newInsertDB.File2DB();
    }


    //

}