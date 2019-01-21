package org.mz.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 86501
 * @Description：
 * @date 2019/1/21
 * @time 14:05
 */
public class FundUtil {
    private static String sendGet(String url, String charset) {
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), charset));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static List<String> getCurrentNetValue(String code) {
        String url = "http://hq.sinajs.cn/list=f_" + code;
        String charSet = "UTF-8";
        String[] strings = sendGet(url, charSet).split(",");
        List<String> list = new ArrayList<>();
        list.add(strings[1]);
        list.add(strings[4]);
        return list;
    }

    public static void main(String[] args) throws Exception {
        List<String> netValue = getCurrentNetValue("000614");
        System.out.println(GsonUtil.toJson(netValue));
    }
}
