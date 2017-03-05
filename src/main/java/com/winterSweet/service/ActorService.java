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

package com.winterSweet.service;

import com.google.common.collect.Lists;
import com.winterSweet.bean.Actor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: Butterfly Killer
 * Date: 2017/2/27 下午 6:00
 * <p>
 * Version: 1.0
 * Description:
 */
@Service
public class ActorService {
    public List<Actor> getActor(String name) {
        Actor actor = new Actor();
        actor.setFirstName(name);
        List<Actor> actors = Lists.newArrayList();
        actors.add(actor);
        return actors;
    }
}
