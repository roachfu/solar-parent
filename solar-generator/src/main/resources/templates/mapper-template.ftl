package ${basePackage}.${module}.mapper;

import ${basePackage}.${module}.entity.${entity};
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ${module}数据层
 *
 * @author fuqiang
 * @date ${date}
 */

@Mapper
public interface ${entity}Mapper {

    /**
     * 获取${module}的所有列表
     *
     * @return
     */
    List<${entity}> selectList();

    /**
    * 根据id查询一条记录
    *
    * @param id 主键id
    * @return 一条记录
    */
    ${entity} selectOne(@Param("id") Integer id);

    /**
    * 插入一条新数据
    *
    * @param ${module} 数据
    * @return 0|1
    */
    int insert(${entity} ${module});

    /**
    * 更新一条数据
    *
    * @param ${module} 更新数据
    * @return 0|1
    */
    int update(${entity} ${module});

    /**
    * 根据id删除一条记录
    *
    * @param id 主键id
    * @return 0|1
    */
    int delete(@Param("id") Integer id);
}
