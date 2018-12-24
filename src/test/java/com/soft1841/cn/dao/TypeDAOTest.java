package com.soft1841.cn.dao;

import com.soft1841.cn.entity.Type;
import com.soft1841.cn.utils.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class TypeDAOTest {
    private TypeDAO typeDAO = DAOFactory.getTypeDAOInstance();

    @Test
    public void insertType() throws SQLException {
        Type type = new Type();
        type.setTypeName("测试类别");
        System.out.println(typeDAO.insertType(type));
    }
}