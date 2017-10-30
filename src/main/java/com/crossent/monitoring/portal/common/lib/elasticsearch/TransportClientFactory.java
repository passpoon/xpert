package com.crossent.monitoring.portal.common.lib.elasticsearch;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.net.InetAddress;

import static org.apache.commons.lang.StringUtils.substringAfterLast;
import static org.apache.commons.lang.StringUtils.substringBeforeLast;


public class TransportClientFactory implements FactoryBean<TransportClient>, InitializingBean, DisposableBean {

	private static final Logger logger = LoggerFactory.getLogger(TransportClientFactory.class);

	private TransportClient client;
	private ElasticsearchProperties properties;
	static final String COLON = ":";

	public TransportClientFactory(){

	}
	public TransportClientFactory(ElasticsearchProperties properties){
		this.properties = properties;

	}

	@Override
	public void destroy() throws Exception {
		try {
			logger.info("Closing elasticSearch  client");
			if (client != null) {
				client.close();
			}
		} catch (final Exception e) {
			logger.error("Error closing ElasticSearch client: ", e);
		}
	}

	@Override
	public TransportClient getObject() throws Exception {
		return client;
	}

	@Override
	public Class<TransportClient> getObjectType() {
		return TransportClient.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		buildClient();
	}

	protected void buildClient() throws Exception {

		client = new PreBuiltTransportClient(settings());
		//Assert.hasText(clusterNodes, "[Assertion failed] clusterNodes settings missing.");
		for (String clusterNode : properties.getHosts()) {
			String hostName = substringBeforeLast(clusterNode, COLON);
			String port = substringAfterLast(clusterNode, COLON);
			Assert.hasText(hostName, "[Assertion failed] missing host name in 'clusterNodes'");
			Assert.hasText(port, "[Assertion failed] missing port in 'clusterNodes'");
			logger.info("adding transport node : " + clusterNode);
			client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(hostName), Integer.valueOf(port)));
		}
		client.connectedNodes();
	}

	private Settings settings() {

		return Settings.builder()
				.put("cluster.name", properties.getClusterName())
				.put("client.transport.sniff", properties.isClientTransportSniff())
				.put("client.transport.ignore_cluster_name", properties.isIgnoreClusterName())
				.put("client.transport.ping_timeout", properties.getPingTimeout())
				.put("client.transport.nodes_sampler_interval", properties.getNodesSampleInterval())
				.build();
	}


	public TransportClient getClient() {
		return client;
	}

	public void setClient(TransportClient client) {
		this.client = client;
	}

	public ElasticsearchProperties getProperties() {
		return properties;
	}

	public void setProperties(ElasticsearchProperties properties) {
		this.properties = properties;
	}

	public static String getCOLON() {
		return COLON;
	}
}
