package com.example.azure.scsservicebus;

import com.azure.resourcemanager.AzureResourceManager;
import com.azure.spring.cloud.autoconfigure.implementation.resourcemanager.ServiceBusResourceMetadata;
import com.azure.spring.cloud.core.properties.resource.AzureResourceMetadata;
import com.azure.spring.cloud.resourcemanager.implementation.connectionstring.ServiceBusArmConnectionStringProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureConfiguration {

    @Bean
    ServiceBusArmConnectionStringProvider serviceBusArmConnectionStringProvider(
        AzureResourceManager azureResourceManager, ServiceBusResourceMetadata resourceMetadata) {
        return new MyServiceBusArmConnectionStringProvider(azureResourceManager, resourceMetadata, resourceMetadata.getName());
    }

    class MyServiceBusArmConnectionStringProvider extends ServiceBusArmConnectionStringProvider {
        public MyServiceBusArmConnectionStringProvider(AzureResourceManager azureResourceManager, AzureResourceMetadata azureResourceMetadata, String namespace) {
            super(azureResourceManager, azureResourceMetadata, namespace);
        }

        @Override
        public String getConnectionString() {
            return null;
        }
    }
}
