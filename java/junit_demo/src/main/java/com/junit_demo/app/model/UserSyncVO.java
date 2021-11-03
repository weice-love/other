package com.junit_demo.app.model;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2021/11/3 14:57
 */
public class UserSyncVO {

    private String instanceId;
    private String topic;
    private String msgId;
    private String bornHost;
    private String bornTimestamp;
    private String storeTimestamp;
    private String reconsumeTimes;
    private String properties;
    private String body;
    private String bodyCRC;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getBornHost() {
        return bornHost;
    }

    public void setBornHost(String bornHost) {
        this.bornHost = bornHost;
    }

    public String getBornTimestamp() {
        return bornTimestamp;
    }

    public void setBornTimestamp(String bornTimestamp) {
        this.bornTimestamp = bornTimestamp;
    }

    public String getStoreTimestamp() {
        return storeTimestamp;
    }

    public void setStoreTimestamp(String storeTimestamp) {
        this.storeTimestamp = storeTimestamp;
    }

    public String getReconsumeTimes() {
        return reconsumeTimes;
    }

    public void setReconsumeTimes(String reconsumeTimes) {
        this.reconsumeTimes = reconsumeTimes;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBodyCRC() {
        return bodyCRC;
    }

    public void setBodyCRC(String bodyCRC) {
        this.bodyCRC = bodyCRC;
    }
}
