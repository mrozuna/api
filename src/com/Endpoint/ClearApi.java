package com.Endpoint;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

@Api(name = "clear")
public class ClearApi {
	@ApiMethod(name = "clearAll", httpMethod = "GET")
	public void Clear(){
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		
		Query qUserApi = new Query("UserApi");
		PreparedQuery pqUserApi = datastore.prepare(qUserApi);
		for (Entity e:pqUserApi.asIterable()){
			datastore.delete(e.getKey());
		}
		
		Query qMedalsType = new Query("MedalsType");
		PreparedQuery pqMedalsType = datastore.prepare(qMedalsType);
		for (Entity e:pqMedalsType.asIterable()){
			datastore.delete(e.getKey());
		}
		
		Query qMedal = new Query("Medal");
		PreparedQuery pqMedal = datastore.prepare(qMedal);
		for (Entity e:pqMedal.asIterable()){
			datastore.delete(e.getKey());
		}
		
		
		Query qStatus = new Query("Status");
		PreparedQuery pqStatus = datastore.prepare(qStatus);
		for (Entity e:pqStatus.asIterable()){
			datastore.delete(e.getKey());
		}
	}
	
	@ApiMethod(name = "clearUserApi", httpMethod = "GET")
	public void clearUserApi(){
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query qUserApi= new Query("UserApi");
		PreparedQuery pqUserApi= datastore.prepare(qUserApi);
		for (Entity e:pqUserApi.asIterable()){
			datastore.delete(e.getKey());
		}
	}
	
	
	
	@ApiMethod(name = "clearMedalsType", httpMethod = "GET")
	public void clearMedalsType(){
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query qMedalsType = new Query("MedalsType");
		PreparedQuery pqMedalsType= datastore.prepare(qMedalsType);
		for (Entity e:pqMedalsType.asIterable()){
			datastore.delete(e.getKey());
		}
	}
	@ApiMethod(name = "clearMedal", httpMethod = "GET")
	public void clearMedal(){
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query qMedal = new Query("Medal");
		PreparedQuery pqMedal= datastore.prepare(qMedal);
		for (Entity e:pqMedal.asIterable()){
			datastore.delete(e.getKey());
		}
	}
	@ApiMethod(name = "clearStatus", httpMethod = "GET")
	public void clearStatus(){
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query qStatus = new Query("UserApi");
		PreparedQuery pqStatus = datastore.prepare(qStatus);
		for (Entity e:pqStatus.asIterable()){
			datastore.delete(e.getKey());
		}
	}
	
}
