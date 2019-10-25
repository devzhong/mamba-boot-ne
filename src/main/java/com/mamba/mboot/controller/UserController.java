package com.mamba.mboot.controller;

import com.mamba.mboot.RestResponse;
import com.mamba.mboot.boot.common.entity.Page;
import com.mamba.mboot.service.UserService;
import com.mamba.mboot.vo.SearchUserVO;
import com.mamba.mboot.vo.UserListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * desc:
 * author:zhongjianbin
 * Date:2019/7/13 22:58
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/findOne", method = RequestMethod.GET)
    public RestResponse findOne(String userName) {
        return userService.list(userName);
    }


    @RequestMapping(value = "/find_all", method = RequestMethod.GET)
    public RestResponse<UserListVO> all(SearchUserVO vo, Page page){
        return userService.all(vo, page);
    }
}
