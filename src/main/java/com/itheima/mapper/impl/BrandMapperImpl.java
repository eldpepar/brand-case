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
    private Connection connection = null;
    private PreparedStatement  preparedStatement = null;
    private ResultSet resultSet = null;
    @Override
    public List<Brand> selectAll() {
        String sql = "select * from tb_brand";
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
        try {
            //1.获取连接
            connection = JDBCUtils.getConnection();
            //2.定义sql
            String sql = "INSERT INTO tb_brand(id,brand_name,company_name,ordered,description,status) values(null,?,?,?,?,?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,brand.getBrandName());
            preparedStatement.setString(2,brand.getCompanyName());
            preparedStatement.setInt(3,brand.getOrdered());
            preparedStatement.setString(4,brand.getDescription());
            preparedStatement.setInt(5,brand.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection,preparedStatement);
        }
    }

    @Override
    public void delById(Integer id) {
        try {
            //1.获取连接
            connection = JDBCUtils.getConnection();
            //2.定义sql
            String sql = "DELETE FROM tb_brand WHERE id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            //3.删除数据
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection,preparedStatement);
        }
    }

    @Override
    public void edit(Brand brand) {
        System.out.println("edit-----------"+"\n"+brand);

        try {
            //1.获取连接
            connection = JDBCUtils.getConnection();
            //2.定义sql
            String sql = "UPDATE tb_brand SET brand_name=?,company_name=?,ordered=?,description=?,status=? WHERE id=?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,brand.getBrandName());
            preparedStatement.setString(2,brand.getCompanyName());
            preparedStatement.setInt(3,brand.getOrdered());
            preparedStatement.setString(4,brand.getDescription());
            preparedStatement.setInt(5,brand.getStatus());
            preparedStatement.setInt(6,brand.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection,preparedStatement);
        }
    }

    @Override
    public List<Brand> selectByPage(int pageSize, int pageIndex) {
        String sql = "select * from tb_brand LIMIT ? OFFSET ?";
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
        String sql = "select * from tb_brand WHERE status=? OR company_name=? OR brand_name=? LIMIT ? OFFSET ? ";
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