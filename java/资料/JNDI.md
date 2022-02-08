# JNDI

### 概念

JNDI即Java Naming and Directory Interface（JAVA命名和目录接口），是[SUN公司](https://baike.baidu.com/item/SUN公司)提供的一种标准的Java命名系统接口。

**命名**：将**Java对象**以某个名称的形式**绑定**（binding）到一个**容器环境**（Context）中，以后调用容器环境（Context）的**查找**（lookup）方法又可以查找出某个名称所绑定的Java对象。

**目录**：指将一个对象的所有**属性信息**保存到一个容器环境中。

### 作用

一个应用程序设计的API,为开发人员提供查找和访问各种命名和目录服务的通用、统一的接口。

### 架构

- 应用编程接口（API）
- 服务提供者接口（SPI）

### 组件

- javax.naming

  访问命名服务的类和接口

- javax.naming.directory

  对命名包的扩充，提供了访问[目录服务](https://baike.baidu.com/item/目录服务)的类和接口

- javax.naming.ecevt

  提供了对访问命名和[目录服务](https://baike.baidu.com/item/目录服务)时的事件通知的支持

- javax.naming.ldap

  提供了对LDAP 版本3扩充的操作和控制的支持

- javax.naming.spi

  提供了一个方法，通过[javax.naming](https://baike.baidu.com/item/javax.naming)和有关包动态增加对访问命名和[目录服务](https://baike.baidu.com/item/目录服务)的支持

#### 使用

```
try {
	Context cntxt = new InitialContext();
	DataSource ds = (DataSource) cntxt.lookup("jdbc/dpt"); //API
} catch(NamingException ne) {
	...
}
```



