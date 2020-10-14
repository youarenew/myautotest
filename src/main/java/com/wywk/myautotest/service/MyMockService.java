package com.wywk.myautotest.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: MyMockService
 * @Author:  zsj
 * @Since:  2020/6/12 13:42
 * @Description:
 *
 * 短信挡板接口
 */

@Service
@RestController
@RequestMapping("/msg/")
public class MyMockService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MyMockService.class);

    @RequestMapping(value = "send", method = {RequestMethod.POST, RequestMethod.GET})
    public Object msgSend() {
        JSONObject jsonObject = new JSONObject();
        Map<String,Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("success", "true");
        map.put("data", true);
        jsonObject.putAll(map);
        log.info("返回数据：" + jsonObject);
        return jsonObject;
    }
}
