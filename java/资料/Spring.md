# Spring

### Spring架构图

​	![Spring架构](.\Spring\Spring架构.jpg)

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

### Spring核心类介绍

1. **DefaultListableBeanFactory**

是整个bean加载的核心部分，是Spring注册及加载bean的默认实现

​	![DefaultListableBeanFactory层次结构图](.\Spring\DefaultListableBeanFactory层次结构图.jpg)
-	AliasRegistry: 定义对alias的简单增删改等操作
-	SimpleAliasRegistry: 主要使用map作为alias的缓存，并对接口AliasRegistry进行实现
-	SingletonBeanRegistry: 定义对单类的注册及获取
-	BeanFactory: 定义获取bean及bean的各种属性
-	DefaultSingletonBeanRegistry: 对接口SingletonBeanRegistry各函数的实现
-	HierarchicalBeanFactory: 继承BeanFactory,也就是在BeanFactory定义的功能的基础上增加了对parentFactory的支持
-	BeanDefinitionRegistry: 定义对BeanDefinition的各种增删改等操作
-	FactoryBeanRegistrySupport：在DefaultSingletonBeanRegistry基础上增加了对FactoryBean的特殊处理功能
-	ConfigurableBeanFactory：提供配置 Factory 的各种方法
-	ListableBeanFactory：根据各种条件获取 bean 的配置清单
-	AbstractBeanFactory：综合 FactoryBeanRegistrySupport ConfigurableBeanFactory功能
-	AutowireCapableBeanFactory：提供创建 bean 、自动注入、初始化以及应用 bean 的后处理器
-	AbstractAutowireCapableBeanFactory：综合 abstractBeanFactory 并对接口 AutowireCapableBeanFactory 进行实现
-	ConfigurableListableBeanFactory: Beanfactory 配置清单，指定忽略类型及接口等
-	DefaultListableBeanFactory: 综合上面所有功能，要是对 bean 注册后的处理

2. **XmlBeanDefinitionReader**

XML配置文件的读取
-	ResourceLoader：定义资源加载器，主要应用于根据给定的资源文件地址返回对应的Resource
-	BeanDefinitionReader：主要定义资源文件读取并转换为 BeanDefinition 的各个功能
-	EnvironmentCapable：定义获取 Environment 方法
-	DocumentLoader：定义从资源、文件加载到转换为 Document 的功能
-	AbstractBeanDefinitionReader：对EnvironmentCapable, BeanDefinitionReader 类定义的功能进行实现
-	BeanDefinitionDocumentReader：定义读取 Docuemnt 并注册 BeanDefinition 功能
-	BeanDefinitionParserDelegate：定义解析 Element 的各种方法
​	

### Spring标签解析

#### 1.默认标签

- import
- alias
- bean
- beans

**bean解析流程**

1. 委托BeanDefinitionDelegate类的parseBeanDefinitionElement方法进行元素解析，返回BeanDefinitionHolder类型的实例bdHolder，经过这个方法后，bdHolder实例已经包含我们配置文件中配置的各种属性，例如class，name，id，alias之类的属性
2. 对存在默认标签的子节点下的自定义属性进行解析
3. 委托DefinitionReaderUtils的registerBeanDefinition对bdHolder进行注册
4. 发送事件，通知相关监听器，该bena已完成加载

#### 2.自定义标签

拓展Spring自定义标签配置步骤

1. 创建一个需要拓展的组件
2. 定义一个XSD文件描述组件内容
3. 创建一个文件，实现BeanDefinitionParser接口，用来解析XSD文件中的定义和组件定义
4. 创建一个Handler文件，拓展自NamespaceHandlerSupport,目的是将组件注册到Spring容器
5. 编写spring.handlers和spring.schemas





