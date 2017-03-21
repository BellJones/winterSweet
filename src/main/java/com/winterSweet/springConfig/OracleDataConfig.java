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

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA
 * User: Butterfly Killer
 * Date: 2017/2/20 下午 11:34
 * <p>
 * Description: 数据库连接配置
 * <p>
 * 这里只列出常用的简单配置，详细配置，请访问
 * http://www.cnblogs.com/SummerinShire/p/5828888.html
 * 获取
 * </p>
 */
@Configuration
@Profile("oracle")
public class OracleDataConfig {

    @Bean("dataSource")
    public DataSource dataSource() throws SQLException, IOException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("core.jdbc.OracleDriver");
        dataSource.setUrl("jdbc:core:thin:@localhost:1521:ORCL/winterSweet");
        dataSource.setUsername("winterSweet");
        dataSource.setPassword("123456");
        dataSource.setInitialSize(3); // 初始化时建立物理连接的个数
        dataSource.setMaxActive(20); // 最大连接池数量
        dataSource.setMinIdle(2); // 最小连接池数量
        dataSource.setMaxWait(2000); // 获取连接时最大等待时间，单位毫秒
        dataSource.setPoolPreparedStatements(true); // 是否缓存preparedStatement
        dataSource.setMaxOpenPreparedStatements(100);
        // 监控统计用的filter:stat 日志用的filter:log4j 防御sql注入的filter:wall
        dataSource.setFilters("stat,wall");
        return dataSource;
    }
}
