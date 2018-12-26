package com.soft1841.cn.service.impl;

import com.soft1841.cn.dao.TypeDAO;
import com.soft1841.cn.entity.Type;
import com.soft1841.cn.service.TypeService;
import com.soft1841.cn.utils.DAOFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeServiceImpl implements TypeService {
    private TypeDAO typeDAO = DAOFactory.getTypeDAOInstance();

    @Override
    public List<Type> selectAllTypes() {
        List<Type> typeList = new ArrayList<>();
        try {
            //调用底层DAO的查询所有类别的方法，得到一个typeList，薄层封装
            typeList = typeDAO.selectAllTypes();
        } catch (SQLException e) {
            //友好处理异常
            System.err.println("查询所有类别出现异常!");
        }
        return typeList;
    }

    @Override
    public Type getTypeById(long id) {
        Type type = new Type();
        try {
            type = typeDAO.getTypeById(id);
        } catch (SQLException e) {
            System.err.println("查询单个类别出现异常!");
        }
        return type;    }

    @Override
    public Long insertType(Type type) {
        long result = 0;
        try {
            //调用底层DAO的查询新增类别方法，薄层封装，返回自增主键
            result = typeDAO.insertType(type);
        } catch (SQLException e) {
            System.err.println("新增类别出现异常!");
        }
        return result;    }

    @Override
    public void deleteTypeById(long id) {
        try {
            //调用底层DAO的查询删除类别方法，薄层封装
            typeDAO.deleteTypeById(id);
        } catch (SQLException e) {
            System.err.println("删除类别出现异常!");
        }
    }
}
