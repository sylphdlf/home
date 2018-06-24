package com.dlf.model.dto.question;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class QuestionSearchDTO implements Serializable{

    private String name;
    private Integer type;
}
