package com.xxxcloud.generator.javafx.LookDivTools;

import com.alibaba.fastjson.JSONObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author LinHongli
 */
public class LookDivController {
    /**
     * 这里的Button对象有需要加@FXML注解，然后变量的名称为你刚才在FXML文件中声明的Button的id属性
     */
    @FXML
    private Button generateBtn;

    @FXML
    private TextField table;

    String s = "";
    /**
     * 这里的handleButtonAction方法为我们在FXML文件中声明的onAction的处理函数
     *
     * @param event
     */
    @FXML
    protected void generateAction(ActionEvent event) {
        Map<String, Object> key = new HashMap<>();
        key.put("key", "www.lookdiv.com");
        JSONObject jsonObject = new JSONObject(key);
        try {
            s = doPost("http://lookdiv.com/index/index/indexcode.html", jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        generateBtn.setText("生成结束");
        table.setText(s);
    }


    @FXML
    protected void openFileAction(ActionEvent event) {
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable tText = new StringSelection(s);
        clip.setContents(tText, null);
        generateBtn.setText("生成");
    }


    public static void deleteFile(String filePath){
        File file = new File(filePath);//输入要删除文件目录的绝对路径
        //取得这个目录下的所有子文件对象
        File[] files = file.listFiles();
        if (file==null||!file.exists()){
            return;
        }
        //遍历该目录下的文件对象
        for (File f: files){
            //判断子目录是否存在子目录,如果是文件则删除
            if (f.isDirectory()){
                deleteFile(f.getPath());
            }else {
                f.delete();
            }
        }
        //删除空文件夹  for循环已经把上一层节点的目录清空。
        file.delete();
    }


    public static String doPost(String url, String params) throws Exception {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);// 创建httpPost
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.89 Safari/537.36");

        String charSet = "UTF-8";
        StringEntity entity = new StringEntity(params, charSet);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;

        try {

            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();

            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                return jsonString;
            } else if (state == HttpStatus.SC_MOVED_PERMANENTLY || state == HttpStatus.SC_MOVED_TEMPORARILY) {
                //重定向
                String newuri = "";
                // 跳转的目标地址是在 HTTP-HEAD 中的
                Header header = response.getFirstHeader("location");
                newuri = header.getValue();
                System.out.println(newuri);
                System.out.println("状态码：" + state);

                httpPost = new HttpPost("http://lookdiv.com" + newuri);
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-Type", "application/json");
                httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.89 Safari/537.36");
                entity = new StringEntity(params, "UTF-8");
                httpPost.setEntity(entity);

                response = httpclient.execute(httpPost);
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);

                String[] split = jsonString.split("<textarea class=\"form-control\" style=\"height: 200px; overflow:hidden\" rows=\"8\">");
                List<String> collect = Arrays.stream(split).map(item -> {
                    String[] split1 = item.split("</textarea>");
                    return split1[0];
                }).collect(Collectors.toList());

                return collect.get(1);
            } else {
                System.out.println("状态码：" + state);
            }
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
