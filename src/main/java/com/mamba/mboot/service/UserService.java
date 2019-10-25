package com.mamba.mboot.service;

import com.mamba.mboot.RestResponse;
import com.mamba.mboot.boot.common.entity.Page;
import com.mamba.mboot.dao.UserDao;
import com.mamba.mboot.entity.User;
import com.mamba.mboot.vo.SearchUserVO;
import com.mamba.mboot.vo.UserListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:
 * author:zhongjianbin
 * Date:2019/7/13 22:53
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public RestResponse list(String userName){
        return RestResponse.success(userDao.select(userName));
    }

    public RestResponse<UserListVO> all(SearchUserVO vo, Page page){
        return RestResponse.success(buildUserList(userDao.all(vo, page)),page);
    }

    //构建经纪人列表数据结构
    private List<UserListVO> buildUserList(List<User> users){
        List<UserListVO> vos = new ArrayList<>();
        for (User user: users){
            UserListVO vo = new UserListVO();
            vo.setUserName(user.getUserName());
            vo.setPassword(user.getPassword());
            vos.add(vo);
        }
        return vos;
    }

}
