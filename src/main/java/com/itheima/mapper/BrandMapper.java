package com.itheima.mapper;

import com.itheima.pojo.Brand;

import java.util.List;

public interface BrandMapper {
    List<Brand> selectAll();

    void add(Brand brand);

    void delById(Integer id);

    void edit(Brand brand);

    List<Brand> selectByPage(int pageSize, int pageIndex);

    List<Brand> selectByPageAndConditions(int pageSize, int pageIndex, Brand brand);

    void deleteByIds(int[] ids);
}
