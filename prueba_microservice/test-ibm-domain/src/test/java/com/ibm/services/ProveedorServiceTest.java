package com.ibm.services;

import com.ibm.exception.ServiceErrorCatalog;
import com.ibm.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ibm.repository.ProveedorRepository;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProveedorServiceTest {

    private ProveedorService mockedProveedorService;
    private ProveedorRepository mockedProveedorRepository;
    private ServiceException mockedServiceException;


    @BeforeEach
    public void setUp() {

        mockedProveedorRepository = mock(ProveedorRepository.class);
        mockedProveedorService = new ProveedorService(mockedProveedorRepository);
    }

    @Test
    void GivenIdCliente5_WhenGetProveedoresListByIdCliente_ThenReturnsProveedorList(){
        // Given
        int id_cliente = 5;

        // When
        mockedProveedorService.getProveedoresListByIdCliente(id_cliente);

        // Then
        verify(mockedProveedorRepository).findByIdCliente(id_cliente);
    }

    @Test
    void GivenIdCliente6_WhenGetProveedoresListByIdCliente_ThenReturnsProveedorList(){
        // Given
        int id_cliente = 6;

        // When
        mockedProveedorService.getProveedoresListByIdCliente(id_cliente);

        // Then
        verify(mockedProveedorRepository).findByIdCliente(id_cliente);
    }

    @Test
    void GivenIdCliente7_WhenGetProveedoresListByIdCliente_ThenReturnsProveedorList(){
        // Given
        int id_cliente = 7;

        // When
        mockedProveedorService.getProveedoresListByIdCliente(id_cliente);

        // Then
        verify(mockedProveedorRepository).findByIdCliente(id_cliente);
    }

    @Test
    void GivenIdCliente8_WhenGetProveedoresListByIdCliente_ThenReturnsProveedorList(){
        // Given
        int id_cliente = 8;

        // When
        mockedProveedorService.getProveedoresListByIdCliente(id_cliente);

        // Then
        verify(mockedProveedorRepository).findByIdCliente(id_cliente);
    }

    @Test
    void GivenIdCliente9_WhenGetProveedoresListByIdCliente_ThenReturnsProveedorListEmpty(){
        // Given
        int id_cliente = 9;

        // When
        mockedProveedorService.getProveedoresListByIdCliente(id_cliente);

        // Then
        verify(mockedProveedorRepository).findByIdCliente(id_cliente);
    }
}
