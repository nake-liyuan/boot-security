package com.ly.security.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.ly.common.exception.ApiException;
import com.ly.security.dto.PayloadDto;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @description: JWT工具类
 * @author: LiYuan
 * @time: 2023/5/16 21:50
 */
@Component
public class JwtTokenUtil {

    private static final KeyPair keyPair;

    static {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "123456".toCharArray());
        keyPair= keyStoreKeyFactory.getKeyPair("jwt", "123456".toCharArray());
    }

    public String generateTokenByRSA(String payloadStr){
        //创建JWS头，设置签名算法和类型
        JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.RS256)
                .type(JOSEObjectType.JWT)
                .build();
        //将负载信息封装到Payload中
        Payload payload = new Payload(payloadStr);
        //创建JWS对象
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        //创建RSA签名器
        JWSSigner jwsSigner = new RSASSASigner(keyPair.getPrivate());
        //签名
        try {
            jwsObject.sign(jwsSigner);
        } catch (JOSEException e) {
            throw new ApiException(e);
        }
        return jwsObject.serialize();
    }

    public PayloadDto verifyTokenByRSA(String token) {
        //从token中解析JWS对象
        JWSObject jwsObject = null;
        try {
            jwsObject = JWSObject.parse(token);
        } catch (ParseException e) {
            throw new ApiException("token解析失败！");
        }
        //使用RSA公钥创建RSA验证器
        JWSVerifier jwsVerifier = new RSASSAVerifier((RSAPublicKey) keyPair.getPublic());
        try {
            if (!jwsObject.verify(jwsVerifier)) {
                throw new ApiException("token签名不合法！");
            }
        } catch (JOSEException e) {
            throw new ApiException(e);
        }
        String payload = jwsObject.getPayload().toString();
        PayloadDto payloadDto = JSONUtil.toBean(payload, PayloadDto.class);
        if (payloadDto.getExp() < new Date().getTime()) {
            throw new ApiException("token已过期！");
        }
        return payloadDto;
    }


    public String refreshHeadToken(String oldToken) throws JOSEException {
        PayloadDto payloadDto = verifyTokenByRSA(oldToken);
        String userName=payloadDto.getUserName();
        List<String> authorities=payloadDto.getAuthorities();
        PayloadDto newPayloadDto = getDefaultPayloadDto(userName, authorities);
        return generateTokenByRSA(JSONUtil.toJsonStr(newPayloadDto));
    }

    public PayloadDto getDefaultPayloadDto(String userName, List<String> authorities) {
        Date now = new Date();
        Date exp = DateUtil.offsetSecond(now, 60*60);
        return PayloadDto.builder()
                .sub("liyuan")
                .iat(now.getTime())
                .exp(exp.getTime())
                .jti(UUID.randomUUID().toString())
                .userName(userName)
                .authorities(authorities)
                .build();
    }

}
