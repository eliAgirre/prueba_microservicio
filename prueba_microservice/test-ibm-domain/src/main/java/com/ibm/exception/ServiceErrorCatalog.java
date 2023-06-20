package com.ibm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * A catalog that contains the ServiceException types.
 *
 * They could be separated according to whatever is needed and may contain a default message if desired.
 */
@Getter
@AllArgsConstructor
public enum ServiceErrorCatalog {

	GENERIC_SERVICE_ERROR("An error has occurred processing your request"),
	ID_CLIENTE_IS_NOT_CORRRECT("El ID del cliente no es correcto"),
	ERROR_OBTAINING_PROVEEDORES("Ha ocurrido un error al obtener los proveedores ")
	;

	private String message;
}
