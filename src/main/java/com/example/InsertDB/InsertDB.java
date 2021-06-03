package com.example.InsertDB;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.text.csv.*;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InsertDB {

    public static void File2DB1(String start, String end) {
        Integer s = Convert.toInt(start);
        Integer e = Convert.toInt(end);
        for (int i = s; i <= e; i++) {
            String repath = "";
            if (i < 10) {
                repath = "/home/data/1/clean/accclean_0" + i + ".csv";
            } else {
                repath = "/home/data/1/clean/accclean_" + i + ".csv";
                // repath="F:\\testdata\\accclean_34.csv";

            }
            CsvReader reader = CsvUtil.getReader();
//从文件中读取CSV数据
            CsvData data = reader.read(FileUtil.file(repath));
            List<CsvRow> rows = data.getRows();
            //指定路径和编码
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
                if (count % 100000 == 0 || count == rows.size()) {
                    try {
                        Db.use().insert(entityList);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                    entityList.clear();
                    Console.log(count + "---" + DateUtil.date());
                }
                count++;
            }
            Console.log("finish " + count + "---" + DateUtil.date());
        }
    }
    public static void File2DB(String start, String end) {
        Integer s = Convert.toInt(start);
        Integer e = Convert.toInt(end);
        for (int i = s; i <= e; i++) {
            String repath = "";
            if (i <10) {
                repath = "/home/data/1/little/acc_0" + i + ".csv";
            }else {
                repath = "/home/data/1/little/acc_" + i + ".csv";
                // repath="F:\\testdata\\accclean_34.csv";

            }
            CsvReader reader = CsvUtil.getReader();
//从文件中读取CSV数据
            CsvData data = reader.read(FileUtil.file(repath));
            List<CsvRow> rows = data.getRows();
            //指定路径和编码
            int count = 1;
            List<Entity> entityList=new ArrayList<>();

//遍历行
            for (CsvRow csvRow : rows) {
                List<String> rawList = csvRow.getRawList();
                Entity entity = new Entity();
                entity.setTableName("acc_base");
                entity.set("card_id",rawList.get(0))
                        .set("in_time",rawList.get(1))
                        .set("in_station",rawList.get(2))
                        .set("out_time",rawList.get(3))
                        .set("out_station",rawList.get(4));
                entityList.add(entity);
                if (count % 100000 == 0) {
                    try {
                        Db.use().insert(entityList);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                    entityList.clear();
                    Console.log(count+"---"+ DateUtil.date());
                }
                count++;
            }
        }


    }
    public static void ReadFile(String start, String end) {
        Integer s = Convert.toInt(start);
        Integer e = Convert.toInt(end);
        for (int i = s; i <= e; i++) {
            String repath = "/home/data/1/little/acc_" + i + ".csv";
            String wrpath = "/home/data/1/clean/accclean_" + i + ".csv";
//        String repath="F:\\testdata\\acc_"+start+".csv";
//        String wrpath="F:\\testdata\\accclean_"+start+".csv";
            CsvReader reader = CsvUtil.getReader();
//从文件中读取CSV数据
            CsvData data = reader.read(FileUtil.file(repath));
            List<CsvRow> rows = data.getRows();
            //指定路径和编码
            CsvWriter writer = CsvUtil.getWriter(wrpath, CharsetUtil.CHARSET_UTF_8);
            int count = 0;
//遍历行
            for (CsvRow csvRow : rows) {
                //getRawList返回一个List列表，列表的每一项为CSV中的一个单元格（既逗号分隔部分）
                if (count % 500000 == 0) {
                    Console.log(count);
                }
                //按行写出
                String[] split = csvRow.toString().split("\t");
                writer.write(
                        new String[]{split[2], split[13], split[14], split[52], split[53]}
                );
                count++;
            }
            Console.log(i + ".csv  Finish!!!!" + new DateTime());
        }
    }

}
