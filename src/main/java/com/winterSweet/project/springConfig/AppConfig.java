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

package com.winterSweet.project.springConfig;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: Butterfly Killer
 * Date: 2017/2/21 上午 12:09
 * <p>
 * Description: MVC上下文环境配置文件
 */
@Configuration
@ComponentScan (basePackages = {"com.winterSweet.project.service", "com.winterSweet.core.file"})
public class AppConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public void dataSource() {
        // 根据JPA类型生成实例
    }

    /**
     * <p>
     * 设置支持的<code>MediaType</code>
     * </p>
     *
     * <p>
     *     设置返回的<code>JsonInclude</code>数据中是否允许特定值,比如<code>NULL</code>,返回到请求端.
     * </p>
     *
     * @see org.springframework.http.MediaType
     * @see com.fasterxml.jackson.annotation.JsonInclude
     *
     */
    @Bean
    public MappingJackson2HttpMessageConverter jacksonMessageConverter() {
        MappingJackson2HttpMessageConverter supportedMediaTypes = new MappingJackson2HttpMessageConverter();
        List<MediaType> mediaTypes = Lists.newArrayList();
        mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        supportedMediaTypes.setSupportedMediaTypes(mediaTypes);
        // 设置返回数据的 Mapper
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        supportedMediaTypes.setObjectMapper(objectMapper);
        return supportedMediaTypes;
    }
}
