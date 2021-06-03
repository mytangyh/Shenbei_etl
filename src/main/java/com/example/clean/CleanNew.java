package com.example.clean;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.text.csv.*;
import cn.hutool.core.util.CharsetUtil;

import java.io.File;
import java.util.List;

public class CleanNew {
//    public static void CleanData(String month) {
//        String mainpath = "/home/data/ods_acc_tf_di/op_month=2020";
//        String twopath = "/op_day=202009";
//        String day = "";
//        int end = 30;
//        if (month.equals("10")) {
//            end = 31;
//        }
//        for (int i = 1; i <= end; i++) {
//            if (i < 10) {
//                day = "0" + i;
//            } else {
//                day = i + "";
//            }
//            String repath = mainpath + "month" + twopath + day;
//            // String repath = "/home/data/1/little/acc_" + i + ".csv";
//            String wrpath = "/home/data/8to11/clean/accclean_" + i + ".csv";
//            // String wrpath = "/home/data/1/clean/accclean_" + i + ".csv";
////        String repath="F:\\testdata\\acc_"+start+".csv";
////        String wrpath="F:\\testdata\\accclean_"+start+".csv";
//            CsvReader reader = CsvUtil.getReader();
////从文件中读取CSV数据
//            CsvData data = reader.read(FileUtil.file(repath));
//            List<CsvRow> rows = data.getRows();
//            //指定路径和编码
//            CsvWriter writer = CsvUtil.getWriter(wrpath, CharsetUtil.CHARSET_UTF_8);
//            int count = 0;
////遍历行
//            for (CsvRow csvRow : rows) {
//                //getRawList返回一个List列表，列表的每一项为CSV中的一个单元格（既逗号分隔部分）
//                if (count % 500000 == 0) {
//                    Console.log(count);
//                }
//                //按行写出
//                String[] split = csvRow.toString().split("\t");
//                writer.write(
//                        new String[]{split[2], split[13], split[14], split[52], split[53]}
//                );
//                count++;
//            }
//            Console.log(i + ".csv  Finish!! count=" + count + new DateTime());
//        }
//    }

    public static void CleanCsv() {
        String repath = "/home/data/ods_acc_tf_di";
        int count = 0;
        List<File> orfiles = FileUtil.loopFiles(repath);
        for (File orfile : orfiles) {
            int i = subFile(orfile);
            count += i;
        }

        Console.log("all Finish!! sum=" + count + new DateTime());
    }

    public static int subFile(File file) {
        //FileReader fileReader = new FileReader(file);
        //List<String> rows = fileReader.readLines();
        CsvReader reader = CsvUtil.getReader();
        CsvData data = reader.read(file);
        List<CsvRow> rows = data.getRows();
        String name = file.getName();
        String[] s = name.split("_");
        String wrpath = "/home/data/8to11/clean/" + s[1] + ".csv";
        CsvWriter writer = CsvUtil.getWriter(wrpath, CharsetUtil.CHARSET_UTF_8);
        int count = 1;
        for (CsvRow row : rows) {
            String[] split = row.toString().split("\t");
            writer.write(
                    new String[]{split[2], split[13], split[14], split[52], split[53]}
            );
            if (count % 50000 == 0) {
                Console.log(count);
            }
            count++;
        }
        Console.log(name + "--finish--count=" + count + new DateTime());
        return count;
    }
}
