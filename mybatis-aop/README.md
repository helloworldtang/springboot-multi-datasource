问题1：
要停止Spring boot Datasource的自动配置
spring.datasource.initialize=false
或
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)

否则 会报下面的错：
The dependencies of some of the beans in the application context form a cycle:

   embeddedServletContainerCustomizerBeanPostProcessor
      ↓
   org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration
      ↓
   defaultPointcutAdvisor defined in class path resource [com/tangcheng/datasources/aop/config/TxConfig.class]
      ↓
   transactionManager defined in class path resource [com/tangcheng/datasources/aop/config/TxConfig.class]
┌─────┐
|  routingDataSource defined in class path resource [com/tangcheng/datasources/aop/config/MyBatisConfig.class]
↑     ↓
|  test1DataSource defined in class path resource [com/tangcheng/datasources/aop/config/MyBatisConfig.class]
↑     ↓
|  dataSourceInitializer
└─────┘

相关信息参考：
Finally, one last gotcha that nailed me was that I wanted to ensure that Spring Boot did not enable the default DataSource auto-configuration,
which resulted in it running a schema.sql file (on your classpath, usually under the src/main/resources/ path) to populate the DB schema.
Unfortunately, using this configuration, you can’t just set the datasource.{primary,secondary}.initialize property to false.
Instead you have to use spring.datasource.initialize=false. This makes sense in the end, but it tripped me up for a bit.
So just to reiterate, if you want to turn off the Spring Boot database initialization, set the following in your application.properties:
# Disable Spring DataSource auto-initialization
spring.datasource.initialize=false
https://www.ccampo.me/java/spring/2016/02/13/multi-datasource-spring-boot.html


问题2：
显示开户事务：@EnableTransactionManagement
原理现在还没有搞清楚，怀疑是设置spring.datasource.initialize=false为false后,导致PlatformTransactionManager也没有自动启动

网上见到的错误用法1：使用AutoConfigureAfter来确定Bean之间的依赖
@AutoConfigureAfter only controls the ordering of auto-configuration classes and has no effect on dependency injection.
It also only affects auto-configuration classes so may we'll be doing nothing on your Assembly class.
http://stackoverflow.com/questions/39717450/explanation-of-configuration-autoconfigureafter-with-kotlin

todo:
动态数据源重复代码剥离：@Primary
not aop





