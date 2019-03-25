package wang.raye.separate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.raye.separate.model.User;
import wang.raye.separate.model.WebResult;
import wang.raye.separate.service.UserService;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    @RequestMapping("add")
    public WebResult add(User user){
        if(service.addUser(user)){
            return WebResult.success();
        }else{
            return WebResult.error("添加失败");
        }
    }

    @RequestMapping("update")
    public WebResult update(User user){
        if(service.updateUser(user)){
            return WebResult.success();
        }else{
            return WebResult.error("更新失败");
        }
    }

    @RequestMapping("delete")
    public WebResult delete(int id){
        if(service.deleteByid(id)){
            return WebResult.success();
        }else{
            return WebResult.error("删除失败");
        }
    }

    @RequestMapping("select")
    public WebResult select(){
        return WebResult.success(service.selectAll());
    }

    @RequestMapping("test")
    public WebResult test(User user){
        try {
            if(service.insertAndUpdate(user)) {
                return WebResult.success();
            }else {
                return WebResult.error("失败,查看事物");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return WebResult.error("失败:"+e.getMessage());
        }
    }
}
