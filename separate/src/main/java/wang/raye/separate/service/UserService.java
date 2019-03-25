package wang.raye.separate.service;

import wang.raye.separate.model.User;

import java.util.List;

/**
 * 用户相关业务接口
 */
public interface UserService {

    /**
     * 查询所有用户 - - 使用从库
     * @return
     */
    List<User> selectAll();

    /**
     * 添加用户 - - 使用主库
     * @param user
     * @return
     */
    boolean addUser(User user);

    /**
     * 更新用户 - - 使用主库
     * @param user
     * @return
     */
    boolean updateUser(User user);

    /**
     * 删除用户 - - 使用从库
     * @param id
     * @return
     */
    boolean deleteByid(int id);

    /**
     * 测试事物，先插入，然后更新的时候出异常
     * @param user
     * @return
     */
    boolean insertAndUpdate(User user);
}
