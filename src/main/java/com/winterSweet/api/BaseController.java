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

package com.winterSweet.api;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: Butterfly Killer
 * Date: 2017/2/27 下午 5:40
 * <p>
 * Version: 1.0
 * Description:
 */
public class BaseController {

    public <T> ResponseBean<T> success(int total, List<T> result, String message) {
        ResponseBean<T> responseBean = new ResponseBean<>();
        responseBean.setCode(HttpServletResponse.SC_OK);
        responseBean.setTotal(total);
        responseBean.setResult(result);
        responseBean.setMessage(message);
        return responseBean;
    }

    public <T> ResponseBean<T> success(int total, List<T> result) {
        ResponseBean<T> responseBean = new ResponseBean<>();
        responseBean.setCode(HttpServletResponse.SC_OK);
        responseBean.setTotal(total);
        responseBean.setResult(result);
        return responseBean;
    }

    public <T> ResponseBean<T> error(int code, String message) {
        ResponseBean<T> responseBean = new ResponseBean<>();
        responseBean.setCode(code);
        responseBean.setMessage(message);
        return responseBean;
    }
}
