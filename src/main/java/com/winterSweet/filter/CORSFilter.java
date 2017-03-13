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

package com.winterSweet.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA
 * User: Butterfly Killer
 * Date: 2017/2/21 上午 12:01
 * <p>
 * Description: 跨域资源访问配置
 */
public class CORSFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        // 授权的源控制
        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        // 控制是否开启与Ajax的Cookie提交方式
        httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
        // 允许请求的HTTP Method
        httpServletResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE, PATCH");
        // 控制哪些header能发送真正的请求
        httpServletResponse.addHeader("Access-Control-Allow-Headers", "*");
//        httpServletResponse.addHeader("Access-Control-Allow-Headers", "DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type");
        // 授权的时间
        httpServletResponse.addHeader("Access-Control-Max-Age", "1728000");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
