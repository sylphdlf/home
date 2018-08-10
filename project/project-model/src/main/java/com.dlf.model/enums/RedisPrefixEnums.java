package com.dlf.model.enums;

public enum RedisPrefixEnums {

    REGISTER_MESSAGE("register_message_","注册验证码"),
    PASSWORD_MESSAGE("password_message_","忘记密码验证码"),
    SEND_LIMIT_PER_TIME("send_limit_per_time_","同一号码2次发送的间隔"),
    SEND_LIMIT_MAX_TIME("send_limit_max_time_","最大限制次数"),
    USER_LOGIN_CACHE("user_login_cache_","用户登录缓存用户名密码"),
    USER_NAME_COMPARE_LIST("user_name_compare_list_","注册用户名比较"),
    ORG_TREE_NODE("org_tree_node", "组织机构树"),
    ORG_TREE_NODE_PARENT("org_tree_node_parent_", "组织机构树父节点"),
    ;
    private String code;
    private String desc;

    RedisPrefixEnums(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
