package ${basePackage}.${module}.service;

import ${basePackage}.${module}.entity.${entity};

import java.util.List;

/**
 * @author fuqiang
 * @datetime ${date}
 */
public interface ${entity}Service {

    /**
     * 获取列表
     * @return
     */
    List<${entity}> getList();

    /**
    * 根据id获取单条记录
    * @return
    */
    ${entity} getOne(Integer id);

    /**
    * 保存记录
    * @param ${module}
    * @return
    */
    int save(${entity} ${module});

    /**
    * 根据id更新记录
    * @param ${module}
    * @return
    */
    int update(${entity} ${module});

    /**
    * 根据id删除一条记录
    * @param id
    * @return
    */
    int delete(Integer id);
}
