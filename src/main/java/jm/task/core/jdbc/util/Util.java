package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    private static SessionFactory sessionFactory;
    private static final Configuration configuration = new Configuration();

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                throw new RuntimeException("Ошибка инициализации SessionFactory", e);
            }
        }
        return sessionFactory;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                configuration.getProperty("hibernate.connection.url"),
                configuration.getProperty("hibernate.connection.username"),
                configuration.getProperty("hibernate.connection.password")
        );
    }
}

