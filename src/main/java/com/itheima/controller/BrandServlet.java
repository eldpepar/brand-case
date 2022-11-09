package com.itheima.controller;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {
    private BrandService brandService = new BrandServiceImpl();

    //查找所有品牌
    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询数据
        List<Brand> brands = null;
        brands = brandService.selectAll();

        //转json
        String jsonStr = JSON.toJSONString(brands);

        //写数据
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(jsonStr);
    }

    //分页查询所有数据
    public void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取页数数据
        int size = Integer.parseInt(req.getParameter("size"));
        int index = Integer.parseInt(req.getParameter("index"));

        System.out.println("size="+size+"index="+index);

        //查询数据
        List<Brand> brands = null;
        brands = brandService.selectByPage(size,index);
        System.out.println(brands);

        //转json
        String jsonStr = JSON.toJSONString(brands);

        //写数据
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(jsonStr);
    }

    //分页查询所有数据
    public void selectByPageAndConditions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String _currentPage = req.getParameter("index");
        String _pageSize = req.getParameter("size");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        // 获取查询条件对象
        BufferedReader br = req.getReader();
        String params = br.readLine();//json字符串

        System.out.println(params);
        //转为 Brand
        Brand brand = JSON.parseObject(params, Brand.class);

        //2. 调用service查询
        List<Brand> brands = brandService.selectByPageAndConditions(pageSize,currentPage,brand);

        //2. 转为JSON
        String jsonString = JSON.toJSONString(brands);

        //3. 写数据
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    //添加数据
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 接收品牌数据
        BufferedReader br = req.getReader();
        String params = br.readLine();//json字符串

        System.out.println(params);

        //转为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        //2. 调用service添加
        brandService.add(brand);

        //3. 响应成功的标识
        resp.getWriter().write("success");
    }

    //根据id删除
    public void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        brandService.delById(Integer.valueOf(id));
        //写数据
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write("success");
    }

    //批量删除
    public void deleteByIds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 接收数据  [1,2,3]
        BufferedReader br = req.getReader();
        String params = br.readLine();//json字符串

        //转为 int[]
        int[] ids = JSON.parseObject(params, int[].class);

        //2. 调用service添加
        brandService.deleteByIds(ids);

        //3. 响应成功的标识
        //写数据
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write("success");
    }

    //修改数据
    public void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 接收品牌数据
        BufferedReader br = req.getReader();
        String params = br.readLine();//json字符串

        //转为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        //2. 调用service修改
        brandService.edit(brand);

        //3. 响应成功的标识
        resp.getWriter().write("success");
    }
}