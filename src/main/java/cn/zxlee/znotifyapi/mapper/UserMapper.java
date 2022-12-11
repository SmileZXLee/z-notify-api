package cn.zxlee.znotifyapi.mapper;

import cn.zxlee.znotifyapi.pojo.po.UserPO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int insertUser(UserPO user);

    UserPO getByAccount(String account);
}
