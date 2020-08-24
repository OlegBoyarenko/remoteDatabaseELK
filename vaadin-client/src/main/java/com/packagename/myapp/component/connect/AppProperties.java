package com.packagename.myapp.component.connect;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private String rmiHost;
    private String elkHost;

    public String getRmiHost() {
        return rmiHost;
    }

    public void setRmiHost(String rmiHost) {
        this.rmiHost = rmiHost;
    }

    public String getElkHost() {
        return elkHost;
    }

    public void setElkHost(String elkHost) {
        this.elkHost = elkHost;
    }
}
