package com.Endpoint;

import java.util.ArrayList;
import java.util.List;


import javax.inject.Named;

import com.Data.MedalsType;
import com.Data.UserApi;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.oauth.OAuthRequestException;

@Api(name = "user")
public class UserController {

	public UserController() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * It uses HTTP POST method.
	 *
	 * @param userapi the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertUser", httpMethod = "POST")
	public UserApi addUser(UserApi userApi) throws OAuthRequestException {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

			long id = 1;

			Query q = new Query("UserApi");
			PreparedQuery pq = datastore.prepare(q);
			id = pq.asList(FetchOptions.Builder.withDefaults()).size();
			id += 1;
			Key key = KeyFactory.createKey("UserApi", id);
			Entity userEntity = new Entity(key);
			userEntity.setProperty("id", id);
			userEntity.setProperty("name", "");
			// userEntity.setProperty("idGoogle",
			// userApi.getUser().getUserId());
			userEntity.setProperty("email", userApi.getEmail());
			userEntity.setProperty("point", 0);
			// userEntity.setProperty("authDomain",
			// userApi.getUser().getAuthDomain());

			userApi.setId(id);
			addMedalsUser(userApi);

			datastore.put(userEntity);
			return userApi;
	}
	
	
	

	
	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getUserData", httpMethod = "GET")
	public UserApi getUserData(@Named("id") long id) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query q = new Query("UserApi").setFilter(new FilterPredicate("id",
				FilterOperator.EQUAL, id));
		PreparedQuery pq = datastore.prepare(q);
		UserApi u = new UserApi();
		Entity e = pq.asSingleEntity();
		u.setId(id);
		u.setNameUser(e.getProperty("name").toString());
		// u.setUser(null);
		u.setEmail(e.getProperty("email").toString());
		u.setTotalPoints(Long.parseLong(e.getProperty("point").toString()));
		return u;
	}

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@ApiMethod(name = "getAllUser", httpMethod = "GET")
	public CollectionResponse<UserApi> getUser() {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query q = new Query("UserApi");
		PreparedQuery pq = datastore.prepare(q);
		List<UserApi> list = new ArrayList<UserApi>();

		for (Entity e : pq.asIterable()) {
			UserApi u = new UserApi();
			u.setId((long) e.getProperty("id"));
			u.setNameUser(e.getProperty("name").toString());
			// User user= new
			// User(e.getProperty("email").toString(),e.getProperty("authDomain").toString(),
			// e.getProperty("idGoogle").toString());
			// u.setUser(user);
			u.setEmail(e.getProperty("email").toString());
			u.setTotalPoints(Long.parseLong(e.getProperty("point").toString()));
			list.add(u);
		}
		return CollectionResponse.<UserApi> builder().setItems(list)
				.setNextPageToken(null).build();
	}

	private void addMedalsUser(UserApi user) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		if (user != null && getMedalsType().size() > 0) {
			long id = 1;
			List<MedalsType> listMedalsType = getMedalsType();

			Query query = new Query("MedalsType");
			PreparedQuery pq = datastore.prepare(query);

			List<MedalsType> list = new ArrayList<MedalsType>();
			for (Entity e : pq.asIterable()) {
				list.add(new MedalsType((long) e.getProperty("id"), e.getProperty(
						"name").toString(), e.getProperty("description").toString()));
			}
			
			
			for (MedalsType medalsType : listMedalsType) {
				Query q = new Query("Medal");
				PreparedQuery pq1 = datastore.prepare(q);
				id = pq1.asList(FetchOptions.Builder.withDefaults()).size();
				id += 1;
				Key userKey = KeyFactory.createKey("Medal", id);
				Entity userEntity = new Entity(userKey);
				userEntity.setProperty("id", userKey.getId());
				userEntity.setProperty("idUser", user.getId());
				userEntity.setProperty("idMedalType", medalsType.getId());
				userEntity.setProperty("total", 0);
				datastore.put(userEntity);

			}

		}
	}

	private List<MedalsType> getMedalsType() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query query = new Query("MedalsType");
		PreparedQuery pq = datastore.prepare(query);

		List<MedalsType> list = new ArrayList<MedalsType>();
		for (Entity e : pq.asIterable()) {
			list.add(new MedalsType((long) e.getProperty("id"), e.getProperty(
					"name").toString(), e.getProperty("description").toString()));
		}
		return list;
	}
}
