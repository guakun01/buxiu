package com.github.guakun01.buxiu.actuator;

import com.github.guakun01.buxiu.BuXiuProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@WebEndpoint(id = "buxiu")
public class BuxiuEndpoint {
    private BuXiuProperties buXiuProperties;

    public BuxiuEndpoint(ObjectProvider<BuXiuProperties> buXiuProperties) {
        this.buXiuProperties = buXiuProperties.getIfAvailable();
    }

    @ReadOperation
    public String state() {
        if (Objects.isNull(buXiuProperties) || !buXiuProperties.isReady()) {
            return "【不修】 还没有准备好开始营业。";
        } else {
            return "【不修】(" + buXiuProperties.getOpenHours() + ") 营业中 ^_^";
        }
    }
}
