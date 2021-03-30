package com.leverx.app.service.odata;

import com.leverx.app.config.odata.edm.UserEdm;
import org.apache.olingo.odata2.api.edm.EdmException;

import java.util.List;

public interface OdataCommonService<T, P> {

    T find(long id);

    List<T> findAll();

    P readRelatedData(Object sourceData, String targetEntityName)throws EdmException;
}