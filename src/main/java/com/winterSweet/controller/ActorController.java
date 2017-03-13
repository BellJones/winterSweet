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

package com.winterSweet.controller;

import com.google.common.collect.Lists;
import com.winterSweet.api.BaseController;
import com.winterSweet.api.ResponseBean;
import com.winterSweet.bean.Actor;
import com.winterSweet.service.ActorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: Butterfly Killer
 * Date: 2017/2/21 上午 1:00
 * <p>
 * Description: 测试用
 */
@Controller
@RequestMapping("/test")
public class ActorController extends BaseController {

    private static final Logger LOGGER = LogManager.getLogger(ActorController.class.getName());

    @Autowired
    private ActorService actorService;

    @RequestMapping(value = "/abc", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String testA(@RequestParam String test) {
        LOGGER.info("test is : {}", test);
        return "success : " + test;
    }

    @RequestMapping (value = "/actor", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResponseBean<Actor> test(@RequestParam ("name") String name) {
        LOGGER.info("name is : {}", name);
        List<Actor> list = actorService.getActor(name);
        return success(list.size(), list);
    }
}
