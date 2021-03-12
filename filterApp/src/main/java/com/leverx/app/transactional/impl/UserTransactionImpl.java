package com.leverx.app.transactional.impl;

import com.leverx.app.entity.request.user.RequestUser;
import com.leverx.app.entity.response.user.ResponseUser;
import com.leverx.app.service.UserService;
import com.leverx.app.transactional.UserTransaction;
import lombok.Builder;
import lombok.Data;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;
import static org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes;

@Data
@Builder
public class UserTransactionImpl implements UserTransaction {

    private final UserService userService;
    private final RequestUser requestUser;
    private static final String ID = "user_id";

    @Override
    public ResponseUser save() {
        ResponseUser responseUser = userService.create(requestUser);
        currentRequestAttributes().setAttribute(ID, responseUser.getId(),
                SCOPE_REQUEST);
        return responseUser;
    }

    @Override
    public void rollback() {
        long id = (long) currentRequestAttributes().getAttribute(ID,
                SCOPE_REQUEST);
        userService.delete(id);
    }
}
