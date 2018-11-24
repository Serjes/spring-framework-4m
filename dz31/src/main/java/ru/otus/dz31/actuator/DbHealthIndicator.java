package ru.otus.dz31.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.actuate.jdbc.DataSourceHealthIndicator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DbHealthIndicator extends AbstractHealthIndicator {

    @Autowired
    private DataSource dataSource;

    public DataSourceHealthIndicator dbHealthIndicator() {
        DataSourceHealthIndicator indicator = new DataSourceHealthIndicator(dataSource);
        return indicator;
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        Health health = dbHealthIndicator().health();
        Status status = health.getStatus();
        if (status != null && "DOWN".equals(status.getCode())) {
            builder.withDetail("DB", "not good").down();
        } else {
            builder.withDetail("DB", "Ok").up();
        }
    }

}
