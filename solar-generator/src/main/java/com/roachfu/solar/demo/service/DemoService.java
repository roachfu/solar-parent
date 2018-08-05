package com.roachfu.solar.demo.service;

import com.roachfu.solar.demo.entity.Demo;

import java.util.List;

/**
 * @author fuqiang
 * @datetime 2018-08-03
 */
public interface DemoService {

    /**
     * 获取列表
     * @return
     */
    List<Demo> getList();

    /**
    * 根据id获取单条记录
    * @return
    */
    Demo getOne(Integer id);

    /**
    * 保存记录
    * @param demo
    * @return
    */
    int save(Demo demo);

    /**
    * 根据id更新记录
    * @param demo
    * @return
    */
    int update(Demo demo);

    /**
    * 根据id删除一条记录
    * @param id
    * @return
    */
    int delete(Integer id);
}
