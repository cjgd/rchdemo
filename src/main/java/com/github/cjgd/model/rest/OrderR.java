package com.github.cjgd.model.rest;

import java.util.List;

import lombok.Data;

@Data
public class OrderR {
    private List<Long> productIds;
    private String email;
}
