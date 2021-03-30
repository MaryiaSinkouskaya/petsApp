package com.leverx.app.service.odata;

import java.util.List;

public interface OdataCommonService<T> {

    T find(long id);

    List<T> findAll();
}