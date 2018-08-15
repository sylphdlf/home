package com.dlf.model.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserResDTO implements Serializable {

    private static final long serialVersionUID = 3751291080758411285L;

    private Long id;

    private String username;

    private String password;

}
