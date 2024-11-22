package com.phn.mytakeout.utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtTool {

    public String createJwt(String secretKey,Long userId, long ttl) {
        //提供签名算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //提供过期时间
        long time = System.currentTimeMillis() + ttl;
        Date date = new Date(time);

        Map<String,Object> claim = new HashMap<String,Object>();
        claim.put("userId", userId);

        //设置jwt的body
        JwtBuilder builder = Jwts.builder()
                .setClaims(claim)
                .signWith(signatureAlgorithm,secretKey.getBytes(StandardCharsets.UTF_8))
                .setExpiration(date);
        return builder.compact();
    }

    public Claims parseToken(String secretKey, String token) {
        // 得到DefaultJwtParser
        Claims claims = Jwts.parser()
                // 设置签名的秘钥
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                // 设置需要解析的jwt
                .parseClaimsJws(token).getBody();
        return claims;
    }

}
