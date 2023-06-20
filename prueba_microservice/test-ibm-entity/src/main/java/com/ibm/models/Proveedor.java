package com.ibm.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Proveedor implements Serializable {

    private static final long serialVersionUID = -3513241031219979141L;

    @Id
    @Column(name="ID_PROVEEDOR")
    private int idProveedor;

    @Column(name="NOMBRE")
    private String nombre;

    @Column(name="FECHA_ALTA")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime fechaAlta;

    @Column(name="ID_CLIENTE")
    private int idCliente;
}
