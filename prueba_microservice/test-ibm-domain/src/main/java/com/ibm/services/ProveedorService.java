package com.ibm.services;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ibm.models.Proveedor;
import com.ibm.repository.ProveedorRepository;
import com.ibm.exception.ServiceErrorCatalog;
import com.ibm.exception.ServiceException;
import com.ibm.utils.DomainConstants;
import com.ibm.utils.Utility;

@Slf4j
@Service
public class ProveedorService {
    private ProveedorRepository proveedorRepository;
    public ProveedorService(ProveedorRepository proveedorRepository){
        this.proveedorRepository = proveedorRepository;
    }

    @PostConstruct
    public void initProveedor(){

        proveedorRepository.saveAll(Stream.of(new Proveedor(1, DomainConstants.LISTA_REFRESCOS.get(0),
                                                    Utility.getLocalDateTimeNow(), 5),

                                            new Proveedor(2, DomainConstants.LISTA_REFRESCOS.get(1),
                                                    Utility.getLocalDateTimeNow(), 5),

                                            new Proveedor(3, DomainConstants.LISTA_REFRESCOS.get(2),
                                                    Utility.getLocalDateTimeNow(), 6),

                                            new Proveedor(4, DomainConstants.LISTA_REFRESCOS.get(3),
                                                    Utility.getLocalDateTimeNow(), 7),

                                            new Proveedor(5, DomainConstants.LISTA_REFRESCOS.get(4),
                                                    Utility.getLocalDateTimeNow(), 8),

                                            new Proveedor(6, DomainConstants.LISTA_REFRESCOS.get(5),
                                                    Utility.getLocalDateTimeNow(), 6)
                )
                .collect(Collectors.toList()));
    }

    public List<Proveedor> getProveedoresListByIdCliente(int id_cliente) throws ServiceException {

        log.info(DomainConstants.LOG_SERVICE, id_cliente);

        if (Objects.isNull(id_cliente)) {
            throw new ServiceException.Builder(String.valueOf(ServiceErrorCatalog.ID_CLIENTE_IS_NOT_CORRRECT))
                    .withHttpStatus(HttpStatus.BAD_REQUEST).build();
        }

        return proveedorRepository.findByIdCliente(id_cliente);
    }
}
