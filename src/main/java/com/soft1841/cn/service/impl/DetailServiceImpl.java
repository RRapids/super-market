package com.soft1841.cn.service.impl;

import com.soft1841.cn.dao.DetailDAO;
import com.soft1841.cn.entity.Detail;
import com.soft1841.cn.service.DetailService;
import com.soft1841.cn.utils.DAOFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetailServiceImpl implements DetailService {
    private DetailDAO detailDAO = DAOFactory.getDetailDAOInstance();
    @Override
    public List<Detail> getAllDetails() {
        List<Detail> detailList = new ArrayList<>();
        try {
            //调用底层DAO的查询所有类别的方法，得到一个detailList，薄层封装
            detailList = detailDAO.getAllDetails();
        } catch (SQLException e) {
            //友好处理异常
            System.err.println("查询所有明细出现异常!");
        }
        return detailList;    }

    @Override
    public Detail getDetailById(long id) {
        Detail detail = new Detail();
        try {
            detail = detailDAO.getDetailById(id);
        } catch (SQLException e) {
            System.err.println("查询单个明细出现异常!");
        }
        return detail;
    }

    @Override
    public Long insertDetail(Detail detail) {
        long result = 0;
        try {
            //调用底层DAO的查询新增类别方法，薄层封装，返回自增主键
            result = detailDAO.insertDetail(detail);
        } catch (SQLException e) {
            System.err.println("新增明细出现异常!");
        }
        return result;    }
}
