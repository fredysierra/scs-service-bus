package com.example.azure.scsservicebus;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Properties;
import java.util.stream.StreamSupport;

@Component
public class ConsumerConfigBreakingChangeRepairMan {

    public ConsumerConfigBreakingChangeRepairMan(ConfigurableEnvironment environment) {
        super();
        Properties properties = new Properties();
        StreamSupport.stream(environment.getPropertySources().spliterator(), false)
                .filter(EnumerablePropertySource.class::isInstance)
                .map(ps -> ((EnumerablePropertySource) ps).getPropertyNames())
                .flatMap(Arrays::stream)
                .distinct()
                .filter(p -> p.startsWith("spring.cloud.stream.bindings.") && p.endsWith(".group"))
                .map(p -> p.substring(28, p.length() -6))
                .map(p -> "spring.cloud.stream.servicebus.bindings." + p + ".consumer.entity-type")
                .forEach(p -> properties.put(p, "topic"));

        environment.getPropertySources().addLast(new PropertiesPropertySource("ThanksMS", properties));
    }

}
