package cn.zxlee.znotifyapi.service;

import cn.zxlee.znotifyapi.pojo.bo.UserBO;
import cn.zxlee.znotifyapi.pojo.bo.UserRegisterBO;
import cn.zxlee.znotifyapi.pojo.vo.UserVO;


public interface IUserService {

    boolean sendCheckCode(String mail);

    UserVO getByAccount(String account);

    int doRegister(UserRegisterBO userBO);

    UserVO doLogin(UserBO userBO);

    boolean doLogout(String token);
}
