package com.wywk.myautotest.controller;

/**
 * @author zsj
 * @date 2020/4/1 16:03
 */

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/app/")
public class GetAppTokenImpl {

//    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(GetAppTokenImpl.class);
//
//    private static final String key = "GM43dxXjoaqEHOZ51NDP306qNfmLCPtPGbCKOFfZicM=";
//    private static final String clientId = "sta_web";
//    private static final Long timeout = 2592000L;
//
//    @GetMapping("getToken")
//    public static Map<String, Object> general(String userId) {
//
//        Map<String, Object> map = new HashMap<>(3);
//        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//        long nowMillis = System.currentTimeMillis();
//        Date now = new Date(nowMillis);
//        SecretKey key = key();
//        JwtBuilder builder = Jwts.builder().setId(clientId).setIssuedAt(now).setSubject(userId).signWith(key, signatureAlgorithm);
//        Date exp = new Date(nowMillis + timeout * 1000);
//        builder.setExpiration(exp);
//        map.put("code", builder.compact());
//
//        log.info("请求接口：{}", "/app/getToken");
//        log.info("请求参数：{}", userId);
//        log.info("获取用户token: {}", map.get("code"));
//        return map;
//    }
//
//    /**
//     * 解析Token
//     *
//     * @param jwt token
//     * @return 解析结果
//     */
//    public static Claims parse(String jwt) {
//        return Jwts.parser().setSigningKey(key()).parseClaimsJws(jwt).getBody();
//    }
//
//    /**
//     * 获取私钥
//     *
//     * @return 私钥
//     */
//    private static SecretKey key() {
//        return Keys.hmacShaKeyFor(Base64Utils.encode(key.getBytes()));
//    }

}