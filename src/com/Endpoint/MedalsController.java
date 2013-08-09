package com.Endpoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.Data.Medals;
import com.Data.MedalsType;
import com.Data.MedalsWin;
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
import com.google.appengine.api.users.User;

/**
 * @author mrosas
 *
 */

@Api(name = "medals")
public class MedalsController {

	public MedalsController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param medaltype
	 * @return
	 * @throws OAuthRequestException
	 */
	@ApiMethod(name = "insertMedalsType", httpMethod = "POST")
	public MedalsType addMedaltype(MedalsType medaltype)
			throws OAuthRequestException {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		if (!medaltype.getName().equals("")
				&& !medaltype.getName().equals(null)) {
			long id = 1;

			Query q = new Query("MedalsType");
			PreparedQuery pq = datastore.prepare(q);
			id = pq.asList(FetchOptions.Builder.withDefaults()).size();
			id += 1;
			Key key = KeyFactory.createKey("MedalsType", id);
			Entity medalTypeEntity = new Entity(key);
			medalTypeEntity.setProperty("id", id);
			medalTypeEntity.setProperty("name", medaltype.getName());
			medalTypeEntity.setProperty("description",
					medaltype.getDescription());
			medaltype.setId(id);
			datastore.put(medalTypeEntity);
			return medaltype;
		} else {
			throw new OAuthRequestException("Invalid Medal type.");
		}

	}

	

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	/**
	 * @param cursorString
	 * @param limit
	 * @return
	 */
	@ApiMethod(name = "listMedalsType")
	public CollectionResponse<MedalsType> listMedalsType(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		List<MedalsType> list = new ArrayList<MedalsType>();
		if (getMedalsType().size() > 0) {
			list = getMedalsType();
		}

		return CollectionResponse.<MedalsType> builder().setItems(list)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * @param medalsWon
	 * @param id
	 * @return
	 */
	@ApiMethod(name = "updateMedalsUser")
	public CollectionResponse<MedalsWin>  updateMedalsUser(CollectionResponse<MedalsWin> medalsWon,
			 @Named("id")long id) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();



		List<MedalsWin> list = new ArrayList<MedalsWin>();
	
		for (MedalsWin medal : medalsWon.getItems()) {
			id = getMedalsTypeId(medal.getNameMedal());
			if (id > 0) {

				Query q = new Query("Medal").setFilter(
						new FilterPredicate("idUser", FilterOperator.EQUAL,
								id)).setFilter(
						new FilterPredicate("idMedalType",
								FilterOperator.EQUAL, id));
				PreparedQuery pq = datastore.prepare(q);
				Entity e = pq.asSingleEntity();

				medal.setAmout((int) e.getProperty("total") + medal.getAmout());
				e.setProperty("total", medal.getAmout());
				list.add(medal);
				datastore.put(e);

			} else {

			}

		}
	
	return CollectionResponse.<MedalsWin> builder().setItems(list)
			.setNextPageToken(null).build();
	}

	@ApiMethod(name = "listMedalsUser")
	public CollectionResponse<Medals> getMedalsUser( @Named("id")long id) {
		List<Medals> list = new ArrayList<Medals>();
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query query = new Query("Medal").setFilter(new FilterPredicate("idUser", FilterOperator.EQUAL, id));
		PreparedQuery pq= datastore.prepare(query);
		for(Entity entMedal: pq.asIterable()){
			Query queryMedalsType= new Query("MedalsType").setFilter( new FilterPredicate("id", FilterOperator.EQUAL, entMedal.getProperty("idMedalType")));
			PreparedQuery pq1= datastore.prepare(queryMedalsType);
			Entity entityResult= pq1.asSingleEntity();
			Medals medal= new Medals();
			medal.setMedalsType(entityResult.getProperty("name").toString());
			medal.setIdMedals((long)entMedal.getProperty("id"));
			medal.setTotal((long)entMedal.getProperty("total"));
			medal.setUserId(id);
			list.add(medal);
		}

		return CollectionResponse.<Medals> builder().setItems(list)
				.setNextPageToken(null).build();
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

	
	private long getMedalsTypeId(String medalName) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query q = new Query("MedalsType").setFilter(new FilterPredicate("name",
				FilterOperator.EQUAL, medalName));
		PreparedQuery pq = datastore.prepare(q);
		Entity entity = pq.asSingleEntity();
		return (long) entity.getProperty("id");
	}

}
