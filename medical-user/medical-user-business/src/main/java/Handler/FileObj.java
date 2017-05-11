package Handler;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/10.
 */
public class FileObj implements Serializable, Cloneable {

    private String fileName;
    private Long size;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
