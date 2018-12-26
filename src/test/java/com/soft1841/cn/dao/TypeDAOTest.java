package com.soft1841.cn.dao;

import com.soft1841.cn.entity.Type;
import com.soft1841.cn.utils.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * 类型测试类
 * @author 袁腾飞
 * 2018.12.25
 */
public class TypeDAOTest {
    private TypeDAO typeDAO = DAOFactory.getTypeDAOInstance();

    @Test
    public void insertType() throws SQLException {
        Type type = new Type();
        type.setTypeName("测试类别");
        System.out.println(typeDAO.insertType(type));
    }

    @Test
    public void deleteTypeById() throws SQLException{
        typeDAO.deleteTypeById(7);
    }

    @Test
    public void selectAllTypes() throws SQLException{
        List<Type> typeList = typeDAO.selectAllTypes();
        typeList.forEach(entity -> System.out.println(entity));

    }

    @Test
    public void getTypeById() throws SQLException {
        Type entity = typeDAO.getTypeById(1);
        System.out.println(entity);
    }
}