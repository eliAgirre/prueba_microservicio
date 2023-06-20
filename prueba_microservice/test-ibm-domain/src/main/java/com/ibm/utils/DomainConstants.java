package com.ibm.utils;

import lombok.experimental.FieldNameConstants;

import java.util.Arrays;
import java.util.List;

@FieldNameConstants
public class DomainConstants {

    private DomainConstants() {}
    public static final List<String> LISTA_REFRESCOS = Arrays.asList("Coca-Cola", "Pepsi", "Redbull", "Fanta", "Casera", "Trina");
    public static final String LOG_SERVICE = "getProveedoresListByIdCliente id_cliente {} ";
}
