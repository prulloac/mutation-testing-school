package com.example.demo;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.PostgreSQLContainer;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(initializers = ContainerDatabaseTest.DataSourceInitializer.class)
public abstract class ContainerDatabaseTest {

    // Annotation used in conjunction with the Testcontainers annotation to mark containers that should be managed by the Testcontainers extension.
    private static final PostgreSQLContainer<?> database;

    static {
        database = new PostgreSQLContainer<>("postgres:16-alpine");
        database.start();
    }

    public static class DataSourceInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        /*
           `initialize` function allows us to set properties dynamically. Since the DataSource is initialized dynamically,
            we need to set url, username, and password that is provided/set by the testcontainers.
         */
        @Override
        public void initialize(@NotNull ConfigurableApplicationContext applicationContext) {
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                    applicationContext,
                    "spring.test.database.replace=none", // Tells Spring Boot not to start in-memory db for tests.
                    "spring.datasource.url=" + database.getJdbcUrl(),
                    "spring.datasource.username=" + database.getUsername(),
                    "spring.datasource.password=" + database.getPassword()
            );
        }
    }
}