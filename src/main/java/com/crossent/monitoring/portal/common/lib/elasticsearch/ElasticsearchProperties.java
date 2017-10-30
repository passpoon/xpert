package com.crossent.monitoring.portal.common.lib.elasticsearch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import java.util.Arrays;

@Validated
@ConfigurationProperties("elasticsearch")
public class ElasticsearchProperties {


    private String[] hosts;


    private String clusterName;


    private boolean ignoreClusterName;


    private String nodesSampleInterval;


    private String pingTimeout;


    private boolean clientTransportSniff;

    public String[] getHosts() {
        return hosts;
    }

    public void setHosts(String[] hosts) {
        this.hosts = hosts;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public boolean isIgnoreClusterName() {
        return ignoreClusterName;
    }

    public void setIgnoreClusterName(boolean ignoreClusterName) {
        this.ignoreClusterName = ignoreClusterName;
    }

    public String getNodesSampleInterval() {
        return nodesSampleInterval;
    }

    public void setNodesSampleInterval(String nodesSampleInterval) {
        this.nodesSampleInterval = nodesSampleInterval;
    }

    public String getPingTimeout() {
        return pingTimeout;
    }

    public void setPingTimeout(String pingTimeout) {
        this.pingTimeout = pingTimeout;
    }

    public boolean isClientTransportSniff() {
        return clientTransportSniff;
    }

    public void setClientTransportSniff(boolean clientTransportSniff) {
        this.clientTransportSniff = clientTransportSniff;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ElasticsearchProperties{");
        sb.append("hosts=").append(Arrays.toString(hosts));
        sb.append(", clusterName='").append(clusterName).append('\'');
        sb.append(", ignoreClusterName=").append(ignoreClusterName);
        sb.append(", nodesSampleInterval='").append(nodesSampleInterval).append('\'');
        sb.append(", pingTimeout='").append(pingTimeout).append('\'');
        sb.append(", clientTransportSniff=").append(clientTransportSniff);
        sb.append('}');
        return sb.toString();
    }
}
