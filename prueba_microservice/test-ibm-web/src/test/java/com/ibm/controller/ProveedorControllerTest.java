package com.ibm.controller;

import com.ibm.JsonToObjectsCreator;
import com.ibm.models.Proveedor;
import com.ibm.services.ProveedorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class ProveedorControllerTest extends JsonToObjectsCreator {

    @Mock
    private ProveedorService mockedProveedorService;
    private ProveedorController proveedorController;

    @BeforeEach
    public void setUp(){
        openMocks(this);
        proveedorController = new ProveedorController(mockedProveedorService);
    }

    @Test
    void GivenIdCliente5_WhenGetProveedoresListByIdCliente_ThenReturnsProveedorList() throws IOException {
        // Given
        int id_cliente = 5;
        List<Proveedor> proveedorList = proveedoresListIdCliente5();

        // When
        when(mockedProveedorService.getProveedoresListByIdCliente(id_cliente)).thenReturn(proveedorList);

        ResponseEntity<List<Proveedor>> responseEntity = proveedorController.getProveedoresListByIdCliente(id_cliente);

        responseEntity.getBody().stream()
                .map( proveedor -> proveedor.toString() )
                .forEach(System.out::println);
        System.out.println();
    }

    @Test
    void GivenIdCliente6_WhenGetProveedoresListByIdCliente_ThenReturnsProveedorList() throws IOException {
        // Given
        int id_cliente = 6;
        List<Proveedor> proveedorList = proveedoresListIdCliente6();

        // When
        when(mockedProveedorService.getProveedoresListByIdCliente(id_cliente)).thenReturn(proveedorList);

        ResponseEntity<List<Proveedor>> responseEntity = proveedorController.getProveedoresListByIdCliente(id_cliente);

        responseEntity.getBody().stream()
                .map( proveedor -> proveedor.toString() )
                .forEach(System.out::println);
        System.out.println();
    }

    @Test
    void GivenIdCliente7_WhenGetProveedoresListByIdCliente_ThenReturnsProveedorList() throws IOException {
        // Given
        int id_cliente = 7;
        List<Proveedor> proveedorList = proveedoresListIdCliente7();

        // When
        when(mockedProveedorService.getProveedoresListByIdCliente(id_cliente)).thenReturn(proveedorList);

        ResponseEntity<List<Proveedor>> responseEntity = proveedorController.getProveedoresListByIdCliente(id_cliente);

        responseEntity.getBody().stream()
                .map( proveedor -> proveedor.toString() )
                .forEach(System.out::println);
        System.out.println();
    }

    @Test
    void GivenIdCliente8_WhenGetProveedoresListByIdCliente_ThenReturnsProveedorList() throws IOException {
        // Given
        int id_cliente = 8;
        List<Proveedor> proveedorList = proveedoresListIdCliente8();

        // When
        when(mockedProveedorService.getProveedoresListByIdCliente(id_cliente)).thenReturn(proveedorList);

        ResponseEntity<List<Proveedor>> responseEntity = proveedorController.getProveedoresListByIdCliente(id_cliente);

        responseEntity.getBody().stream()
                .map( proveedor -> proveedor.toString() )
                .forEach(System.out::println);
        System.out.println();
    }

    @Test
    void GivenIdCliente9_WhenGetProveedoresListByIdCliente_ThenReturnsProveedorListEmpty() throws IOException {
        // Given
        int id_cliente = 9;
        List<Proveedor> proveedorList = proveedoresListIdCliente9();

        // When
        when(mockedProveedorService.getProveedoresListByIdCliente(id_cliente)).thenReturn(proveedorList);

        ResponseEntity<List<Proveedor>> responseEntity = proveedorController.getProveedoresListByIdCliente(id_cliente);

        responseEntity.getBody().stream()
                .map( proveedor -> proveedor.toString() )
                .forEach(System.out::println);
        System.out.println();
    }
}