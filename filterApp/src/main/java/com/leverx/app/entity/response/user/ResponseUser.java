package com.leverx.app.entity.response.user;

import com.leverx.app.entity.response.DTO.ResponseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseUser implements ResponseEntity {

    private long id;

    private String name;

}
