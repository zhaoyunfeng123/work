package service;

import bean.UserBean;

import java.util.List;

/**
 * 用户需求的接口
 */
public interface UserService {
    /**
     * 添加用户信息：注册
     */
    void register(UserBean user);
    /**
     * 登录
     */
    boolean login(UserBean user);
    /**
     * 根据用户名查找用户信息
     */
    UserBean findUser(String name);
    /**
     * 查找所有用户
     * */
    List<UserBean> selectAllUser();
    /**
     * 删除用户
     * */
    boolean deleteUserByUserName(String userName);
    /**
     * 修改用户信息
     * */
    boolean updateUserByUserName(UserBean userBean);
}
