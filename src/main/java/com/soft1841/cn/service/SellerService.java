package com.soft1841.cn.service;

import com.soft1841.cn.entity.Seller;

import java.util.List;

/**
 * 收银员业务逻辑接口
 *
 * @author 袁腾飞 & 汤越
 */
public interface SellerService {

    /**
     * 登录功能
     *
     * @param number
     * @param password
     * @return
     */
    boolean login(String number, String password);

    /**
     * 根据工号查询
     *
     * @param number
     * @return
     */
    Seller getSellerByNumber(String number);

    /**
     * 查询所有
     */
    List<Seller> selectAllSellers();

    /**
     * 根据id删除
     */
    void deleteSellerById(long id);

    /**
     * 新增
     */
    Long insertSeller(Seller seller);

    /**
     * 修改密码
     */
    void updateSeller(Seller seller);

    /**
     * 根据名字查
     * @param keywords
     * @return
     */
    List<Seller> getSellerByName(String keywords);

}
