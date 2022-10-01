package com.Rest.GolfMax.API.Security.OAuth2;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.extractors.OAuth2AccessTokenExtractor;
import com.github.scribejava.core.extractors.OAuth2AccessTokenJsonExtractor;
import com.github.scribejava.core.extractors.TokenExtractor;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuth2ServiceFactory {

    private static final Map<String, OAuth20Service> services = new HashMap<>();
    private final OAuth2Properties OAUTH_PROPERTIES;

    public OAuth20Service getService(String serviceId) {
        if (services.containsKey(serviceId)) {
            return services.get(serviceId);
        }

        OAuth2Properties.Registration registration = OAUTH_PROPERTIES.getREGISTRATION().get(serviceId);
        OAuth2Properties.Provider provider = OAUTH_PROPERTIES.getPROVIDER().get(serviceId);

        OAuth20Service oAuth20Service = new ServiceBuilder(registration.getClientId())
                .apiSecret(registration.getClientSecret())
                .callback(registration.getRedirectUri())
                .defaultScope(registration.getScope())
                .responseType(registration.getAuthorizationGrantType())
                .userAgent("GolfMax")
                .build(new OAuth2Api(provider));
        services.put(serviceId, oAuth20Service);
        return oAuth20Service;
    }

    @RequiredArgsConstructor
    public static class OAuth2Api extends DefaultApi20 {

        private final OAuth2Properties.Provider PROVIDER;

        @Override
        public String getAccessTokenEndpoint() {
            return PROVIDER.getTokenUri();
        }


        @Override
        public String getAuthorizationBaseUrl() {
            return PROVIDER.getAuthorizationUri();
        }

        @Override
        public String getRevokeTokenEndpoint() {
            return PROVIDER.getRevokeTokenUri();
        }

        @Override
        public TokenExtractor<OAuth2AccessToken> getAccessTokenExtractor() {
            if ("github".equalsIgnoreCase(PROVIDER.getName()))
                return OAuth2AccessTokenExtractor.instance();

            return OAuth2AccessTokenJsonExtractor.instance();
        }

        public String getUserInfoEndpoint() {
            return PROVIDER.getUserInfoUri();
        }

        public String getUserNameAttribute() {
            return PROVIDER.getUserNameAttribute();
        }

        public String getRevokePermissionEndpoint() {
            return PROVIDER.getRevokePermissionUri();
        }
    }
}
