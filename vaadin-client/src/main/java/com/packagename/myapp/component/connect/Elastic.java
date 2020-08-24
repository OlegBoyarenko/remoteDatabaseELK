package com.packagename.myapp.component.connect;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Connect to ELK
 */

@Component
public class Elastic {
    private RestHighLevelClient client;

    public Elastic(AppProperties appProperties) {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo( appProperties.getElkHost() + ":9200")
                .build();
        this.client = RestClients.create(clientConfiguration)
                .rest();
    }

    public RestHighLevelClient getClient() {
        return client;
    }

    public void setClient(RestHighLevelClient client) {
        this.client = client;
    }
}
