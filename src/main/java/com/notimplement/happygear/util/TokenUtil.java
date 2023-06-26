package com.notimplement.happygear.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

public class TokenUtil {
    public static String getBearerToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
