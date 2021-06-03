package com.example.InsertDB;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class newInsertDB {

    public static void File2DB() {
        String repath = "/home/data/8to11/clean";
        int count = 0;
        List<File> orfiles = FileUtil.loopFiles(repath);
        for (File orfile : orfiles) {
            int i = insertDB(orfile);
            count += i;
        }
        Console.log("all Finish!! sum=" + count + new DateTime());
    }

    public static int insertDB(File file) {
        CsvReader reader = CsvUtil.getReader();
        CsvData data = reader.read(file);
        List<CsvRow> rows = data.getRows();
        int count = 1;
        List<Entity> entityList = new ArrayList<>();
//遍历行
        for (CsvRow csvRow : rows) {
            List<String> rawList = csvRow.getRawList();
            Entity entity = new Entity();
            entity.setTableName("acc_base");
            entity.set("card_id", rawList.get(0))
                    .set("in_time", rawList.get(1))
                    .set("in_station", rawList.get(2))
                    .set("out_time", rawList.get(3))
                    .set("out_station", rawList.get(4));
            entityList.add(entity);
            if (count % 50000 == 0 || count == rows.size()) {
                try {
                    Db.use().insert(entityList);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                entityList.clear();
                Console.log(count);
            }
            count++;
        }
        Console.log(file.getName() + " finish! " + count + "---" + DateUtil.date());
        return count;
    }
}



