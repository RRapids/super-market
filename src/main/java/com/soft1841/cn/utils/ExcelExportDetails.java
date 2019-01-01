package com.soft1841.cn.utils;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.soft1841.cn.entity.Detail;

import java.util.List;

public class ExcelExportDetails {
    public static void export(List<Detail> detailList) {
        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/detail.xlsx");
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(7, "明细表");
        // 一次性写出内容，使用默认样式
        writer.write(detailList);
        // 关闭writer，释放内存
        writer.close();
    }
}
