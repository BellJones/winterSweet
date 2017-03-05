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

package com.winterSweet.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA
 * User: Butterfly Killer
 * Date: 2017/2/23 下午 11:01
 * <p>
 * Description: 异常
 */
public class SweetException extends Exception {
    public static final Logger LOGGER = LoggerFactory.getLogger(SweetException.class.getName());

    public SweetException() {
    }

    public SweetException(String message) {
        super(message);
        LOGGER.error(message);
    }
}
