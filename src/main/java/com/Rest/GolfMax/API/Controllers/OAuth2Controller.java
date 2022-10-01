package com.Rest.GolfMax.API.Controllers;

import com.Rest.GolfMax.API.Security.OAuth2.OAuth2ServiceFactory;
import com.Rest.GolfMax.API.Security.Redis.UserSessionInRedis;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.github.scribejava.core.revoke.TokenTypeHint;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
public class OAuth2Controller {

    private final OAuth2ServiceFactory oAuth2ServiceFactory;
    private final ObjectMapper objectMapper;
    private final UserSessionInRedis userSessionInRedis;
    private final HttpServletResponse httpServletResponse;
    private static final String KEY_USER = "user";
    private static final String KEY_STATE = "state";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_ACCESS_TOKEN = "accessToken";
    private static final String KEY_SERVICE_ID = "serviceId";

    @GetMapping("/oauth2/authorization/{serviceId}")
    public RedirectView oauth2Login(@PathVariable String serviceId) {
        String state = UUID.randomUUID().toString();
        userSessionInRedis.put(KEY_STATE, state, Duration.of(60, ChronoUnit.SECONDS));
        return new RedirectView(oAuth2ServiceFactory.getService(serviceId).getAuthorizationUrl(state));
    }

    @GetMapping("/login/oauth2/code/{serviceId}")
    public RedirectView oauth2Code(@PathVariable String serviceId, String code, String state)
            throws InterruptedException, ExecutionException, IOException {
        if (!Objects.equals(state, userSessionInRedis.get(KEY_STATE))) {
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
        else {
            OAuth20Service oAuth20Service = oAuth2ServiceFactory.getService(serviceId);
            OAuth2AccessToken accessToken = oAuth20Service.getAccessToken(code);
            OAuth2ServiceFactory.OAuth2Api oAuth2Api = (OAuth2ServiceFactory.OAuth2Api)oAuth20Service.getApi();

            final OAuthRequest oAuthRequest = new OAuthRequest(Verb.GET, oAuth2Api.getUserInfoEndpoint());
            oAuth20Service.signRequest(accessToken, oAuthRequest);

            Map<String, String> map = objectMapper.readValue(oAuth20Service.execute(oAuthRequest).getBody(), Map.class);
            map.put(KEY_ACCESS_TOKEN, accessToken.getAccessToken());
            map.put(KEY_SERVICE_ID, serviceId);
            map.put(KEY_USERNAME, map.get(oAuth2Api.getUserNameAttribute()));

            int timeLimit = Optional.ofNullable(accessToken.getExpiresIn()).orElse(3600);
            userSessionInRedis.put(KEY_USER, map, Duration.of(timeLimit, ChronoUnit.SECONDS));
        }
        return new RedirectView("/");
    }

    @GetMapping("/user")
    public Map user(HttpServletRequest servletRequest) {
        Map<String, Object> userMap = ((Map) userSessionInRedis.get(KEY_USER));
        return Objects.isNull(userMap) ? null : Collections.singletonMap(KEY_USERNAME, userMap.get(KEY_USERNAME));
    }

    @PostMapping("/logout")
    public void logout() throws InterruptedException, ExecutionException, IOException {
        Map<String, String> userMap = (Map) userSessionInRedis.get(KEY_USER);
        if (Objects.isNull(userMap)) {
            String serviceId = userMap.get(KEY_SERVICE_ID);
            OAuth20Service oAuth20Service = oAuth2ServiceFactory.getService(serviceId);

            if (Objects.equals("facebook", serviceId))
                revokeFacebookPermission(oAuth20Service, userMap);
            else if (!StringUtils.isEmpty(oAuth20Service.getApi().getRevokeTokenEndpoint()))
                oAuth20Service.revokeToken(userMap.get(KEY_ACCESS_TOKEN), TokenTypeHint.ACCESS_TOKEN);
        }
        userSessionInRedis.invalidate();
    }

    private void revokeFacebookPermission(OAuth20Service oAuth20Service, Map<String, String> userMap)
        throws InterruptedException, ExecutionException, IOException {

        OAuth2ServiceFactory.OAuth2Api oAuth2Api = (OAuth2ServiceFactory.OAuth2Api)oAuth20Service.getApi();
        String endpoint = oAuth2Api.getRevokePermissionEndpoint().replace("{user-id}", userMap.get("id"));
        final OAuthRequest oAuthRequest = new OAuthRequest(Verb.DELETE, endpoint);
        oAuth20Service.signRequest(userMap.get(KEY_ACCESS_TOKEN), oAuthRequest);
        oAuth20Service.execute(oAuthRequest);
    }
}
