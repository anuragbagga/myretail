package com.myretail.utils;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBSessionFactory {

	private static SqlSessionFactory sqlMapper = null;

	/* Return sql session */
	
	public static SqlSession getDBConnection(){
		String resource = "org/mybatis/config/configuration.xml";
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSession session = sqlMapper.openSession();
		return session;
	}
}
