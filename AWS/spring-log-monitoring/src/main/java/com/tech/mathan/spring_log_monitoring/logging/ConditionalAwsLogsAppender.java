package com.tech.mathan.spring_log_monitoring.logging;

import ca.pjer.logback.AwsLogsAppender;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;

public class ConditionalAwsLogsAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

    private static final String ENABLED_PROPERTY = "awsLogsEnabled";

    private final AwsLogsAppender delegate = new AwsLogsAppender();

    private String logGroupName;
    private String logStreamName;
    private String logStreamUuidPrefix;
    private String logRegion;
    private String cloudWatchEndpoint;
    private int maxBatchLogEvents = 50;
    private long maxFlushTimeMillis = 0;
    private long maxBlockTimeMillis = 5000;
    private int retentionTimeDays = 0;
    private boolean verbose = true;
    private String accessKeyId;
    private String secretAccessKey;

    @Override
    public void start() {
        if (!Boolean.parseBoolean(getContextProperty(ENABLED_PROPERTY, "true"))) {
            addInfo("CloudWatch logging is disabled via awslogs.enabled=false");
            super.start();
            return;
        }

        delegate.setContext(getContext());
        delegate.setName(getName());
        delegate.setLogGroupName(logGroupName);
        delegate.setLogStreamName(logStreamName);
        delegate.setLogStreamUuidPrefix(logStreamUuidPrefix);
        delegate.setLogRegion(logRegion);
        delegate.setCloudWatchEndpoint(cloudWatchEndpoint);
        delegate.setMaxBatchLogEvents(maxBatchLogEvents);
        delegate.setMaxFlushTimeMillis(maxFlushTimeMillis);
        delegate.setMaxBlockTimeMillis(maxBlockTimeMillis);
        delegate.setRetentionTimeDays(retentionTimeDays);
        delegate.setVerbose(verbose);
        delegate.setAccessKeyId(accessKeyId);
        delegate.setSecretAccessKey(secretAccessKey);
        delegate.start();
        super.start();
    }

    @Override
    protected void append(ILoggingEvent eventObject) {
        if (delegate.isStarted()) {
            delegate.doAppend(eventObject);
        }
    }

    @Override
    public void stop() {
        delegate.stop();
        super.stop();
    }

    public void setLogGroupName(String logGroupName) {
        this.logGroupName = logGroupName;
    }

    public void setLogStreamName(String logStreamName) {
        this.logStreamName = logStreamName;
    }

    public void setLogStreamUuidPrefix(String logStreamUuidPrefix) {
        this.logStreamUuidPrefix = logStreamUuidPrefix;
    }

    public void setLogRegion(String logRegion) {
        this.logRegion = logRegion;
    }

    public void setCloudWatchEndpoint(String cloudWatchEndpoint) {
        this.cloudWatchEndpoint = cloudWatchEndpoint;
    }

    public void setMaxBatchLogEvents(int maxBatchLogEvents) {
        this.maxBatchLogEvents = maxBatchLogEvents;
    }

    public void setMaxFlushTimeMillis(long maxFlushTimeMillis) {
        this.maxFlushTimeMillis = maxFlushTimeMillis;
    }

    public void setMaxBlockTimeMillis(long maxBlockTimeMillis) {
        this.maxBlockTimeMillis = maxBlockTimeMillis;
    }

    public void setRetentionTimeDays(int retentionTimeDays) {
        this.retentionTimeDays = retentionTimeDays;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public void setSecretAccessKey(String secretAccessKey) {
        this.secretAccessKey = secretAccessKey;
    }

    private String getContextProperty(String key, String defaultValue) {
        if (getContext() == null) {
            return defaultValue;
        }

        String value = getContext().getProperty(key);
        return value == null ? defaultValue : value;
    }
}
