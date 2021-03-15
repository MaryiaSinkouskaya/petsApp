package com.leverx.app.service.impl;

import com.leverx.app.dto.request.RequestDto;
import com.leverx.app.dto.response.ResponseDto;
import com.leverx.app.dto.response.ResponseListDto;
import com.leverx.app.entity.response.cat.ResponseCat;
import com.leverx.app.entity.response.dog.ResponseDog;
import com.leverx.app.entity.response.user.ResponseUser;
import com.leverx.app.service.CatService;
import com.leverx.app.service.CommonService;
import com.leverx.app.service.DogService;
import com.leverx.app.service.UserService;
import com.leverx.app.transactional.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.LinkedList;
import java.util.List;

import static com.leverx.app.transactional.builder.TransactionalBuilder.buildCatTransaction;
import static com.leverx.app.transactional.builder.TransactionalBuilder.buildDogTransaction;
import static com.leverx.app.transactional.builder.TransactionalBuilder.buildUserTransaction;

@Service
@RequiredArgsConstructor
@Log4j2
public class CommonServiceImpl implements CommonService {

    private final UserService userService;
    private final CatService catService;
    private final DogService dogService;

    @Override
    public ResponseListDto findAll() {
        return new ResponseListDto(
                dogService.findAll(),
                catService.findAll(),
                userService.findAll());
    }

    @Override
    public ResponseDto createAll(RequestDto requestDTO) {
        ResponseUser user = createUser(requestDTO);
        List<Transaction> successfulTransactions = new LinkedList<>();
        ResponseDto responseDTO = new ResponseDto();
        responseDTO.setUser(user);
        try {
            Transaction currentTransaction = buildDogTransaction(requestDTO.getDog(), dogService);
            responseDTO.setDog((ResponseDog) currentTransaction.save());
            successfulTransactions.add(currentTransaction);

            currentTransaction = buildCatTransaction(requestDTO.getCat(), catService);
            responseDTO.setCat((ResponseCat) currentTransaction.save());
            successfulTransactions.add(currentTransaction);
        } catch (HttpClientErrorException | HttpServerErrorException exception) {
            successfulTransactions.forEach(Transaction::rollback);
            throw new HttpClientErrorException(exception.getStatusCode());
        }
        return responseDTO;
    }

    private ResponseUser createUser(RequestDto requestDTO) {
        ResponseUser user = buildUserTransaction(requestDTO.getUser(), userService).save();
        attachUserToRequestPets(user, requestDTO);
        return user;

    }

    private void attachUserToRequestPets(ResponseUser user, RequestDto requestDTO) {
        requestDTO.getCat().setUser(user);
        requestDTO.getDog().setUser(user);
    }
}
