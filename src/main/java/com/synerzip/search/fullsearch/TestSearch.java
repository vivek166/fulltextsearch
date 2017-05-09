package com.synerzip.search.fullsearch;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

public class TestSearch {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		EntityManager entityManager = Persistence.createEntityManagerFactory(
				"MumzHibernateSearch").createEntityManager();
		FullTextEntityManager fullTextEntityManager = Search
				.getFullTextEntityManager(entityManager);
		try {
			fullTextEntityManager.createIndexer().startAndWait();
			addMoreRecords(entityManager);
			QueryBuilder qb = fullTextEntityManager.getSearchFactory()
					.buildQueryBuilder().forEntity(Book.class).get();
			org.apache.lucene.search.Query query = qb.keyword()
					.onFields("name", "author").matching("Pro Android 4")
					.createQuery();
			Query jpaQuery = fullTextEntityManager.createFullTextQuery(query,
					Book.class);
			List<Book> bookResult = jpaQuery.getResultList();
			if (bookResult != null) {
				for (Book book : bookResult) {
					System.out.println("Book found = " + book);
				}
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (fullTextEntityManager != null) {
				fullTextEntityManager.close();
			}
			fullTextEntityManager = null;
		}
	}

	private static void addMoreRecords(EntityManager entityManager) {
		Book bookObj = new Book();
		bookObj.setName("Pro Spring 3");
		bookObj.setAuthor("Clarence Ho and Rob Harrop");
		entityManager.persist(bookObj);
		bookObj = new Book();
		bookObj.setName("vivek Pro Spring 3");
		bookObj.setAuthor("guruG");
		entityManager.persist(bookObj);
		bookObj = new Book();
		bookObj.setName("vivek  Spring 3");
		bookObj.setAuthor("guruG");
		entityManager.persist(bookObj);
		bookObj = new Book();
		bookObj.setName("Pro JPA 2 Mastering the Java Persistence API");
		bookObj.setAuthor("Mike Keith and Merrick Schincariol");
		entityManager.getTransaction().begin();
		entityManager.persist(bookObj);
		entityManager.getTransaction().commit();
	}
}