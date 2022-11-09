package com.itheima.service;

import com.itheima.pojo.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> selectAll();

    void add(Brand brand);

    void delById(Integer id);

    void edit(Brand brand);

    List<Brand> selectByPage(int pageSize, int pageIndex);

    void deleteByIds(int[] ids);

    List<Brand> selectByPageAndConditions(int pageSize, int pageIndex, Brand brand);
}
