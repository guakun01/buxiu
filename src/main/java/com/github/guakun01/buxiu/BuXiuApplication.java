package com.github.guakun01.buxiu;

import com.github.guakun01.buxiu.actuator.SalesMetrics;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.core.instrument.logging.LoggingMeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Random;

@SpringBootApplication
@EnableScheduling
public class BuXiuApplication {

	private static Logger logger = LoggerFactory.getLogger(BuXiuApplication.class);

	private Random random = new Random();

	@Autowired
	private SalesMetrics salesMetrics;

	public static void main(String[] args) {
		SpringApplication.run(BuXiuApplication.class, args);
	}

	@Bean
	public MeterRegistry customMeterRegistry() {
		CompositeMeterRegistry meterRegistry = new CompositeMeterRegistry();
		meterRegistry.add(new SimpleMeterRegistry());
		meterRegistry.add(new LoggingMeterRegistry());
		return meterRegistry;
	}

	@Scheduled(fixedRate = 3000, initialDelay = 500)
	public void periodcallyMakeAnOrder() {
		int amount = random.nextInt(100);
		salesMetrics.makeNewOrder(amount);
		logger.info("有顾客下单了，订单金额 {} 元。", amount);
	}

}
