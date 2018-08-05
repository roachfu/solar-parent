package com.roachfu.solar.demo.mapper;

import com.roachfu.solar.demo.entity.Demo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * demo数据层
 *
 * @author fuqiang
 * @date 2018-08-03
 */

@Mapper
public interface DemoMapper {

    /**
     * 获取demo的所有列表
     *
     * @return
     */
    List<Demo> selectlist();

    /**
    * 根据id查询一条记录
    *
    * @param id 主键id
    * @return 一条记录
    */
    Demo selectOne(@Param("id") Integer id);

    /**
    * 插入一条新数据
    *
    * @param demo 数据
    * @return 0|1
    */
    int insert(Demo demo);

    /**
    * 更新一条数据
    *
    * @param demo 更新数据
    * @return 0|1
    */
    int update(Demo demo);

    /**
    * 根据id删除一条记录
    *
    * @param id 主键id
    * @return 0|1
    */
    int delete(@Param("id") Integer id);
}
