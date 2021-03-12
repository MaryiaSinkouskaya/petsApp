package com.leverx.app.service.impl;

import com.leverx.app.dto.request.RequestDto;
import com.leverx.app.dto.response.ResponseDto;
import com.leverx.app.dto.response.ResponseListDto;
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
import static java.util.Arrays.asList;

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
        List<Transaction> transactions = getTransactions(attachUser(user, requestDTO));
        List<Transaction> successfulTransactions = new LinkedList<>();
        ResponseDto responseDTO = new ResponseDto();
        responseDTO.setUser(user);
        transactions.forEach(transaction -> {
            try {
                responseDTO.add(transaction.save());
                successfulTransactions.add(transaction);
            } catch (HttpClientErrorException | HttpServerErrorException exception) {
                successfulTransactions.forEach(Transaction::rollback);
                throw new HttpClientErrorException(exception.getStatusCode());
            }
        });
        return responseDTO;
    }

    private List<Transaction> getTransactions(RequestDto requestDTO) {

        return asList(
                buildCatTransaction(requestDTO.getCat(), catService),
                buildDogTransaction(requestDTO.getDog(), dogService)
        );
    }

    private ResponseUser createUser(RequestDto requestDTO) {
        return buildUserTransaction(requestDTO.getUser(), userService).save();
    }

    private RequestDto attachUser(ResponseUser user, RequestDto requestDTO) {
        requestDTO.getCat().setUser(user);
        requestDTO.getDog().setUser(user);
        return requestDTO;
    }
}
