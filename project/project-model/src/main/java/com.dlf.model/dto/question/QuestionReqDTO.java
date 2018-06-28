package com.dlf.model.dto.question;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class QuestionReqDTO implements Serializable{

    private Long id;
    private String name;
    private Integer type;
    private String content;
    private String description;
}
