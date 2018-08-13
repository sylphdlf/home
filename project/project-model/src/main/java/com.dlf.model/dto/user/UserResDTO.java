package com.dlf.model.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserResDTO implements Serializable {

    private static final long serialVersionUID = 115482590716699001L;

    private Long id;

    public String username;

    public String password;

}
