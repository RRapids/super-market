package com.soft1841.cn.dao;

import com.soft1841.cn.entity.Detail;
import com.soft1841.cn.utils.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;

/**
 * 小票明细测试
 *
 * @author 汤越
 * 2018.12.25
 */
public class DetailDAOTest {
    private DetailDAO detailDAO = DAOFactory.getDetailDAOInstance();

    @Test
    public void insertDetail() throws SQLException {
        Detail detail = new Detail();
        detail.setBarCode("6901265652");
        detail.setTicketID(1);
        detail.setNumber("7");
        System.out.println(detailDAO.insertDetail(detail));
    }

    @Test
    public void getDetailById() throws SQLException {
        System.out.println(detailDAO.getDetailById(2));
    }
}