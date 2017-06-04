package ru.nsu.saltuk.factory;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Creates an object using the key
 */
public final class Factory {

    private final Logger log = Logger.getLogger(Factory.class.getName());
    private Properties properties;

    /**
     * Construct a Factory using stream with configs
     * @param config stream with configuration, making
     *          mapping key with classname
     * @throws IOException on error while reading from
     *          config stream
     */
    public Factory(InputStream config) throws IOException {
        log.info("Construct factory with config file " + config);

        if (config == null)
            throw new IllegalArgumentException("config should not be null");

        properties = new Properties();
        properties.load(config);
    }

    /**
     * Creates an object by mapped key from
     * configuration file
     * @param key this value will be used for
     *            getting classname from config
     * @return Object of class, mapped with key,
     * if this value exists, else returns null
     */
    public Object create(String key){

        log.debug("Creating object by key " + key);

        String className = properties.getProperty(key);
        if (className == null) {
            log.debug("property not found");
            return null;
        }

        log.debug("Fond class " + className);

        Object obj = null;
        try {
            log.debug("Trying to instantiate an object");
            Class aClass = Class.forName(className);
            obj = aClass.newInstance();

        } catch (ClassNotFoundException e) {
            log.warn("Class not found");
        } catch (InstantiationException e) {
            log.warn("Impossible to instantiate");
        } catch (IllegalAccessException e) {
            log.warn("Constructor is unreachable");
        }

        if (obj != null)
            log.debug("Success");
        return obj;
    }

}
