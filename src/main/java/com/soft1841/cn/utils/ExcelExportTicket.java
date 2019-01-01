package com.soft1841.cn.utils;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.soft1841.cn.entity.Ticket;

import java.util.List;

public class ExcelExportTicket {
    public static void export(List<Ticket> ticketList) {
        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/ticket.xlsx");
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(7, "小票表");
        // 一次性写出内容，使用默认样式
        writer.write(ticketList);
        // 关闭writer，释放内存
        writer.close();
    }
}
