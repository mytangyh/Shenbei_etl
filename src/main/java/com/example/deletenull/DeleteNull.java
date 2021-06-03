package com.example.deletenull;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.text.csv.*;
import cn.hutool.core.util.CharsetUtil;

import java.io.File;
import java.util.List;

/**
 * @author tangyh
 * @version 1.0
 * @description: TODO删除空字段
 * @date 2021/6/2 20:59
 */
public class DeleteNull {

    public static void deleteNull(){
        String path = "/ordata/clean";
        List<File> files = FileUtil.loopFiles(path);
        for (File file : files) {
            singleDelete(file);
        }

    }
    public static void singleDelete(File file){
        int i=0;
        String wpath="/cleandata";
        CsvReader reader = CsvUtil.getReader();
        CsvData data = reader.read(file);
        List<CsvRow> rows = data.getRows();
        CsvWriter writer = CsvUtil.getWriter(wpath, CharsetUtil.CHARSET_UTF_8);
        for (CsvRow row : rows) {
            if (row.contains("")){
                Console.log(i++);
                continue;
            }
            writer.write(row);
        }
    }
}
