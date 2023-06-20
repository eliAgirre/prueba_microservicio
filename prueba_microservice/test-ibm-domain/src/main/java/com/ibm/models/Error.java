package com.ibm.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class Error implements Serializable {
    private String code;
    private String message;
    private List<String> errors;
}
