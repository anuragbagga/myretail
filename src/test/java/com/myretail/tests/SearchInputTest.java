package com.myretail.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.myretail.mapper.ContactMapper;
import com.myretail.model.ItemDetailsResponse;
import com.myretail.utils.DBSessionFactory;


public class SearchInputTest {

	/*
	 * Validating for following Input
	 *  ID = 5555
	 *  Name = Stroller
	 *  Category = Baby
	 *  Price = $199.99
	 */

	@Test
	public void testInput1()  {
		SqlSession session = DBSessionFactory.getDBConnection();
		ContactMapper mapper = session.getMapper(ContactMapper.class);
		List<ItemDetailsResponse> itemList = mapper.select(3232);
		for(ItemDetailsResponse item : itemList){
			assertTrue((item.getId()==5555));
			assertEquals(item.getCategory_id(),"Baby");
		//	assertEquals(item.getPrice(),"199.99");
			assertEquals(item.getName(),"Stroller");
		}
		session.close();

	}

	/*
	 * Validating for following Input
	 *  ID = 7563
	 *  Name = Sega Genesis
	 *  Category = Toys
	 *  Price = $149.99
	 * 
	 */

	@Test
	public void testInput2()  {
		SqlSession session = DBSessionFactory.getDBConnection();
		ContactMapper mapper = session.getMapper(ContactMapper.class);
		List<ItemDetailsResponse> itemList = mapper.select(7563);
		for(ItemDetailsResponse item : itemList){
			assertTrue((item.getId()==7563));
			assertEquals(item.getCategory_id(),"Toys");
		//	assertEquals(item.getPrice(),"149.99");
			assertEquals(item.getName(),"Sega Genesis");
			assertEquals(item.getSku(),"XYZ904");

		}
		session.close();
	}

	/*
	 * Validating for count of items in database > 0
	 *  
	 */

	@Test
	public void testInput3()  {
		SqlSession session = DBSessionFactory.getDBConnection();
		ContactMapper mapper = session.getMapper(ContactMapper.class);
		List<ItemDetailsResponse> itemList = mapper.selectAll();
		assertFalse(itemList.size()==0);
		for(ItemDetailsResponse item : itemList){
			if(item.getSku().equals("IOL123")){
				assertTrue((item.getId()==5543));
				assertEquals(item.getCategory_id(),"Toys");
		//		assertEquals(item.getPrice(),"13.37");
				assertEquals(item.getName(),"Optimus Prime");
			}
		}
	}

	/*
	 * Validating for search by Catalog
	 * Searches Catalog by Baby and verify output by one of the items
	 *
	 */

	@Test
	public void testInput4()  {
		SqlSession session = DBSessionFactory.getDBConnection();
		ContactMapper mapper = session.getMapper(ContactMapper.class);
		List<ItemDetailsResponse> itemList = mapper.selectOnCatalog("Baby");
		assertTrue(itemList.size()>0);
		for(ItemDetailsResponse item : itemList){
			assertTrue((item.getId()==5555));
			assertEquals(item.getCategory_id(),"Baby");
			//assertEquals(item.getPrice(),"199.99");
			assertEquals(item.getName(),"Stroller");	
			assertEquals(item.getSku(),"AEX143");

		}
		session.close();
	}

}


