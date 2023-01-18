package com.example.ebankingbackend.dtos;

import com.example.ebankingbackend.entities.Customer;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class CustomerDTO {

    private Long id;
    private String name;
    private String email;

}
