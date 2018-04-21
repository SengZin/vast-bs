package pers.vast.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.util.ClassUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * MyBatis基础配置
 *
 * Created by sengzin on 2017/9/13.
 */
@Configuration
@EnableTransactionManagement
public class MybatisConfig implements TransactionManagementConfigurer {
    @Autowired
    private DataSource dataSource = null;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean() {
            static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";
            @Override
            public void setTypeAliasesPackage(String typeAliasesPackage) {
                ResourcePatternResolver resolver = (ResourcePatternResolver) new PathMatchingResourcePatternResolver();
                MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resolver);
                typeAliasesPackage = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                        ClassUtils.convertClassNameToResourcePath(typeAliasesPackage) + "/" + DEFAULT_RESOURCE_PATTERN;

                //将加载多个绝对匹配的所有Resource
                //将首先通过ClassLoader.getResource("META-INF")加载非模式路径部分
                //然后进行遍历模式匹配
                try {
                    List<String> result = new ArrayList<String>();
                    Resource[] resources =  resolver.getResources(typeAliasesPackage);
                    if(resources != null && resources.length > 0){
                        MetadataReader metadataReader = null;
                        for(Resource resource : resources){
                            if(resource.isReadable()){
                                metadataReader =  metadataReaderFactory.getMetadataReader(resource);
                                try {
                                    result.add(Class.forName(metadataReader.getClassMetadata().getClassName()).getPackage().getName());
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    if(result.size() > 0) {
                        StringBuilder pack = new StringBuilder();
                        result.forEach(ele -> pack.append(ele).append("."));
                        pack.setLength(pack.length() - 1);
                        super.setTypeAliasesPackage(pack.toString());
                    }else{
                        System.out.println("参数typeAliasesPackage:"+typeAliasesPackage+"，未找到任何包");
                    }
                    //logger.info("d");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("pers.vast.*.dao.mapper");
        bean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        // 导入 xml 配置
        // bean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));

        // 分页插件
        /*PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);

        //添加插件
        bean.setPlugins(new Interceptor[]{pageHelper});*/

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            //bean.setMapperLocations(resolver.getResources("classpath:/mapper/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
