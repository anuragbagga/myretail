package com.myretail.utils;


import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.myretail.mapper.ContactMapper;
import com.myretail.model.ItemDetailsResponse;

public class ExampleApplication {
	/**
	 * scope: application
	 */
	private static SqlSessionFactory sqlMapper = null;

	public static void main(String[] args) {
		String resource = "org/mybatis/config/configuration.xml";
		Reader reader = null;

		try {
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}

		SqlSession session = sqlMapper.openSession();
		try {
			ContactMapper mapper = session.getMapper(ContactMapper.class);
			List<ItemDetailsResponse> contacts = mapper.selectOnCatalog("Toys");
			for (ItemDetailsResponse contact : contacts) {
				System.out.println(contact.getId() + ":"
						+ contact.getName() + ":" + contact.getPrice());
			}
		} finally {
			session.close();
		}
	}
}
