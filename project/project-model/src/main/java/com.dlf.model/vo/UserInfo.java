package com.dlf.model.vo;

import com.dlf.model.po.Function;
import com.dlf.model.po.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserInfo {

    private Long id;

    private String username;

    private String password;

    private List<Role> roles;

    private List<Function> functions;
}
