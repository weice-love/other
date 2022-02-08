# Spring

### Spring架构图

​	![Spring架构](C:\Users\EDZ\Downloads\Spring架构.jpg)

#### Core Container

1. Core

   核心工具类

2. Bean

   包含访问配置文件，创建和管理bean以及进行Ioc/DI操作的相关的所有类

3. Context

   类似[JNDI](./JNDI.md)注册器的框架式对象访问方法

4. SpEL

   提供强大的表达式语言，用于在运行时查询和操纵对象

#### Data Access/Integration

1. JDBC

   提供了一个JDBC（[Java语言](https://baike.baidu.com/item/Java语言)中用来规范客户端程序如何来访问数据库的[应用程序接口](https://baike.baidu.com/item/应用程序接口/10418844)）抽象层，包含Spring对JDBC数据访问进行封装的所有类

2. ORM

   对象关系映射API，为JPA,Hibernate,Ibatus提供了一个交互层。

3. OXM

   提供了一个对Obeject/XML映射实现的抽象层

4. JMS

   制造和消费消息的特性

5. Transactions

   支持编程和声明式的事务管理

#### Web

1. WebSocket

2. Servlet

   MVC实现，模型代码与表单分离

3. Web

   提供了基础的面向Web的集成特性。例如，多文件上传，使用servlet listeners初始出Ioc容器以及一个面向Web的应用上下文，远程支持的web相关部分。

4. Portlet

   提供了用于Portlet环境和Web-Servlet模块的MVC实现

#### AOP

​	提供了一个符合AOP联盟标准的面向切面编程的实现，可以让我们定义方法拦截器和切点，从而将逻辑代码分开，降低他们之间的耦合性。利用source-level的元数据功能，还可以将各种行为信息 合并到你的代码中

#### Aspects

​	提供了对AspectJ的集成支持

#### Instrumentation

​	提供了class Instrumentation 支持和classloader实现，使得可以在特定的应用服务器上使用 

#### Messaging

​	提供了一系列的用来映射消息到方法的注解。

#### Test

​	支持使用JUnit和TestNG对Spring组件进行测试

​	

