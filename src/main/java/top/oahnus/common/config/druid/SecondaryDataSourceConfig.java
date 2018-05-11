//package top.oahnus.common.config.druid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//import java.util.Map;
//
///**
// * Created by oahnus on 2017/11/28
// * 9:42.
// */
//@Configuration
//@EnableTransactionManagement(proxyTargetClass = true)
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "secondaryEntityManagerFactory",
//        transactionManagerRef = "secondaryTransactionManager",
//        basePackages = {"top.oahnus.repository.secondary"}
//)
//public class SecondaryDataSourceConfig {
//
//    @Autowired
//    private JpaProperties jpaProperties;
//
//    @Autowired
//    @Qualifier("secondaryDataSource")
//    private DataSource secondaryDataSource;
//
//    /**
//     * 我们通过LocalContainerEntityManagerFactoryBean来获取EntityManagerFactory实例
//     * LocalContainerEntityManagerFactoryBean和userEntityManagerFactory方法其中一个注解@Primary即可
//     *
//     * 此配置用于双MySql数据源，如果是mysql，sqlserver作为数据源，配置项要进行修改
//     * 注释 application-*.yaml 中的jpa配置
//     * @Primary 注解要标记在mysql数据源上，以求mysql语法兼容sqlserver
//     *
//     * @return
//     */
//    @Bean(name = "secondaryEntityManagerFactoryBean")
//    //@secondary
//    public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
//        // SqlServer数据源使用此配置
////        LocalContainerEntityManagerFactoryBean em = builder
////                .dataSource(secondaryDataSource)
////                .packages("top.oahnus.domain.secondary") //设置实体类所在位置
////                .persistenceUnit("secondary")
////                .build();
////        Properties properties = new Properties();
////        properties.setProperty("hibernate.physical_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
////        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServer2012Dialect");
////        em.setJpaProperties(properties);
////        return em;
//
//        return builder
//                .dataSource(secondaryDataSource)
//                .properties(getVendorProperties(secondaryDataSource))
//                .packages("top.oahnus.domain.secondary") //设置实体类所在位置
//                .persistenceUnit("secondary")
//                .build();
//        //.getObject();//不要在这里直接获取EntityManagerFactory
//    }
//
//    private Map<String, String> getVendorProperties(DataSource dataSource) {
//        return jpaProperties.getHibernateProperties(dataSource);
//    }
//
//    /**
//     * EntityManagerFactory类似于Hibernate的SessionFactory,mybatis的SqlSessionFactory
//     * 总之,在执行操作之前,我们总要获取一个EntityManager,这就类似于Hibernate的Session,
//     * mybatis的sqlSession.
//     * @param builder
//     * @return
//     */
//    @Bean(name = "secondaryEntityManagerFactory")
//    public EntityManagerFactory secondaryEntityManagerFactory(EntityManagerFactoryBuilder builder) {
//        return this.secondaryEntityManagerFactoryBean(builder).getObject();
//    }
//
//    /**
//     * 配置事物管理器
//     * @return
//     */
//    @Bean(name = "secondaryTransactionManager")
//    public PlatformTransactionManager writeTransactionManager(EntityManagerFactoryBuilder builder) {
//        return new JpaTransactionManager(secondaryEntityManagerFactory(builder));
//    }
//}
