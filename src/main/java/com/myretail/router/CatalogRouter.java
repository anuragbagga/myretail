package com.myretail.router;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.ibatis.session.SqlSession;

import com.myretail.exception.ItemNotFoundException;
import com.myretail.mapper.ContactMapper;
import com.myretail.model.ItemDetailsResponse;
import com.myretail.model.ListItemDetailsResponse;
import com.myretail.utils.ErrorConstants;
import com.myretail.utils.DBSessionFactory;


@Path("/catalog")

public class CatalogRouter {

	private static final Logger logger = Logger.getLogger(CatalogRouter.class.getName());
	
	@GET
	@Path("{catalog_id}")
	@Produces({ MediaType.APPLICATION_JSON})
	public Response getItemsbyCatalog(@PathParam("catalog_id") String catalog) throws ItemNotFoundException {	
		logger.info("Call Trace - ItemRouter.getItemsbyCatalog() - Start");
		logger.info("Catalog name:-"+catalog);
		ListItemDetailsResponse response = new ListItemDetailsResponse();
		SqlSession session = DBSessionFactory.getDBConnection();
		ContactMapper mapper = session.getMapper(ContactMapper.class);
		List<ItemDetailsResponse> itemList = mapper.selectOnCatalog(catalog);
		response.setList(itemList);
		if (itemList.size()!=0){
			for (ItemDetailsResponse item : itemList) {
				System.out.println(item.getId() + ":"
						+ item.getName() + ":" + item.getPrice());
			}
		}
		else {
			throw new ItemNotFoundException("Invalid Catalog Id", ErrorConstants.INVALID_CATALOG_ID);
		}
		session.close();
		logger.info("Call Trace - ItemRouter.getItemsbyCatalog() - End");
		return Response.status(200).entity(response).build();
	}
}
