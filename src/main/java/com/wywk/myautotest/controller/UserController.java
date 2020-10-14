package com.wywk.myautotest.controller;

import com.wywk.myautotest.constant.GlobalConstants;
import com.wywk.myautotest.entity.User;
import com.wywk.myautotest.service.RedisService;
import com.wywk.myautotest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @ClassName: UserController
 * @Author: zsj
 * @Since:  2020/4/2 19:17
 * @Description: 用户操作接口映射
 */

@Controller
@RequestMapping(value = "user", method = RequestMethod.POST)
public class UserController {

    private final static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    /**
     * 查询所有用户
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "findAll")
    public List<User> findAll(){
        return userService.findAll();
    }

    /**
     * 新建用户
     * @param user
     */
    @ResponseBody
    @RequestMapping(value = "create")
    public Object create(@RequestBody User user) {

        log.info(user.toString());
        userService.create(user);
        HashMap<String, Object> result = new HashMap<>();
        result.put(GlobalConstants.RESP_CODE_KEY, GlobalConstants.getRespSuccessCode());
        result.put("data", user);
        result.put(GlobalConstants.RESP_MSG_KEY, GlobalConstants.getRespSuccessMsg());
        log.info("用户新建成功！" + user.toString());
        redisService.set(user.getId().toString(), user.getUserNo());
        log.info("缓存用户信息" + user.getId() + ":" + user.getUserNo());
        return result;

    }


    /**
     *  根据ID查询用户
     */
    @ResponseBody
    @RequestMapping(value = "findUserById")
    public Object findById(long id){
        HashMap<String, Object> result = new HashMap<>();

        if (userService.findUserById(id) == null){
            result.put(GlobalConstants.RESP_CODE_KEY, GlobalConstants.getRespFailCode());
            result.put(GlobalConstants.RESP_MSG_KEY, GlobalConstants.getRespFailResearchMsg());
            result.put("data", null);
            log.warn("未查询到会员");
            return result;
        }else {
            result.put(GlobalConstants.RESP_CODE_KEY, GlobalConstants.getRespSuccessCode());
            result.put(GlobalConstants.RESP_MSG_KEY, GlobalConstants.getRespSuccessMsg());
            result.put(GlobalConstants.RESP_DATA_KEY, userService.findUserById(id));
            log.info("会员查询成功！" + userService.findUserById(id).getUserName() + ":" + userService.findUserById(id).getUserNo());
            return result;
        }
    }

    /**
     * 根据ID删除用户
     */
    @ResponseBody
    @RequestMapping(value = "delete")
    public Object deleteById(long id){
        HashMap<String, Object> result = new HashMap<>();
        if (userService.delete(id)){
            result.put(GlobalConstants.RESP_CODE_KEY, GlobalConstants.getRespSuccessCode());
            result.put(GlobalConstants.RESP_MSG_KEY, GlobalConstants.getRespSuccessMsg());
            log.info("用户删除成功！");
            return result;
        } else {
            result.put(GlobalConstants.RESP_CODE_KEY, GlobalConstants.getRespFailCode());
            result.put(GlobalConstants.RESP_MSG_KEY, GlobalConstants.getRespFailResearchMsg());
            log.info("未查询到该用户");
            return result;
        }
    }

    @ResponseBody
    @RequestMapping(value = "update")
    public Object updateUserById(@RequestBody User user){
        HashMap<String, Object> result = new HashMap<>();
        if (userService.update(user)){
            result.put(GlobalConstants.RESP_CODE_KEY, GlobalConstants.getRespSuccessCode());
            result.put(GlobalConstants.RESP_MSG_KEY, GlobalConstants.getRespSuccessMsg());
            log.info("用户更新成功！");
        } else {
            result.put(GlobalConstants.RESP_CODE_KEY, GlobalConstants.getRespFailCode());
            result.put(GlobalConstants.RESP_MSG_KEY, GlobalConstants.getRespFailResearchMsg());
            log.warn("未查询到用户");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "count")
    public Map<String ,Object> getCount(){
        HashMap<String, Object> result = new HashMap<>();

        result.put(GlobalConstants.RESP_CODE_KEY, GlobalConstants.getRespSuccessCode());
        result.put(GlobalConstants.RESP_DATA_KEY, userService.userCount());
        return result;
    }
}
