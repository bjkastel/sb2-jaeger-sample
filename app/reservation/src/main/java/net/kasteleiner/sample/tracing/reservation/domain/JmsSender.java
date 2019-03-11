package net.kasteleiner.sample.tracing.reservation.domain;

public interface JmsSender {
    void sendSms(String mobileNumer, String text);
}
