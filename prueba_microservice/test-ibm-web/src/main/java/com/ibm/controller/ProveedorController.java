package com.ibm.controller;

import com.ibm.exception.ServiceException;
import com.ibm.models.Proveedor;
import com.ibm.services.ProveedorService;
import com.ibm.utils.WebConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping(value="/api/proveedores")
@Validated
public class ProveedorController {
    private ProveedorService proveedorService;
    public ProveedorController(ProveedorService proveedorService){
        this.proveedorService = proveedorService;
    }
    @GetMapping(WebConstants.PATH_FILTER)
    public ResponseEntity<List<Proveedor>> getProveedoresListByIdCliente(@NotBlank @RequestParam(value = "id_cliente") int id_cliente) throws ServiceException {

        log.info(WebConstants.LOG_CONTROLLER, id_cliente);

        if(Objects.isNull(id_cliente)){
            return ResponseEntity.badRequest().build();
        }

        List<Proveedor> pricesList;

        try {
            pricesList = proveedorService.getProveedoresListByIdCliente(id_cliente);
        }
        catch (ServiceException e) {
            throw new ServiceException(e.getCode(), e.getHttpStatus(), e.getMessage(), e.getCause(), e.getParams());
        }

        return ResponseEntity.ok(pricesList);
    }
}
