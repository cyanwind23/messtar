package com.thiennam.messtar.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "messtar")
public class MesStarConfig {
    private String hostName;
    private String roomSocketDestPrefix;
    private String userSocketDestPrefix;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getRoomSocketDestPrefix() {
        return roomSocketDestPrefix;
    }

    public void setRoomSocketDestPrefix(String roomSocketDestPrefix) {
        this.roomSocketDestPrefix = roomSocketDestPrefix;
    }

    public String getUserSocketDestPrefix() {
        return userSocketDestPrefix;
    }

    public void setUserSocketDestPrefix(String userSocketDestPrefix) {
        this.userSocketDestPrefix = userSocketDestPrefix;
    }
}
