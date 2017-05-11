package Handler;

import java.io.*;

/**
 * Created by Administrator on 2017/5/10.
 */
public class TestCreateObj {
    public static void main(String[] args) throws IOException, ClassNotFoundException, CloneNotSupportedException {
        RandomAccessFile file = new RandomAccessFile("E:\\randomAccess.txt","rw");

        for(int i=1;i<=5;i++){
            file.writeUTF(i + "");
        }

        file.close();

//        file.seek(1L);
    }
}
