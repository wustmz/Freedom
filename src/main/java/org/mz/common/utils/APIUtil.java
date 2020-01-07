package org.mz.common.utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class APIUtil {


    public static String API(String APIName) {
        return API(APIName, new HashMap<>());
    }


    /**
     * 返回API调用结果
     *
     * @param APIName 接口在api.properties中的名称
     * @param params  访问api所需的参数及参数值
     * @return 此处返回的是JSON格式的数据
     */
    public static String API(String APIName, Map<String, Object> params) {
        String content = "";
        //请求结果
        CloseableHttpResponse response;
        //实例化httpclient
        CloseableHttpClient httpclient = HttpClients.createDefault();

        try {
            //读取配置文件的URL
            Properties properties = new Properties();
            URL fileURL = APIUtil.class.getClassLoader().getResource("api.properties");
            properties.load(new FileInputStream(new File(fileURL.getFile())));
            String API = properties.getProperty(APIName);
            //构造url请求
            StringBuilder url = new StringBuilder(API);
            if (params != null && params.size() > 0) {
                url.append("?");
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    url.append(entry.getKey() + "=" + entry.getValue() + "&");
                }
                url.substring(0, url.length() - 1);
            }
            //实例化get方法
            HttpGet httpget = new HttpGet(url.toString());
            //执行get请求
            response = httpclient.execute(httpget);
            if (response.getStatusLine().getStatusCode() == 200) {
                content = EntityUtils.toString(response.getEntity(), "utf-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static void main(String[] args) {
        String s = API("bank", new HashMap<>());
        double now = Double.parseDouble(s.split(",")[3]);
        double buy = 10.46;
        double rate = (now - buy) / buy;
        System.out.println("now========>>" + now);
        System.out.println("rate========>>" + rate);
    }

}

