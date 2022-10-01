package com.Rest.GolfMax.API.Security.Redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserSessionInRedis {

    private final RedisTemplate<String, Object> REDIS_TEMPLATE;
    private final HttpServletRequest SERVLET_REQUEST;
    private final HttpServletResponse SERVLET_RESPONSE;

    public void put(String key, Object value, Duration timeout) {
        REDIS_TEMPLATE.opsForValue().set(buildSessionKey(key), value, timeout);
    }

    public Object get(String key) {
        return REDIS_TEMPLATE.opsForValue().get(buildSessionKey(key));
    }

    public void invalidate() {
        Set<String> keys = REDIS_TEMPLATE.keys(SERVLET_REQUEST.getSession().getId().concat("*"));
        if (keys != null) {
            for (String key : keys) {
                REDIS_TEMPLATE.expire(key, Duration.ZERO);
            }
        }

        Cookie sessionCookie = WebUtils.getCookie(SERVLET_REQUEST, "JSESSIONID");
        if (sessionCookie != null) {
            sessionCookie.setMaxAge(0);
        }
        SERVLET_RESPONSE.addCookie(sessionCookie);

        SERVLET_REQUEST.getSession().invalidate();
    }

    private String buildSessionKey(String key) {
        return String.format("%s-%s", SERVLET_REQUEST.getSession().getId(), key);
    }
}
