package com.soft1841.cn.service;

import com.soft1841.cn.entity.Type;
import com.soft1841.cn.utils.ServiceFactory;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TypeServiceTest {
    private TypeService typeService = ServiceFactory.getTypeServiceInstance();

    @Test
    public void selectAllTypes(){
        List<Type> typeList = typeService.selectAllTypes();
        typeList.forEach(type -> System.out.println(type));
    }

}