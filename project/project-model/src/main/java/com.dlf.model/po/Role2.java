package com.dlf.model.po;


import java.util.List;

public class Role2 extends Role{

    //待新增ID和待删除ID
    List<Long> toAddIds;
    List<Long> toDelIds;

    public List<Long> getToAddIds() {
        return toAddIds;
    }

    public void setToAddIds(List<Long> toAddIds) {
        this.toAddIds = toAddIds;
    }

    public List<Long> getToDelIds() {
        return toDelIds;
    }

    public void setToDelIds(List<Long> toDelIds) {
        this.toDelIds = toDelIds;
    }
}
