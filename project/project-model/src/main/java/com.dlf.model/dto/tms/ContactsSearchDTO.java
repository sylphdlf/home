package com.dlf.model.dto.tms;

import com.dlf.model.dto.PageDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContactsSearchDTO extends PageDTO {

    private String name;

    private Integer type;
}
