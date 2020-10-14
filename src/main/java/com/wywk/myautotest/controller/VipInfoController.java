package com.wywk.myautotest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @ClassName: VipInfoController
 * @author: zsj
 * @Date: 2020/4/14 09:57
 * @Descripition:
 */

@Api(value = "vipInfoController")
@RestController
public class VipInfoController {

    @Value("${yvip.path}")
    private String yvip;


    @ApiImplicitParams(@ApiImplicitParam)
    @GetMapping("vip/info")
    public String userInfo(String url, String data) throws IOException
    {
        CloseableHttpClient closeableHttpClient= HttpClients.createDefault();
        // 创建http请求
        HttpGet httpGet = new HttpGet(url);
        System.out.println("url: " + httpGet.getURI());
        CloseableHttpResponse response = null;
        try {
            //执行请求
            response = closeableHttpClient.execute(httpGet);
            //获取http响应体
            HttpEntity httpEntity = response.getEntity();
            //打印响应
            System.out.println(response.getStatusLine());
            if (httpEntity != null )
            {
                System.out.println("response content length: " + httpEntity.getContentLength());
                String content = EntityUtils.toString(httpEntity);
                System.out.println("response content: " + content);
                return content;
            }
        } catch (ClientProtocolException e)
        {
            e.printStackTrace();
        }
        finally {
            response.close();
            closeableHttpClient.close();
        }
        return null;
    }
}
