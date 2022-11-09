package com.itheima.service.impl;

import com.itheima.mapper.BrandMapper;
import com.itheima.mapper.impl.BrandMapperImpl;
import com.itheima.pojo.Brand;
import com.itheima.service.BrandService;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    private BrandMapper brandMapper = new BrandMapperImpl();

    @Override
    public List<Brand> selectAll() {
        return brandMapper.selectAll();
    }

    @Override
    public void add(Brand brand) {
        brandMapper.add(brand);
    }

    @Override
    public void delById(Integer id) {
        brandMapper.delById(id);
    }

    @Override
    public void edit(Brand brand) {
        brandMapper.edit(brand);
    }

    @Override
    public List<Brand> selectByPage(int pageSize, int pageIndex) {
        return brandMapper.selectByPage(pageSize,pageIndex);
    }

    @Override
    public void deleteByIds(int[] ids) {
        brandMapper.deleteByIds(ids);
    }

    @Override
    public List<Brand> selectByPageAndConditions(int pageSize, int pageIndex, Brand brand) {
        return brandMapper.selectByPageAndConditions(pageSize, pageIndex, brand);
    }
}