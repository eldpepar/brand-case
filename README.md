# brand-case
黑马程序员JavaWeb品牌管理项目

### 项目介绍
在学习JavaWeb的时候，寻找了很多的项目，几乎无一例外的是JSP，只找到了黑马的这个没有采用JSP，于是采用了黑马的品牌管理系统，对其中的mybatis进行了剔除。使用了了非ORM框架的方式完成了本项目。并且完成了课程中并未实现的全部功能。黑马使用的是mybatisplus操作的数据库，这里用的是德鲁伊连接池的方法来进行操作的。由于数据库和前端的缘故，查询部分有一些问题，JavaWeb的核心在于selvlet加之前端很久没有写比较手生就放弃了。

### 运行指南
项目使用IDEA进行编写，IDEA版本为2021.3.3 (Ultimate Edition)。数据库使用的是mysql3.6，jdk使用的是1.8。项目地址如下

[](https://github.com/eldpepar/brand-case)
#### 数据库导入
首先在本地创建一个名为hm-brand的数据库，数据库的字符集选择utf8mb4，如果使用其他的字符集则容易出现乱码问题，尤其是项目中含有emoji表情的情况下。然后执行tb_brand.sql文件导入相关的数据。

#### 修改配置
打开项目/src/main/resources/的jdbc.properties文件，将数据库的地址和数据库名，密码等信息修改为自己的。参考配置如下。如果项目中mysql驱动版本高于6则需要配置serverTimezone参数。如果是mysql8以下，driverClassName的值需要改为com.mysql.jdbc.Driver

```
driverClassName=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/hm-brand?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false
username=root
password=123456
maxWait=3000
```

#### 运行项目
1.如果使用本地tomcat运行，需要将tomcat的端口修改为8090，因为前端接口调用的是8090端口，建议将tomcat的运行目录设置为/，方便进行调试

2.如果使用的是本项目用的maven插件，需要点击鼠标右键，选择run-maven，最后选择tomcat7:run