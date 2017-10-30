package com.crossent.monitoring.portal.common.config;


import com.crossent.monitoring.portal.common.lib.elasticsearch.ElasticsearchProperties;
import com.crossent.monitoring.portal.common.lib.elasticsearch.ElasticsearchTemplate;
import com.crossent.monitoring.portal.common.lib.elasticsearch.TransportClientFactory;
import org.elasticsearch.client.transport.TransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;


@Configuration
@EnableConfigurationProperties(ElasticsearchProperties.class)
public class ElasticsearchConfiguration {
    private static Logger logger = LoggerFactory.getLogger(ElasticsearchConfiguration.class);


    @Bean
    public TransportClientFactory transportClientFactory(final ElasticsearchProperties elasticsearchProperties) throws Exception{
        return new TransportClientFactory(elasticsearchProperties);
    }



    public TransportClient transportClient(TransportClientFactory transportClientFactory) throws Exception{

        return transportClientFactory.getObject();

    }


    @Bean
    public ElasticsearchTemplate elasticsearchTemplate(final TransportClient transportClient) throws Exception{
        return new ElasticsearchTemplate(transportClient);
    }


}
