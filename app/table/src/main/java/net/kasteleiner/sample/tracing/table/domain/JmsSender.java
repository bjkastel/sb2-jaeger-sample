package net.kasteleiner.sample.tracing.table.domain;

public interface JmsSender {
    void sendSms(String mobileNumer, String text);
}
