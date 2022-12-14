package com.example.demo.persistence;

import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.persistence.spi.ClassTransformer;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.sql.DataSource;
import java.net.URL;
import java.util.List;
import java.util.Properties;

public class PersistenceContext implements PersistenceUnitInfo {

    String persistenceUnitName;
    DataSource dataSourcePool;

    public PersistenceContext(String persistenceUnitName, DataSource dataSourcePool) {
        this.persistenceUnitName = persistenceUnitName;
        this.dataSourcePool = dataSourcePool;
    }

    XmlParser xmlParser = new XmlParser(); // TODO Переделать

    public PersistenceContext(String persistenceUnitName) {

        try {
            PersistenceContext tempContext = xmlParser.readPersistenceContext(persistenceUnitName);
            this.dataSourcePool = tempContext.getJtaDataSource() == null ?
                    tempContext.getNonJtaDataSource() : tempContext.getJtaDataSource();
            this.persistenceUnitName = persistenceUnitName;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public String getPersistenceUnitName() {
        return persistenceUnitName;
    }

    @Override
    public String getPersistenceProviderClassName() {
        return null;
    }

    @Override
    public PersistenceUnitTransactionType getTransactionType() {
        return PersistenceUnitTransactionType.JTA;
    }

    @Override
    public DataSource getJtaDataSource() {
        return dataSourcePool;
    }

    @Override
    public DataSource getNonJtaDataSource() {
        return null;
    }

    public DataSource getAnyDataSource() {
        return dataSourcePool;
    }

    @Override
    public List<String> getMappingFileNames() {
        return null;
    }

    @Override
    public List<URL> getJarFileUrls() {
        return null;
    }

    @Override
    public URL getPersistenceUnitRootUrl() {
        return null;
    }

    @Override
    public List<String> getManagedClassNames() {
        return null;
    }

    @Override
    public boolean excludeUnlistedClasses() {
        return false;
    }

    @Override
    public SharedCacheMode getSharedCacheMode() {
        return null;
    }

    @Override
    public ValidationMode getValidationMode() {
        return null;
    }

    @Override
    public Properties getProperties() {
        return null;
    }

    @Override
    public String getPersistenceXMLSchemaVersion() {
        return null;
    }

    @Override
    public ClassLoader getClassLoader() {
        return null;
    }

    @Override
    public void addTransformer(ClassTransformer classTransformer) {

    }

    @Override
    public ClassLoader getNewTempClassLoader() {
        return null;
    }
}
