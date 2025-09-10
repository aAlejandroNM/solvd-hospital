package com.solvd.hospital.annotation;

import java.lang.reflect.Method;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuditUtil {
    private static final Logger LOGGER = LogManager.getLogger(AuditUtil.class);

    public static void logAuditableMethods(Class<?> clazz) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Auditable.class)) {
                Auditable auditable = method.getAnnotation(Auditable.class);
                LOGGER.info("Método auditable detectado: " + method.getName() + " | Acción: " + auditable.action());
            }
        }
    }
}

