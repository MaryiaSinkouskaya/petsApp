package com.leverx.app.service.odata;

import org.apache.olingo.odata2.api.edm.EdmException;
import org.apache.olingo.odata2.api.exception.ODataNotImplementedException;

import java.util.List;

public interface OdataCommonService<T, P, R> {

    T find(long id);

    List<T> findAll();

    P readRelatedData(Object sourceData, String targetEntityName) throws EdmException;

    R save(T data) throws ODataNotImplementedException;

    void delete(long id) throws ODataNotImplementedException;

    T getNewEdm() throws ODataNotImplementedException;
}