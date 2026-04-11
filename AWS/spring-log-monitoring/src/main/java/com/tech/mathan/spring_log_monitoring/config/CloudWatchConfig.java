package com.tech.mathan.spring_log_monitoring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudwatch.CloudWatchClient;
import software.amazon.awssdk.services.cloudwatchlogs.CloudWatchLogsClient;

@Configuration
@ConditionalOnProperty(name = "aws.cloudwatch.enabled", havingValue = "true", matchIfMissing = true)
public class CloudWatchConfig {

	private static final Logger logger = LoggerFactory.getLogger(CloudWatchConfig.class);

	@Value("${aws.cloudwatch.region:us-east-1}")
	private String region;

	@Bean
	public AwsCredentialsProvider awsCredentialsProvider() {
		return DefaultCredentialsProvider.create();
	}

	@Bean
	public Region awsRegion() {
		return Region.of(region);
	}

	@Bean
	public CloudWatchLogsClient cloudWatchLogsClient(AwsCredentialsProvider credentialsProvider, Region awsRegion) {
		logger.info("Initializing CloudWatch Logs client for region: {}", region);
		return CloudWatchLogsClient.builder()
				.region(awsRegion)
				.credentialsProvider(credentialsProvider)
				.build();
	}

	@Bean
	public CloudWatchClient cloudWatchClient(AwsCredentialsProvider credentialsProvider, Region awsRegion) {
		logger.info("Initializing CloudWatch Metrics client for region: {}", region);
		return CloudWatchClient.builder()
				.region(awsRegion)
				.credentialsProvider(credentialsProvider)
				.build();
	}
}
