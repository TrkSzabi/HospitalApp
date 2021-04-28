package com.hospital.util;


import com.hospital.model.MedicalEmployee;
import com.hospital.model.MedicalRecords;
import com.hospital.model.Patient;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {

    public static SessionFactory instance = null;

    public static SessionFactory getSessionFactory() {
        if (instance == null) {
            instantiateSessionFactory();
        }
        return instance;
    }

    private static void instantiateSessionFactory() {
        Configuration configuration = new Configuration();
        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        settings.put(Environment.URL, "jdbc:mysql://localhost:3306/hospital_app?useSSL=false");
        settings.put(Environment.USER, "root");
        settings.put(Environment.PASS, "emporioarmani1988");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL55Dialect");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        settings.put(Environment.HBM2DDL_AUTO, "update");

        configuration.addAnnotatedClass(Patient.class);
        configuration.addAnnotatedClass(MedicalEmployee.class);
        configuration.addAnnotatedClass(MedicalRecords.class);

        configuration.setProperties(settings);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        instance = configuration.buildSessionFactory(serviceRegistry);

    }
}

