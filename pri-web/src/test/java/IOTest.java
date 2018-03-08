import java.io.*;

/**
 * Created by admin on 2018/3/8.
 */
public class IOTest {

    public static void main(String[] args) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream("D:/upload/img.jpg");
            outputStream = new FileOutputStream("D:/upload/newImg.jpg");
            int len = 0;
            //定义一个数组,长度为1024
            byte[] b = new byte[1024];
            //使用一个byte的数组作为一个缓冲区，每次从数据源中读取和缓冲区大小（二进制位）相同的数据并将其存在缓冲区中,直到末尾没有可用的字节才结束
            while ((len = inputStream.read(b)) >= 0){
                outputStream.write(b, 0, len);
                //如果没有调用close()方法要调用filush(),不然有时会出问题,  调用close()方法的时候会默认自动调用flush()方法
                outputStream.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (inputStream != null){
                    inputStream.close();
                }
                if (outputStream != null){
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
