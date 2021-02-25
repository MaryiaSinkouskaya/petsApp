package com.leverx.app.service.impl;

import com.leverx.app.service.CatService;
import com.leverx.app.service.CommonService;
import com.leverx.app.service.DogService;
import com.leverx.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.web.client.HttpClientErrorException.NotFound;
import static org.springframework.web.client.HttpClientErrorException.Unauthorized;

@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService {

    private final UserService userService;
    private final CatService catService;
    private final DogService dogService;


    @Override
    public ResponseEntity<List<Object>> findAll() {
        try {
            List<Object> list = asList(
                    catService.findAll(),
                    dogService.findAll(),
                    userService.findAll()
            );
            return ResponseEntity.of(Optional.of(list));
        } catch (Unauthorized exception) {
            return ResponseEntity.status(UNAUTHORIZED).build();
        } catch (NotFound exception) {
            return ResponseEntity.status(NOT_FOUND).build();
        } catch (Exception exception) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
