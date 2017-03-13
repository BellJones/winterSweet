/*
 * Copyright [2017] [Butterfly Killer]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.winterSweet.springConfig;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter;
import org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: Butterfly Killer
 * Date: 2017/2/21 上午 12:12
 * <p>
 * Description: 界面视图配置
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.winterSweet.controller")
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * 注册一种视图解析器
     * @see ViewResolverRegistry
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/view/", ".jsp");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public HttpRequestHandlerAdapter httpRequestHandlerAdapter() {
        return new HttpRequestHandlerAdapter();
    }

    @Bean
    public SimpleControllerHandlerAdapter simpleControllerHandlerAdapter() {
        return new SimpleControllerHandlerAdapter();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
//        builder.dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//        builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));

        MappingJackson2HttpMessageConverter supportedMediaTypes = new MappingJackson2HttpMessageConverter();
        // 设置支持的 MediaType
        List<MediaType> mediaTypes = Lists.newArrayList();
        mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        supportedMediaTypes.setSupportedMediaTypes(mediaTypes);
        // 设置返回数据的 Mapper
        ObjectMapper objectMapper = new ObjectMapper();
        // 设置返回的 JsonInclude 数据中是否允许特定值,比如 NULL ,返回到请求端.
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // TODO 此处定义无效
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        supportedMediaTypes.setObjectMapper(objectMapper);
    }

    /**
     * 文件上传下载
     */
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        // 设置允许上传的文件的最大值，单位是bytes
        commonsMultipartResolver.setMaxUploadSize(20971520); // 20m，默认值为-1，表示无限制
        commonsMultipartResolver.setMaxUploadSizePerFile(5242880); // 5m，默认值为-1，表示无限制
        commonsMultipartResolver.setMaxInMemorySize(1048576); // 1m，默认为 10240 bytes
        // 设置默认的文件编码为UTF-8，此编码必须与用户JSP的编码一致
        commonsMultipartResolver.setDefaultEncoding("UTF-8");
        return commonsMultipartResolver;
    }
}
