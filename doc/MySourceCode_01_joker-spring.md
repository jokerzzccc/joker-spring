

# 目录

[toc]

# MySourceCode_01_joker-spring

- time：2022-8-28
- version: 
  - spring 5
  - jdk 8
  - maven 3.5.6
  - 
- 参考链接：
  - small-spring: https://github.com/fuzhengwei/small-spring





# 0、spring 框架的基本信息

## 包信息

1. spring.core.io : 主要用于处理资源加载流









# 1、spring-IOC 

## 概览

###  Bean 的生命周期











## joker-spring-01

### 1、目标

- 定义一个简单的 Spring 容器，用于定义、存放、获取对象。



### 2、设计

- 容器：凡是可以存放数据的具体数据结构实现，都可以称之为容器。
- 用 HashMap 存放 Spring 的 Bean
- Spring Bean  容器的实现，需要，Bean 的定义，注册，获取，三个基本步骤。
  - 定义：BeanDefinition。初始只存放一个简单的 Object  对象。
  - 注册：把 Bean 对象信息存放到 HashMap 中
  - 获取：Bean 的名字就是 key
- ![image-20220926205330071](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20220926205330071.png)



### 3、实现

- 最简单的工厂模板

#### 项目结构：

```sh
└─src
    ├─main
    │  ├─java
    │  │  └─com
    │  │      └─joker
    │  │          └─springframework
    │  │              └─beans
    │  │                      BeanDefinition.java # Bean 定义 对象
    │  │                      BeanFactory.java # Bean 注册工厂
    │  │
    │  └─resources
    └─test
        └─java
            └─com
                └─joker
                    └─springframework
                        └─beans
                                BeanFactoryTest.java
                                UserService.java
```



####  UML 图

（Spring 容器核心）

![joker-spring-01-spring 容器最简单工厂图](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/joker-spring-01-spring 容器最简单工厂图.png)





## joker-spring-02

### 1、目标

- 实现 Bean 容器关于 Bean 的定义、注册、获取。
- 把 Bean 的创建交给容器，而不是在调用的时候 new 一个。
- 还需要考虑单例对象，在对象的二次获取时可以从内存中获取对象





### 2、设计

- 使用 **模板模式** 
- 需要把 BeanDefinition 的 Object 换成 Class 对象
- 整体设计图：
- ![image-20220926211338067](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20220926211338067.png)



### 3、实现

- 核心实现类：`DefaultListableBeanFactory`

#### 项目结构

```sh
└─src
    ├─main
    │  ├─java
    │  │  └─com
    │  │      └─joker
    │  │          └─springframework
    │  │              └─beans
    │  │                  │  BeansException.java # 自定义 Bean 异常
    │  │                  │
    │  │                  └─factory
    │  │                      │  BeanFactory.java
    │  │                      │
    │  │                      ├─config
    │  │                      │      BeanDefinition.java
    │  │                      │      SingletonBeanRegistry.java # 提供获取单例对象的接口
    │  │                      │
    │  │                      └─support
    │  │                              AbstractAutowireCapableBeanFactory.java # 实现 Bean 的实例化操作
    │  │                              AbstractBeanFactory.java # 抽象 Bean 工厂
    │  │                              BeanDefinitionRegistry.java
    │  │                              DefaultListableBeanFactory.java # 核心实现类
    │  │                              DefaultSingletonBeanRegistry.java
    │  │
    │  └─resources
    └─test
        └─java
            └─com
                └─joker
                    └─springframework
                        └─test
                            └─bean
                                    ApiTest.java
                                    UserService.java

```



#### 类图



![joker-spring-02-DefaultListableBeanFactory](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/joker-spring-02-DefaultListableBeanFactory.png)



## joker-spring-03

- 实现目标：对象实例化策略
  - 按照是否包含构造函数实现不同的实例化策略





## joker-spring-04

- 实现目标：注入属性和依赖对象
  - 暂不考虑循环依赖的问题
- ![image-20220928211627185](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20220928211627185.png)



## joker-spring-05

- 实现目标：资源加载器解析文件加载对象（比如：xml)
- 设计：
- ![image-20220929221144295](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20220929221144295.png)







## joker-spring-06

- 实现 Spring  的应用上下文
- 实现目标：把 Bean 对象扩展机制功能和 Spring 框架上下文的包装融合起来。

设计图：

![image-20221001142505935](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20221001142505935.png)



## joker-spring-07

- 实现目标：满足用户可以在 XML 文件中配置 **初始化 和销毁** 的方法。
  - 比如：在 Bean 的初始化过程中，执行一些操作：做一些数据的加载执行，链接注册中心暴露 RPC 接口以及在 Web 程序关闭时执行链接断开，内存销毁等操作。
- 设计：
- ![image-20221002002125955](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20221002002125955.png)

接口与类设计目的：

1. **DisposableBeanAdapter**： 销毁执行时，不希望关注都销毁那些类型的方法，更希望有一个统一的接口进行销毁，所以新增了适配类，做统一处理
   1. 目前，本项目销毁方法的实现方式有两种：实现接口 DisposableBean; 配置信息 destroy-method





## joker-spring-08

- 目标：在 Spring 框架中提供一种能感知容器操作的接口，如果谁实现了这个接口，就能获取接口入参中的各类能力。
- 目的：通过这个感知接口，获得 Spring 框架提供的 BeanFactory, ApplicationContext, BeanClassLoader 等这些能力做一些扩展框架的使用。
- 设计：
- ![image-20221002220150089](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20221002220150089.png)
- 接口 Aware ： 在 Spring 框架中它是一种感知性标记接口，具体的子类定义和实现能感知容器中的相关对象。也就是通过这个桥梁，向具体的实现类中提供容器服务。



## joker-spring-09

- 对象作用域和 FactoryBean 
- 目标：需要把复杂且以代理方式动态变化的对象，注册到 Spring 容器中。 
  - 提供一个能让使用者定义复杂的 Bean 对象的功能点。
- 设计：
- ![image-20221002225125292](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20221002225125292.png)
- 实现包括两部分：
  - 一部分解决单例还是原型对象
  - 另一个处理 FactoryBean 类型对象创建过程中关于获取具体调用对象的 getObject 操作。
- 

## joker-spring-10

- 容器事件和时间监听器
- 目标：以观察者模式，设计和实现 Spring Event  的容器事件和事件监听器功能，最终可以让我们在现有实现的  Spring 框架中可以定义、监听和发布自己的事件信息。
- 设计：
- ![image-20221002231937285](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20221002231937285.png)
- EventObject ：是什么



## Spring-IOC 总结

项目内容包含：1-10

### joker-spring-10 项目结构：

```sh
src
├─main
│  ├─java
│  │  └─com
│  │      └─joker
│  │          └─springframework
│  │              ├─beans
│  │              │  │  BeansException.java
│  │              │  │  PropertyValue.java
│  │              │  │  PropertyValues.java
│  │              │  │
│  │              │  └─factory
│  │              │      │  Aware.java
│  │              │      │  BeanClassLoaderAware.java
│  │              │      │  BeanFactory.java
│  │              │      │  BeanFactoryAware.java
│  │              │      │  BeanNameAware.java
│  │              │      │  ConfigurableListableBeanFactory.java
│  │              │      │  DisposableBean.java
│  │              │      │  FactoryBean.java
│  │              │      │  HierarchicalBeanFactory.java
│  │              │      │  InitializingBean.java
│  │              │      │  ListableBeanFactory.java
│  │              │      │
│  │              │      ├─config
│  │              │      │      AutowireCapableBeanFactory.java
│  │              │      │      BeanDefinition.java
│  │              │      │      BeanFactoryPostProcessor.java
│  │              │      │      BeanPostProcessor.java
│  │              │      │      BeanReference.java
│  │              │      │      ConfigurableBeanFactory.java
│  │              │      │      SingletonBeanRegistry.java
│  │              │      │
│  │              │      ├─support
│  │              │      │      AbstractAutowireCapableBeanFactory.java
│  │              │      │      AbstractBeanDefinitionReader.java
│  │              │      │      AbstractBeanFactory.java
│  │              │      │      BeanDefinitionReader.java
│  │              │      │      BeanDefinitionRegistry.java
│  │              │      │      CglibSubclassingInstantiationStrategy.java
│  │              │      │      DefaultListableBeanFactory.java
│  │              │      │      DefaultSingletonBeanRegistry.java
│  │              │      │      DisposableBeanAdapter.java
│  │              │      │      FactoryBeanRegistrySupport.java
│  │              │      │      InstantiationStrategy.java
│  │              │      │      SimpleInstantiationStrategy.java
│  │              │      │
│  │              │      └─xml
│  │              │              XmlBeanDefinitionReader.java
│  │              │
│  │              ├─context
│  │              │  │  ApplicationContext.java
│  │              │  │  ApplicationContextAware.java
│  │              │  │  ApplicationEvent.java
│  │              │  │  ApplicationEventPublisher.java
│  │              │  │  ApplicationListener.java
│  │              │  │  ConfigurableApplicationContext.java
│  │              │  │
│  │              │  ├─event
│  │              │  │      AbstractApplicationEventMulticaster.java
│  │              │  │      ApplicationContextEvent.java
│  │              │  │      ApplicationEventMulticaster.java
│  │              │  │      ContextClosedEvent.java
│  │              │  │      ContextRefreshedEvent.java
│  │              │  │      SimpleApplicationEventMulticaster.java
│  │              │  │
│  │              │  └─support
│  │              │          AbstractApplicationContext.java
│  │              │          AbstractRefreshableApplicationContext.java
│  │              │          AbstractXmlApplicationContext.java
│  │              │          ApplicationContextAwareProcessor.java
│  │              │          ClassPathXmlApplicationContext.java
│  │              │
│  │              ├─core
│  │              │  └─io
│  │              │          ClassPathResource.java
│  │              │          DefaultResourceReader.java
│  │              │          FileSystemResource.java
│  │              │          Resource.java
│  │              │          ResourceLoader.java
│  │              │          UrlResource.java
│  │              │
│  │              └─util
│  │                      ClassUtils.java
│  │
│  └─resources
│          spring.xml
│
└─test
    └─java
        └─com
            └─joker
                └─springframework
                    └─test
                        │  ApiTest.java
                        │
                        └─event
                                ContextClosedEventListener.java
                                ContextRefreshedEventListener.java
                                CustomEvent.java
                                CustomEventListener.java

```



### Spring Bean 的生命周期

- 参考链接：
  
  - https://juejin.cn/post/6844904065457979405
  
- 核心代码：

  - ```
    AbstractApplicationContext #refresh
    
    AbstractAutowireCapableBeanFactory #createBean
    ```

- 生命周期图，详见语雀。



# 2、spring-AOP 

## 概览：







## joker-spring -11

- 基于 JDK、CGlib 实现 AOP 切面
- 实现目标：
- 解决的问题：
  - 如何给符合规则的方法做代理
  - 以及做完代理方法的案例后，把类的职责拆分出来
- ![image-20221003165122317](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20221003165122317.png)



- 



## joker-spring-12

- 把 AOP 扩展到 Bean 的生命周期
- 到本篇完结，Spring 的 IOC 与 AOP 的核心功能已完结
- 实现目标：完成 AOP 核心功能和 Spring 框架的整合，最终能通过在 Spring 配置的方式完成切面的操作
- 需要解决的问题：
  - 怎么借着 BeanPostProcessor  把动态代理融入到 Bean 的生命周期中，
  - 以及如何组装各项切点、拦截、前置的功能和适配对应的代理器。
- 设计：
- ![image-20221004015404181](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20221004015404181.png)





### **FAQ**

- Q：从Spring的原始注释可以知道这个是一个 Shortcut，什么意思呢？
  - A:  当多次构建同一个 bean 时，可以使用这个 Shortcut，
  - 也就是说不再需要每次推断应该使用哪种方式构造 bean
  - 比如在多次构建同一个 prototype 类型的 bean 时，就可以走此处的 shortcut



## joker-spring-13

- 自动扫描 Bean 对象注册
- 实现目标：在目前的核心逻辑上，填充一些自动化的功能，比如：包的扫描注册、注解配置的使用、占位符属性的填充等。
- 设计：
- ![image-20221004212841008](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20221004212841008.png)





**总结：**

- 



## joker-spring-14

- 通过注解注入属性信息
- 实现目标: 像我没使用 Spring 框架中的 @Autowired ，@Value 一样，完成对属性和对象的注入操作。
- 设计：
- ![image-20221005165147315](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20221005165147315.png)





- 





## joker-spring-15

- 给代理对象设置属性注入。
- 实现目标：把代理对象的创建融入到 Bean 的生命周期中
  - 也就是需要把创建代理对象的逻辑迁移到 Bean 对象执行初始化方法之后，再执行代理对象的创建
- 设计：
- ![image-20221006010401388](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20221006010401388.png)







## Spring-AOP 总结

- 



# APPDIX

## joker-spring-16

- 三级缓存处理循环依赖
- 循环依赖主要分为三种：自身依赖自身，互相循环依赖，多组循环依赖
- Spring  的三级缓存，分别存放了 成品对象、半成品对象（未填充属性值）、代理对象
  - 第三级缓存最主要解决的就是： 循环依赖对 AOP 的处理。
  - Spring 的解决方式，把所有的普通 Bean 初始化都完成，再处理代理对象的初始化。
- 设计：
- ![image-20221006163127197](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20221006163127197.png)

- 实现：（处理循环依赖核心流程）
  - 循环依赖的核心功能实现包括：







## joker-spring-17

- 数据类型转换工厂设计与实现
- 设计思路：
  - 把一个简单的类型转换操作抽象成框架，那么它就需要一个标准的接口，谁实现这个接口，就具备类型转换的具体实现，提供类型转换的能力。那么在有了这样的接口后，还需要类型转换服务的注册、工厂等内容，才可以把类型转换抽象成一个组件服务。
- 设计：
- ![image-20221007124616717](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20221007124616717.png)



```sh
addPropertiesSet

afterPropertiesSet
```



# 总结

# UML 图-Spring 核心



# THE END

