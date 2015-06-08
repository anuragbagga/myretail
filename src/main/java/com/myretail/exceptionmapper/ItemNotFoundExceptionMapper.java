package com.myretail.exceptionmapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.myretail.exception.ItemNotFoundException;
import com.myretail.model.ErrorResponse;

@Provider
public class ItemNotFoundExceptionMapper implements ExceptionMapper<ItemNotFoundException>{


	public Response toResponse(ItemNotFoundException itemNotFoundException) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorId(itemNotFoundException.getErrorId());
		errorResponse.setErrorCode(itemNotFoundException.getMessage());
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
				errorResponse).type(
						MediaType.APPLICATION_XML).build();
	}

}
