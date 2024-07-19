package com.example.runnersclub.oauth;

public interface OAuth2UserInfo {
    String getProvider();

    String getProviderId();

    String getEmail();

    String getName();
}
