package com.roachfu.solar.demo.service.impl;

import com.roachfu.solar.demo.entity.Demo;
import com.roachfu.solar.demo.mapper.DemoMapper;
import com.roachfu.solar.demo.service.DemoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author fuqiang
 * @date 2018-08-03
 */

@Service
public class DemoServiceImpl implements DemoService{

    @Resource
    private DemoMapper demoMapper;

    @Override
    public List<Demo> getList() {
        return demoMapper.selectlist();
    }

    @Override
    public Demo getOne(Integer id) {
        return demoMapper.selectOne(id);
    }

    @Override
    public int save(Demo demo) {
        return demoMapper.insert(demo);
    }

    @Override
    public int update(Demo demo) {
        return demoMapper.update(demo);
    }

    @Override
    public int delete(Integer id) {
        return demoMapper.delete(id);
    }
}
