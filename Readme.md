# 设计描述

## 一期业务
### 1.功能点
    1.实现用户的增删改查
    2.实现业务系统挡板
    3.实现接口业务转发
    4.实现页面登陆，暂不验证
### 2.内部接口 
    path: /user/
    1.查询数据库用户   
        @interface: getUserList
    2.添加新用户 
        @interface: addUser
    3.删除用户信息
        @interface: delUser
    4.更新用户数据
        @interface: updateUser

## 二期业务
### 功能点
    1.实现用户权限控制
    2.实现数据库断言
    3.实现接口数据断言
### 内部接口


```
    JAVA 中项目分层的概念
    一、概念理解
        DAO         Data Access Object 数据库访问对象（接口）
            DAO 一般是数据库的具体操作，包括增删改查语句、数据库数据与java模型的映射
        DAOImpl     DAO 的实现类
        entity      数据对象实体，也叫model层
            entity 主要对应数据库表的实体，每个实体对应一张表
        Service     中间层，业务逻辑层（接口）
            service 主要编写具体和业务逻辑
        ServiceImpl Service的实现类
        Servlet     JAVA web 应用，也叫controller层
            servlet用于接口请求并且调用
        Util        工具类
            util 主要是存在于各个项目中高频出现又不好划分到某层中的功能类，如：数据库连接，文件导入导出，获取系统参数等。
        
```






