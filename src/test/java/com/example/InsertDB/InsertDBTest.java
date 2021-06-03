package com.example.InsertDB;

import org.junit.jupiter.api.Test;

import static com.example.InsertDB.InsertDB.File2DB;
import static org.junit.jupiter.api.Assertions.*;

class InsertDBTest {

    @Test
    void readFileTest() {
        File2DB("34","34");
    }

}