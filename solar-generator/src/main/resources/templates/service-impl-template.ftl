package ${basePackage}.${module}.service.impl;

import ${basePackage}.${module}.entity.${entity};
import ${basePackage}.${module}.mapper.${entity}Mapper;
import ${basePackage}.${module}.service.${entity}Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author fuqiang
 * @date ${date}
 */

@Service
public class ${entity}ServiceImpl implements ${entity}Service{

    @Resource
    private ${entity}Mapper ${module}Mapper;

    @Override
    public List<${entity}> getList() {
        return ${module}Mapper.selectList();
    }

    @Override
    public ${entity} getOne(Integer id) {
        return ${module}Mapper.selectOne(id);
    }

    @Override
    public int save(${entity} demo) {
        return ${module}Mapper.insert(demo);
    }

    @Override
    public int update(${entity} demo) {
        return ${module}Mapper.update(demo);
    }

    @Override
    public int delete(Integer id) {
        return ${module}Mapper.delete(id);
    }
}
