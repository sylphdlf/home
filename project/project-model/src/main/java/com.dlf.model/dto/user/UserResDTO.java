package com.dlf.model.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class UserResDTO implements Serializable {

    private static final long serialVersionUID = -8882679226062272497L;

    private Long id;

    private String username;

    private String password;

    private String realName;

    private String sessionId;

    private Date lastAccessTime;

    private String realIp;

}
