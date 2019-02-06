package net.kasteleiner.sample.tracing.backend.adapter.bootCli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootAppBackend {
    public static void main(String args[]) {
        SpringApplication.run(BootAppBackend.class, args);
    }
}
