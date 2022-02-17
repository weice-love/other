# Spring

### Spring架构图

​	![Spring架构](.\Spring\Spring架构.jpg)

#### Core Container

1. Core

   核心工具类

2. Beans

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

### Bean的加载

##### 1.转换对应beanName

- 去除FactoryBean的修饰符，也就是如果name=“&aa”，那么就会去除“&”
- 取指定alias所表示的最终beanName

##### 2.尝试从缓存中加载单例

- 单例只会创建一次，后续再获取bean，就直接从缓存中获取
- 缓存中不存在，从singletonFactories中加载（单例bean会存在依赖注入的问题，会提早曝光bean 的ObjectFactory，下一个bean需要使用的时候，直接使用ObjectFactory）。

##### 3.bean的实例化

- 从缓存中获取的是bean的原始状态，需要工厂bean中定义的factory-method方法中返回的bean，具体方法getObjectForBeanInstance

##### 4.原型模式的依赖检查

- 只有再单例情况下才会尝试解决循环依赖（A->B,B->A,A还未创建完，去创建B，B又依赖A，又去创建A，出现循环依赖）。使用isPrototyp CurrentlyinCreation(beanName）判断是否是原型模式。

##### 5.检测parentFactory

- xml配置中没有当前bean的信息，只能从父工厂中去加载当前的bean

##### 6.将存储XML配置文件的GernericBeanDefinition转换成RootBeanDefinition

- 加载Bean都是针对RootBeanDefinition，所以需要转换，如果父类bean不为空，还需要合并父类属性

##### 7.寻找依赖

- 初始化一个bean的时候会初始化这个bean所对应的依赖（@DependOn）

##### 8.针对不同的scope进行bean的创建

- 根据不同的配置进行不同的初始化策略

##### 9.类型转换

- 根据requireType来进行类型转换，可以用户自定义

#### FactoryBean的使用

**bean的实例化方式**

1. <bean>中提供配置<property>

   优点: 简单，易配置

   缺点: 在需要大量配置情况下会比较复杂

2. 利用FactoryBean进行bean的实例化

   优点：可以自定义实例化

   缺点：复杂，需要自己实现FactoryBean代码

```
package rg Springframew k.beans factory ; 
public interface FactoryBean<T> { 
	/*
		返回由 FactoryBean 创建的 bean 实例，如果isSington（）返回 true ，则
		该实例会放到 Spring容器中单实例缓存池中
		
		当<bean>配置文件中的class配置的是FactoryBean类时，
		getBean() 返回的的是getObject()
	*/
	T getObject () throws Exception ;
    /*
    	返回由 FactoryBean 创建的 bean 实例的作用域是singleton还是prototype
    */
	Class<?> getObjectType (); 
	/*
		返回 FactoryBean创建的 bean 类型
	*/
	boolean isSingleton() ; 
}

-- 配置文件
<bean id="XXX" class="XXXFactoryBean " <属性>="xxx" /＞

-- 使用

getBean(XXX) 获取bean实例
getBean(&XXX)获取factorybean实例


```



#### 缓存中获取单例bean

```
protected Object getSingleton(String beanName, boolean allowEarlyReference) {
		// Quick check for existing instance without full singleton lock
		Object singletonObject = this.singletonObjects.get(beanName);
		if (singletonObject == null && isSingletonCurrentlyInCreation(beanName)) {
			singletonObject = this.earlySingletonObjects.get(beanName);
			if (singletonObject == null && allowEarlyReference) {
				synchronized (this.singletonObjects) {
					// Consistent creation of early reference within full singleton lock
					singletonObject = this.singletonObjects.get(beanName);
					if (singletonObject == null) {
						singletonObject = this.earlySingletonObjects.get(beanName);
						if (singletonObject == null) {
							ObjectFactory<?> singletonFactory = this.singletonFactories.get(beanName);
							if (singletonFactory != null) {
								singletonObject = singletonFactory.getObject();
								this.earlySingletonObjects.put(beanName, singletonObject);
								this.singletonFactories.remove(beanName);
							}
						}
					}
				}
			}
		}
		return singletonObject;
	}
	
	
	
Map<String, Object> singletonObjects
	用于保存beanName和bean 的关系
Map<String, Object> earlySingletonObjects
	也是用于保存beanName和bean 的关系，当一个bean放入这里，当bean还在创建过程中，就可以通过getBean获取		到了
Map<String, ObjectFactory<?>> singletonFactories
	用于保存beanName和创建bean 的工厂之间的关系
Set<String> registeredSingletons
	用于保存已注册的bena
```



#### 从bean的实例中获取对象

getObjectForBeanInstance

1. 对FactoryBean 的正确性的验证
2. 对非FactoryBean不做任何处理
3. 对bean进行转换
4. 将从Factory中解析bena的工作委托给getObjectFromFactoryBean

```
protected Object getObjectForBeanInstance(
			Object beanInstance, String name, String beanName, @Nullable RootBeanDefinition mbd) {

		// 是否是工厂bean， 以&打头，如果是，则直接返回工厂bean
		if (BeanFactoryUtils.isFactoryDereference(name)) {
			if (beanInstance instanceof NullBean) {
				return beanInstance;
			}
			// 不是FactoryBean类型直接抛错
			if (!(beanInstance instanceof FactoryBean)) {
				throw new BeanIsNotAFactoryException(beanName, beanInstance.getClass());
			}
			if (mbd != null) {
				mbd.isFactoryBean = true;
			}
			return beanInstance;
		}

		// Now we have the bean instance, which may be a normal bean or a FactoryBean.
		// If it's a FactoryBean, we use it to create a bean instance, unless the
		// caller actually wants a reference to the factory.
		// 如果不是工厂bean，则直接返回。
		if (!(beanInstance instanceof FactoryBean)) {
			return beanInstance;
		}

		Object object = null;
		if (mbd != null) {
			mbd.isFactoryBean = true;
		}
		else {
			object = getCachedObjectForFactoryBean(beanName);
		}
		if (object == null) {
			// Return bean instance from factory.
			FactoryBean<?> factory = (FactoryBean<?>) beanInstance;
			// Caches object obtained from FactoryBean if it is a singleton.
			if (mbd == null && containsBeanDefinition(beanName)) {
				mbd = getMergedLocalBeanDefinition(beanName);
			}
			
			// 是否是用户定义的而不是应用程序本身定义的
			boolean synthetic = (mbd != null && mbd.isSynthetic());
			object = getObjectFromFactoryBean(factory, beanName, !synthetic);
		}
		return object;
	}
```

#### 获取单例

**获取步骤**

1. 检查缓存是否已经加载过

2. 若没有加载，则记录beanName的正在加载状态

3. 加载单例前记录加载状态（this.**singletonsCurrentlyInCreation**.add(beanName)）,方便检测循环依赖

4. 通过调用参数传入ObjectFactory的个体Object方法实例化bean

5. 加载单例后的处理方法调用（this.**singletonsCurrentlyInCreation**.remove(beanName)）

6. 将结果记录至缓存并删除加载bean过程中所记录的各种辅助状态

   ```
   		synchronized (this.singletonObjects) {
   			this.singletonObjects.put(beanName, singletonObject);
   			this.singletonFactories.remove(beanName);
   			this.earlySingletonObjects.remove(beanName);
   			this.registeredSingletons.add(beanName);
   		}
   ```

7. 返回处理结果

```
public Object getSingleton(String beanName, ObjectFactory<?> singletonFactory) {
		Assert.notNull(beanName, "Bean name must not be null");
		// 同步
		synchronized (this.singletonObjects) {
			// 检查是否已经加载
			Object singletonObject = this.singletonObjects.get(beanName);
			if (singletonObject == null) {
				if (this.singletonsCurrentlyInDestruction) {
					throw new BeanCreationNotAllowedException(beanName,
							"Singleton bean creation not allowed while singletons of this factory are in destruction " +
							"(Do not request a bean from a BeanFactory in a destroy method implementation!)");
				}
				if (logger.isDebugEnabled()) {
					logger.debug("Creating shared instance of singleton bean '" + beanName + "'");
				}
				beforeSingletonCreation(beanName);
				boolean newSingleton = false;
				boolean recordSuppressedExceptions = (this.suppressedExceptions == null);
				if (recordSuppressedExceptions) {
					this.suppressedExceptions = new LinkedHashSet<>();
				}
				try {
					// 初始化bean
					singletonObject = singletonFactory.getObject();
					newSingleton = true;
				}
				catch (IllegalStateException ex) {
					// Has the singleton object implicitly appeared in the meantime ->
					// if yes, proceed with it since the exception indicates that state.
					singletonObject = this.singletonObjects.get(beanName);
					if (singletonObject == null) {
						throw ex;
					}
				}
				catch (BeanCreationException ex) {
					if (recordSuppressedExceptions) {
						for (Exception suppressedException : this.suppressedExceptions) {
							ex.addRelatedCause(suppressedException);
						}
					}
					throw ex;
				}
				finally {
					if (recordSuppressedExceptions) {
						this.suppressedExceptions = null;
					}
					afterSingletonCreation(beanName);
				}
				if (newSingleton) {
					addSingleton(beanName, singletonObject);
				}
			}
			return singletonObject;
		}
	}
```

#### 创建bean
