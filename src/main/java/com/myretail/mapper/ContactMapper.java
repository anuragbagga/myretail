package com.myretail.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myretail.model.ItemDetailsResponse;
import com.myretail.model.ItemResponse;

public interface ContactMapper {

	/* Return all list of items */

	List<ItemDetailsResponse> selectAll();
	
	/* Return list of items based on item id */

	List<ItemDetailsResponse> select(Integer id);
	
	/* Return list of items based on calalog string */
	
	List<ItemDetailsResponse> selectOnCatalog(String catalogName);
	
	public void updateQuantity(@Param("id") Integer id,@Param("quantity")Integer quantity);

}
