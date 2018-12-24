package com.soft1841.cn.dao;

import com.soft1841.cn.entity.Seller;

import java.sql.SQLException;
import java.util.List;

public interface SellerDAO {
    /**
     * 增
     * @param seller
     * @return
     * @throws SQLException
     */
    int insertSeller(Seller seller)throws SQLException;

    /**
     * 根据id删除
     * @param id
     * @return
     * @throws SQLException
     */
    int deleteSeller(int id) throws SQLException;

    /**
     * 改
     * @param seller
     * @return
     * @throws SQLException
     */
    int updateSeller(Seller seller)throws SQLException;

    /**
     * 查所有收银员
     * @return
     * @throws SQLException
     */
    List<Seller> selectAll()throws SQLException;

    /**
     * 根据id查询收银员
     * @param id
     * @return
     * @throws SQLException
     */
    Seller getSeller(int id)throws SQLException;

    /**
     * 根据工号查询收银员
     * @param account
     * @return
     * @throws SQLException
     */
    Seller getSeller(String account)throws SQLException;
}
