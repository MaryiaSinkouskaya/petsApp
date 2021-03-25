package com.leverx.app.config.odata;

import org.apache.olingo.odata2.annotation.processor.core.ListsProcessor;
import org.apache.olingo.odata2.annotation.processor.core.datasource.AnnotationValueAccess;
import org.apache.olingo.odata2.annotation.processor.core.datasource.DataSource;
import org.apache.olingo.odata2.annotation.processor.core.datasource.ValueAccess;
import org.apache.olingo.odata2.api.processor.ODataSingleProcessor;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAContext;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAServiceFactory;
import org.apache.olingo.odata2.jpa.processor.api.exception.ODataJPARuntimeException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;

import static javax.persistence.Persistence.createEntityManagerFactory;

@Component
public class ODataJPAFactoryImplApp extends ODataJPAServiceFactory {

    private static final String PERSISTENCE_UNIT_NAME = "odataJPAPersistence";

    @Override
    public ODataJPAContext initializeODataJPAContext() throws ODataJPARuntimeException {
        ODataJPAContext oDatJPAContext = this.getODataJPAContext();
        EntityManagerFactory emf = createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        oDatJPAContext.setEntityManagerFactory(emf);
        oDatJPAContext.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);
        return oDatJPAContext;
    }

    @Override
    public ODataSingleProcessor createCustomODataProcessor(ODataJPAContext oDataJPAContext) {
        ValueAccess valueAccess = new AnnotationValueAccess();
        DataSource dataSource = new AppDataSource();
        return new ListsProcessor(dataSource, valueAccess);
    }
}