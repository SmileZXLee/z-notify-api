package cn.zxlee.znotifyapi.service.impl;

import cn.zxlee.znotifyapi.exception.CommonException;
import cn.zxlee.znotifyapi.mapper.UserMapper;
import cn.zxlee.znotifyapi.pojo.bo.UserBO;
import cn.zxlee.znotifyapi.pojo.bo.UserRegisterBO;
import cn.zxlee.znotifyapi.pojo.po.UserPO;
import cn.zxlee.znotifyapi.pojo.vo.UserVO;
import cn.zxlee.znotifyapi.service.IUserService;
import cn.zxlee.znotifyapi.utils.BeanConvertUtils;
import cn.zxlee.znotifyapi.utils.CheckCodeUtils;
import cn.zxlee.znotifyapi.utils.TokenUtils;
import cn.zxlee.znotifyapi.utils.mail.IMailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @program: z-notify-api
 * @description:
 * @author: zxlee
 * @create: 2022-11-26 01:05
 **/

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private IMailUtils mailUtils;

    @Autowired
    private CheckCodeUtils checkCodeUtils;

    @Override
    public boolean sendCheckCode(String mail) {
        String checkCode = CheckCodeUtils.getRandomCheckCode();
        checkCodeUtils.saveCheckCode(mail, checkCode);
        return mailUtils.sendCheckCode(checkCode, mail);
    }

    @Override
    public UserVO getByAccount(String account){
        return BeanConvertUtils.convertTo(userMapper.getByAccount(account), UserVO::new);
    }

    @Override
    public int doRegister(UserRegisterBO userBO) {
        UserVO userVO = getByAccount(userBO.getAccount());
        if (null != userVO) {
            throw new CommonException("此邮箱已注册");
        }

        String checkCode = checkCodeUtils.getCheckCode(userBO.getAccount());
        if (!(StringUtils.hasText(checkCode) && checkCode.equals(userBO.getCheckCode()))) {
            throw new CommonException("验证码不正确");
        }

        UserPO userPO = BeanConvertUtils.convertTo(userBO, UserPO::new);
        return userMapper.insertUser(userPO);
    }

    @Override
    public UserVO doLogin(UserBO userBO) {
        UserVO currentUserBO = getByAccount(userBO.getAccount());
        if (null != currentUserBO) {
            // 用户登录
            if (currentUserBO.getPassword() != null && currentUserBO.getPassword().equals(userBO.getPassword())) {
                // 登录成功
                String token = tokenUtils.generateAndSaveToken(currentUserBO.getId());
                UserVO userVO = new UserVO();
                userVO.setAccount(currentUserBO.getAccount());
                userVO.setToken(token);
                return userVO;
            } else {
                throw new CommonException("用户密码不正确");
            }
        } else {
            throw new CommonException("用户未注册");
        }
    }

    @Override
    public boolean doLogout(String token) {
        return tokenUtils.removeToken(token);
    }
}
