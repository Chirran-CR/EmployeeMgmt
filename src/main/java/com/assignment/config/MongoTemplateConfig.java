package com.assignment.config;


import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;

@Configuration
@EnableMongoRepositories(basePackages = "com.assignment.repository")
public class MongoTemplateConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    @Value("${spring.data.mongodb.host.address}")
    private String mongodbHostAddress;

    @Value("${spring.data.mongodb.uri}")
    private String mongodbUriConfig;

    private ConnectionString connectionString;

    @Override
    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(mongodbUriConfig);
    }

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }
    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, getDatabaseName());
    }
//    @Bean
//    public MongoTemplate mongoTemplate(MongoClient mongoClient, MongoMappingContext context) {
//        MappingMongoConverter converter = new MappingMongoConverter(
//                mongoDbFactory(), context);
//        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
//        return new MongoTemplate(mongoClient, getDatabaseName());
//    }
}
