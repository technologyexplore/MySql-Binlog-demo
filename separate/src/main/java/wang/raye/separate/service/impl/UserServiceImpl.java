package wang.raye.separate.service.impl;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.descriptor.web.WebXml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wang.raye.separate.annotation.Master;
import wang.raye.separate.annotation.Slave;
import wang.raye.separate.config.DynamicDataSource;
import wang.raye.separate.model.User;
import wang.raye.separate.model.mapper.UserMapper;
import wang.raye.separate.service.UserService;

import java.beans.Transient;
import java.util.List;

/**
 * 用户相关业务接口实现类
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;

//    @Slave
    @Override
    public List<User> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public boolean addUser(User user) {
        return mapper.insertSelective(user) > 0;
    }

    @Override
    public boolean updateUser(User user) {
        return mapper.updateByPrimaryKey(user) > 0;
    }

    @Override
    public boolean deleteByid(int id) {
        return mapper.deleteByPrimaryKey(id) > 0;
    }

    @Transactional(rollbackFor = Exception.class )
    @Override
    public boolean insertAndUpdate(User user){
        log.info("当前key："+ DynamicDataSource.getType().name());
        int count = 0;
        count += mapper.insertSelective(user);
        user = null;
        user.getId();
        count += mapper.updateByPrimaryKey(user);
        return count > 1;
    }
}
