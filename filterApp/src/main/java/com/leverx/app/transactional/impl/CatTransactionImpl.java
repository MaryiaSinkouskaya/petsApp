package com.leverx.app.transactional.impl;

import com.leverx.app.dto.response.ResponseEntity;
import com.leverx.app.entity.request.cat.RequestCat;
import com.leverx.app.entity.response.cat.ResponseCat;
import com.leverx.app.service.CatService;
import com.leverx.app.transactional.CatTransaction;
import lombok.Builder;
import lombok.Data;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;
import static org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes;

@Data
@Builder
public class CatTransactionImpl implements CatTransaction {

    private final CatService catService;
    private final RequestCat requestCat;
    private static final String ID = "cat_id";

    @Override
    public ResponseEntity save() {
        ResponseCat responseCat = catService.create(requestCat);
        currentRequestAttributes().setAttribute(ID, responseCat.getId(),
                SCOPE_REQUEST);
        return responseCat;
    }

    @Override
    public void rollback() {
        long id = (long) currentRequestAttributes().getAttribute(ID,
                SCOPE_REQUEST);
        catService.delete(id);
    }
}
