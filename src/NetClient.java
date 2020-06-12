import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 类 -
 * ============================================================================
 * 2008-2019 hoperun,并保留所有权利。
 * ============================================================================
 *
 * @author : Zane Qin
 * creatTime : 10:49 2019-02-13
 * modifier:
 * modifyTime:
 */
public class NetClient {
    public static void main(String[] args) throws IOException {

        Socket s = new Socket("127.0.0.1", 30000);
        BufferedReader bf = new BufferedReader(new InputStreamReader(s.getInputStream()));
        System.out.println("来自server的消息:" + bf.readLine());
        bf.close();
        s.close();
    }
}
