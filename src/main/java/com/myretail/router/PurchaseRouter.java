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
import com.myretail.model.ItemResponse;
import com.myretail.model.ListItemDetailsResponse;
import com.myretail.utils.DBSessionFactory;
import com.myretail.utils.ErrorConstants;

@Path("/addtocart")

public class PurchaseRouter {
	
	private static final Logger logger = Logger.getLogger(ItemRouter.class.getName());
	
	@GET
	@Path("{id}/buy/{quantity}")
	@Produces({ MediaType.APPLICATION_JSON})
	public Response addToCart(@PathParam("id") int id,@PathParam("quantity") int quantity) throws ItemNotFoundException {	
		logger.info("Call Trace - PurchaseRouter.findById() - Start");
		logger.info("Searching Database with id : "+id);
		logger.info("Purchasing Quantity : "+ quantity);
		ItemResponse itemresponse = new ItemResponse();
		ListItemDetailsResponse response = new ListItemDetailsResponse();
		SqlSession session = DBSessionFactory.getDBConnection();
		ContactMapper mapper = session.getMapper(ContactMapper.class);
		List<ItemDetailsResponse> itemList = mapper.select(id);
		response.setList(itemList);
		try{
			if (itemList.size()!=0){
				logger.info("Total Count of items :- "+itemList.size());
				for (ItemDetailsResponse item : itemList) {
					if(item.getQuantity()!=0 && item.getQuantity()-quantity>=0){
						logger.info("updating quantity of item"+id);
						mapper.updateQuantity(id, quantity);
						logger.info("quantity updated");
						itemresponse.setResponse_code(ErrorConstants.SUCCESS);
						itemresponse.setResponse_msg(ErrorConstants.ITEM_UPDATE_RESPONSE);
					} else {
						logger.info("item out of stock");
						itemresponse.setResponse_code(ErrorConstants.INVENTORY_OUT_OF_STOCK_CODE);
						itemresponse.setResponse_msg(ErrorConstants.INVENTORY_OUT_OF_STOCK_MSG);
					}
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
		logger.info("Call Trace - PurchaseRouter.addToCart() - End");
		return Response.status(200).entity(itemresponse).build();
	}

}
