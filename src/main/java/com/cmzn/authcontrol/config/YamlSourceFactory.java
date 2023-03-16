package com.cmzn.authcontrol.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.util.Properties;


public class YamlSourceFactory extends DefaultPropertySourceFactory {
    @NotNull
    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        String sourceName = name != null ? name : resource.getResource().getFilename();
        if (!resource.getResource().exists()) {
            assert sourceName != null;
            return new PropertiesPropertySource(sourceName, new Properties());
        } else {
            assert sourceName != null;
            if (sourceName.endsWith(".yml") || sourceName.endsWith(".yaml")) {
                Properties propertiesFromYaml = loadYml(resource);
                return new PropertiesPropertySource(sourceName, propertiesFromYaml);
            } else {
                return super.createPropertySource(name, resource);
            }
        }
    }
    private Properties loadYml(EncodedResource resource) throws IOException {
        YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
        factory.setResources(resource.getResource());
        factory.afterPropertiesSet();
        return factory.getObject();
    }
}
