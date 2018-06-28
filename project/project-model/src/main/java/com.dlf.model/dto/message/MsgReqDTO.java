package com.dlf.model.dto.message;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MsgReqDTO {
    /**
     * 目标对象（手机、邮箱）
     */
    private String target;
    /**
     * 内容
     */
    private String content;
}
