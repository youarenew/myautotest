package com.wywk.myautotest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wywk.myautotest.constant.GlobalConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName: GetApp Sign and Token
 * @Author: zsj
 * @Since:  2020/7/11 11:00
 * @Description:
 */

@RestController
@RequestMapping(value = "app")
public class GetAppAuthController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(GetAppAuthController.class);

    private static final String key = "GM43dxXjoaqEHOZ51NDP306qNfmLCPtPGbCKOFfZicM=";
    private static final String clientId = "sta_web";
    private static final Long timeout = 2592000L;

    @GetMapping("getToken")
    public static Map<String, Object> general(String userId) {

        Map<String, Object> map = new HashMap<>(3);
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey key = key();
        JwtBuilder builder = Jwts.builder().setId(clientId).setIssuedAt(now).setSubject(userId).signWith(key, signatureAlgorithm);
        Date exp = new Date(nowMillis + timeout * 1000);
        builder.setExpiration(exp);

        map.put(GlobalConstants.RESP_CODE_KEY, GlobalConstants.RESP_SUCCESS_CODE);
        map.put(GlobalConstants.RESP_TOKEN_KEY, builder.compact());

        log.info("请求接口：{}", "/app/getToken");
        log.info("请求参数：{}", userId);
        log.info("获取用户token: {}", map.get("code"));
        return map;
    }

    /**
     * 解析Token
     *
     * @param jwt token
     * @return 解析结果
     */
    public static Claims parse(String jwt) {
        return Jwts.parser().setSigningKey(key()).parseClaimsJws(jwt).getBody();
    }

    /**
     * 获取私钥
     *
     * @return 私钥
     */
    private static SecretKey key() {
        return Keys.hmacShaKeyFor(Base64Utils.encode(key.getBytes()));
    }



    /**
     * 签名
     *
     * @return sign 签名
     */
    @PostMapping(value = "getSign")
    public static Object sign(String body,String version,String token) {
        Map<String, Object> map = new HashMap<>();
        LinkedHashMap headers = new LinkedHashMap();
        headers.put("X-Authorization", token);
        log.info("user token: " + token);
        headers.put("X-Version", version);
        log.info("user version: " + version);
        StringBuilder stringBuilder = new StringBuilder();
        for (Object o : headers.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            stringBuilder.append(entry.getKey());
            stringBuilder.append("=");
            stringBuilder.append(entry.getValue());
            stringBuilder.append("&");
        }
        String header = stringBuilder.length() > 0 ? stringBuilder.substring(0, stringBuilder.length() - 1) : "";

        Map preBody = new LinkedHashMap();
        if (StringUtils.hasText(header)) {
            preBody.put("header", header);
        }
        if (StringUtils.hasText(body)) {
            preBody.put("body", body);
        }
        preBody.put("salt", "wywk2018");
        String preSign;
        try {
            preSign = new ObjectMapper().writeValueAsString(preBody);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        map.put("sign",md5(preSign));
        log.info("user body: " + body);
        log.info("result: " + map);

        return map;
    }

    /** md5计算 */
    private static String md5(String message) {
        String md5str = "";
        try {
            // 1 创建一个提供信息摘要算法的对象，初始化为md5算法对象
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 2 将消息变成byte数组
            byte[] input = (message).getBytes();
            // 3 计算后获得字节数组,这就是那128位了
            byte[] buff = md.digest(input);
            // 4 把数组每一字节（一个字节占八位）换成16进制连成md5字符串
            md5str = bytesToHex(buff);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5str;
    }

    /** 二进制转十六进制 */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder md5str = new StringBuilder();
        // 把数组每一字节换成16进制连成md5字符串
        int digital;
        for (byte aByte : bytes) {
            digital = aByte;
            if (digital < 0) {
                digital += 256;
            }
            if (digital < 16) {
                md5str.append("0");
            }
            md5str.append(Integer.toHexString(digital));
        }
        return md5str.toString().toUpperCase();
    }



}
