package service.Impl;

import bean.UserBean;
import dao.UserDao;
import service.UserService;
import util.DataBaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 用户需求的接口实现
 */
public class UserServiceImpl implements UserService {
    /**
     * 添加用户信息：注册
     */
    @Override
    public void register(UserBean user){
        UserDao userDao = new UserDao();
        userDao.save(user);
    }
    /**
     * 登录
     */
    @Override
    public boolean login(UserBean user) {
        UserDao userDao = new UserDao();
        UserBean userBean = userDao.findByUserNameAndPasswordAndUserType(user.getUserName(), user.getPassword(), user.getUserType());
        if (userBean != null){
            return true;
        }
        return false;
    }
    /**
     * 根据用户名查找用户信息
     */
    @Override
    public UserBean findUser(String name) {
        UserDao userDao = new UserDao();
        return userDao.findByUserName(name);
    }
    /**
     * 查找所有用户
     * */
    @Override
    public List<UserBean> selectAllUser() {
        UserDao userDao = new UserDao();
        return userDao.findAllUser();
    }
    /**
     * 删除用户
     * */
    @Override
    public boolean deleteUserByUserName(String userName) {
        UserDao userDao = new UserDao();
        return userDao.removeUser(userName);
    }
    /**
     * 修改用户信息
     * */
    @Override
    public boolean updateUserByUserName(UserBean userBean) {
        UserDao userDao = new UserDao();
        return userDao.updateUser(userBean);
    }
}
