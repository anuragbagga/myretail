package com.myretail.mapper;

import java.util.List;

import com.myretail.model.ItemDetailsResponse;

public interface ContactMapper {

	/* Return all list of items */

	List<ItemDetailsResponse> selectAll();
	
	/* Return list of items based on item id */

	List<ItemDetailsResponse> select(Integer id);
	
	/* Return list of items based on calalog string */
	
	List<ItemDetailsResponse> selectOnCatalog(String catalogName);

}
