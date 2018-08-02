package com.dlf.model.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TreeNode {

    private Long id;

    private String name;

    private String code;

    private String label;

    private String parent;

    private List<TreeNode> children;
}
