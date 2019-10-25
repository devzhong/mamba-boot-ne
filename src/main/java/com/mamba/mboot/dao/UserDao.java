package com.mamba.mboot.dao;

import com.mamba.mboot.boot.common.entity.Page;
import com.mamba.mboot.entity.User;
import com.mamba.mboot.vo.SearchUserVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * desc:
 * author:zhongjianbin
 * Date:2019/7/13 22:48
 */
@Repository
public interface UserDao {

    User select(String userName);

    List<User> all(@Param("user") SearchUserVO vo, Page page);
}
