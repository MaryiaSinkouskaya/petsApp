package com.leverx.app.service.impl;

import com.leverx.app.entity.request.DTO.RequestDTO;
import com.leverx.app.entity.response.DTO.ResponseDTO;
import com.leverx.app.entity.response.DTO.ResponseEntity;
import com.leverx.app.entity.response.DTO.ResponseListDTO;
import com.leverx.app.entity.response.cat.ResponseCat;
import com.leverx.app.entity.response.dog.ResponseDog;
import com.leverx.app.entity.response.user.ResponseUser;
import com.leverx.app.service.CatService;
import com.leverx.app.service.CommonService;
import com.leverx.app.service.DogService;
import com.leverx.app.service.UserService;
import com.leverx.app.transactional.Transaction;
import com.leverx.app.transactional.builder.TransactionalBuilder;
import com.leverx.app.transactional.impl.UserTransactionImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;

@Service
@RequiredArgsConstructor
@Log4j2
public class CommonServiceImpl implements CommonService {

    private final UserService userService;
    private final CatService catService;
    private final DogService dogService;

    @Override
    public ResponseListDTO findAll() {
        return new ResponseListDTO(
                dogService.findAll(),
                catService.findAll(),
                userService.findAll());
    }

    @Override
    public ResponseDTO createAll(RequestDTO requestDTO) {
        ResponseUser user = createUser(requestDTO);
        List<Transaction> transactions = getPetsTransactional(getPetsWithSettedUser(user, requestDTO));
        List<Transaction> successfulTransactions = new LinkedList<>();
        List<ResponseEntity> responseEntities = new ArrayList<>();
        responseEntities.add(user);

        transactions.forEach(transaction -> {
            try {
                responseEntities.add(transaction.add());
                successfulTransactions.add(transaction);
            } catch (HttpClientErrorException | HttpServerErrorException exception) {
                successfulTransactions.forEach(Transaction::rollback);
                throw new HttpClientErrorException(exception.getStatusCode());
            }
        });
        return convertToResponseDTO(responseEntities);
    }

    private ResponseDTO convertToResponseDTO(List<ResponseEntity> entities) {
        ResponseDTO responseDTO = new ResponseDTO();
        entities.forEach(entity ->
        {
            if (entity instanceof ResponseUser) {
                responseDTO.setUser((ResponseUser) entity);
            } else if (entity instanceof ResponseCat) {
                responseDTO.setCat((ResponseCat) entity);
            } else if (entity instanceof ResponseDog) {
                responseDTO.setDog((ResponseDog) entity);
            }
        });
        return responseDTO;
    }

    private List<Transaction> getPetsTransactional(RequestDTO requestDTO) {
        return asList(
                TransactionalBuilder
                        .buildCatTransaction(requestDTO.getCat(), catService),
                TransactionalBuilder
                        .buildDogTransaction(requestDTO.getDog(), dogService)
        );
    }

    private ResponseUser createUser(RequestDTO requestDTO) {
        UserTransactionImpl userTransactional = TransactionalBuilder
                .buildUserTransaction(requestDTO.getUser(), userService);
        return userTransactional.add();
    }

    private RequestDTO getPetsWithSettedUser(ResponseUser user, RequestDTO requestDTO) {
        requestDTO.getCat().setUser(user);
        requestDTO.getDog().setUser(user);
        return requestDTO;
    }
}
