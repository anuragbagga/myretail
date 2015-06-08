package com.myretail.router;

import java.util.List;

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
import java.util.logging.Logger;

@Path("/item")
public class ItemRouter {

	private static final Logger logger = Logger.getLogger(ItemRouter.class.getName());

	/* 
	 * This method will return all items in inventory.
	 * No parameters required. 
	 * Return - List of ItemDetailsResponse retrieved from Database.
	 * 
	 */

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllItems()
			throws ItemNotFoundException {
		logger.info("Call Trace - ItemRouter.getAllItems() - Start");
		logger.info("*** Retrieving all items from database ***");
		SqlSession session = DBSessionFactory.getDBConnection();
		ListItemDetailsResponse response = new ListItemDetailsResponse();
		ContactMapper mapper = session.getMapper(ContactMapper.class);
		List<ItemDetailsResponse> itemList = mapper.selectAll();
		response.setList(itemList);
		if (itemList.size()!=0){
			logger.info("Total Count of items :- "+itemList.size());
			for (ItemDetailsResponse item : itemList) {
				System.out.println(item.getId() + ":"
						+ item.getName() + ":" + item.getPrice());
				logger.info(item.getId() + ":"
						+ item.getName() + ":" + item.getPrice());
			}
		}
		else {
			logger.info("No Items found in database");
			throw new ItemNotFoundException("No Items found in database", ErrorConstants.NO_RECORDS_FOUND_DB);
		}
		session.close();
		logger.info("Call Trace - ItemRouter.getAllItems() - End");
		return Response.status(200).entity(response).build();
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON})
	public Response findById(@PathParam("id") int id) throws ItemNotFoundException {	
		logger.info("Call Trace - ItemRouter.findById() - Start");
		logger.info("Searching Database with id : "+id);
		ListItemDetailsResponse response = new ListItemDetailsResponse();
		SqlSession session = DBSessionFactory.getDBConnection();
		ContactMapper mapper = session.getMapper(ContactMapper.class);
		List<ItemDetailsResponse> itemList = mapper.select(id);
		response.setList(itemList);
		try{
			if (itemList.size()!=0){
				logger.info("Total Count of items :- "+itemList.size());
				for (ItemDetailsResponse item : itemList) {
					System.out.println(item.getId() + ":"
							+ item.getName() + ":" + item.getPrice());
				}
			}
			else {
				logger.info("No Items found in database based on given id :-"+id);
				throw new ItemNotFoundException("Invalid Item Id", ErrorConstants.INVALID_ITEM_ID);
			}
		}catch (Exception e){
			e.printStackTrace();
			logger.info("No Items found in database based on given id :-"+id );
			throw new ItemNotFoundException("Invalid Item Id", ErrorConstants.INVALID_ITEM_ID);
		}
		session.close();
		logger.info("Call Trace - ItemRouter.findById() - End");
		return Response.status(200).entity(response).build();
	}

}
