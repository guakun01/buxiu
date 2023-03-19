package com.github.guakun01.buxiu;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("buxiu")
public class BuXiuProperties {
    /**
     * 是否准备好营业
     */
    private boolean ready;

    /**
     * 营业时间
     */
    private String openHours;

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public String getOpenHours() {
        return openHours;
    }

    public void setOpenHours(String openHours) {
        this.openHours = openHours;
    }
}
