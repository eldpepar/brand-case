package com.itheima.mapper.impl;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrandMapperImpl implements BrandMapper {

    @Override
    public List<Brand> selectAll() {
        Connection connection = null;
        String sql = "select * from tb_brand";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Brand> list = new ArrayList<>();
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Brand brand = new Brand(resultSet.getInt(1),resultSet.getString(2),
                              resultSet.getString(3),resultSet.getInt(4),
                              resultSet.getString(5), resultSet.getInt(6));
                list.add(brand);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection,preparedStatement,resultSet);
        }
        return list;
    }

    @Override
    public void add(Brand brand) {
        System.out.println(brand);
        Connection conn = null;
        PreparedStatement psmt = null;
        try {
            //1.获取连接
            conn = JDBCUtils.getConnection();
            //2.定义sql
            String sql = "INSERT INTO tb_brand(id,brand_name,company_name,ordered,description,status) values(null,?,?,?,?,?)";

            psmt = conn.prepareStatement(sql);
            psmt.setString(1,brand.getBrandName());
            psmt.setString(2,brand.getCompanyName());
            psmt.setInt(3,brand.getOrdered());
            psmt.setString(4,brand.getDescription());
            psmt.setInt(5,brand.getStatus());
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn,psmt);
        }
    }

    @Override
    public void delById(Integer id) {
        Connection conn = null;
        PreparedStatement psmt = null;
        try {
            //1.获取连接
            conn = JDBCUtils.getConnection();
            //2.定义sql
            String sql = "DELETE FROM tb_brand WHERE id=?";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1,id);
            //3.删除数据
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn,psmt);
        }
    }

    @Override
    public void edit(Brand brand) {
        System.out.println("edit-----------"+"\n"+brand);
        Connection conn = null;
        PreparedStatement psmt = null;
        try {
            //1.获取连接
            conn = JDBCUtils.getConnection();
            //2.定义sql
            String sql = "UPDATE tb_brand SET brand_name=?,company_name=?,ordered=?,description=?,status=? WHERE id=?";

            psmt = conn.prepareStatement(sql);
            psmt.setString(1,brand.getBrandName());
            psmt.setString(2,brand.getCompanyName());
            psmt.setInt(3,brand.getOrdered());
            psmt.setString(4,brand.getDescription());
            psmt.setInt(5,brand.getStatus());
            psmt.setInt(6,brand.getId());
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn,psmt);
        }
    }

    @Override
    public List<Brand> selectByPage(int pageSize, int pageIndex) {
        Connection connection = null;
        String sql = "select * from tb_brand LIMIT ? OFFSET ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Brand> list = new ArrayList<>();
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, pageSize);
            preparedStatement.setInt(2, pageSize * (pageIndex - 1));
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Brand brand = new Brand(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getInt(4),
                        resultSet.getString(5), resultSet.getInt(6));
                list.add(brand);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection,preparedStatement,resultSet);
        }
        return list;
    }

    @Override
    public List<Brand> selectByPageAndConditions(int pageSize, int pageIndex, Brand brand) {
        Connection connection = null;
        String sql = "select * from tb_brand WHERE status=? OR company_name=? OR brand_name=? LIMIT ? OFFSET ? ";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Brand> list = new ArrayList<>();
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,brand.getStatus());
            preparedStatement.setString(2,brand.getCompanyName());
            preparedStatement.setString(3,brand.getBrandName());
            preparedStatement.setInt(4, pageSize);
            preparedStatement.setInt(5, pageSize * (pageIndex - 1));

            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                brand = new Brand(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getInt(4),
                        resultSet.getString(5), resultSet.getInt(6));
                list.add(brand);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection,preparedStatement,resultSet);
        }
        return list;
    }

    @Override
    public void deleteByIds(int[] ids) {
        //这里调用了单个删除来实现批量删除，还可以通过拼接SQL字符串的方式实现批量删除
        for (int i = 0; i < ids.length; i++) {
            delById(ids[i]);
        }
    }
}