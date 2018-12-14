package com.ease.data.config;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author Wagic
 * @Description: ES客户端配置类，构建Jest对象
 * @Date: 2018-12-14 16:06 by Wagic 创建
 */
@Configuration
public class ElasticsearchConfig implements FactoryBean<JestClient>, InitializingBean, DisposableBean {

    @Value("${spring.jest.elasticsearch.cluster-nodes}")
    private String clusterNodes;

    private static final Logger LOG = LoggerFactory.getLogger(ElasticsearchConfig.class);

    private JestClient client;

    @Override
    public void destroy() throws Exception {
        try {
            if (client!=null){
                client.close();
            }
        }catch (IOException e){
            LOG.error("Error closing ElasticSearch client: ", e);
        }finally {

        }
    }

    @Override
    public JestClient getObject() throws Exception {
        return client;
    }

    @Override
    public Class<?> getObjectType() {
        return JestClient.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        // Construct a new Jest client according to configuration via factory
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder(clusterNodes)
                .multiThreaded(true)
                //Per default this implementation will create no more than 2 concurrent connections per given route
                .defaultMaxTotalConnectionPerRoute(2)
                // and no more 20 connections in total
                .maxTotalConnection(20)
                        .build());
        client = factory.getObject();
    }
}
