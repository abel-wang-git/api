package com.wanghuiwen.base.config;


import com.wanghuiwen.base.model.Role;
import com.wanghuiwen.base.model.SysDepartment;
import com.wanghuiwen.base.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目常量
 */
public final class ProjectConstant {


    /**
     * 初始化用户
     */
    public static List<User> users = new ArrayList<>();

    /**
     * 默认部门
     */
    public static SysDepartment department;

    static {
        SysDepartment department1 = new SysDepartment();
        department1.setId(1L);
        department1.setName("总组织");
        department1.setPid(0L);
        department =department1;

        User user = new User();
        user.setAvatar("http://e.hiphotos.baidu.com/image/pic/item/a1ec08fa513d2697e542494057fbb2fb4316d81e.jpg");
        user.setEnable(true);
        user.setLoginName("admin");
        user.setNickName("管理员");
        user.setPassword(new BCryptPasswordEncoder().encode("123456"));
        user.setLocked(false);
        users.add(user);
    }

    public static final String BASE_PACKAGE = "com.api";//项目基础包名称，根据自己公司的项目修改

    static final String MODEL_PACKAGE = BASE_PACKAGE + ".model";//Model所在包
    static final String MAPPER_PACKAGE = BASE_PACKAGE + ".**.dao";//Mapper所在包
    static final String MAPPER_INTERFACE_REFERENCE = "com.wanghuiwen.core.ApiMapper";//Mapper插件基础接口的完全限定名

    public static final String ROLE_ADMIN="管理员";
    public static final String ROLE_USER="普通用户";

    /**
     * 默认角色
     */
    static final Role[] initRole={new Role("管理员"),new Role("普通用户")};

    /**
     * 缓存Key
     */
    public static final String WHITE_LIST_CACHE_KEY = "white_list";
    public static final String API_LIST_CACHE_KEY = "api_list";

}
