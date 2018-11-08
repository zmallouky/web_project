package fr.ub.m2gl;

import java.util.ArrayList;
import java.util.List;

import org.bson.BSON;
import org.bson.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.util.JSON;
import com.sun.research.ws.wadl.Doc;

import static com.mongodb.client.model.Filters.eq;

public class MongoUser {

	public static  String addUserToMongo(User us) {
		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient();
		    MongoDatabase db = mongoClient.getDatabase("userDb");
		    MongoCollection<Document> collection = db.getCollection("userCollection");

		    ObjectMapper mapper = new ObjectMapper();
		    String jsonString = mapper.writeValueAsString(us);
		    Document doc = Document.parse(jsonString);
		    collection.insertOne(doc);
		    return "Utilisateur " +jsonString+" "+ us.getFirstName() + " " + us.getLastName() + " added successfully.";
		} catch (Exception e) {
		    e.printStackTrace();
		} finally{
		    mongoClient.close();
		}
		return "failed";
	}
	
	public static  String deleteUserFromMongo(String f) {
		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient();
		    MongoDatabase db = mongoClient.getDatabase("userDb");
		    MongoCollection<Document> collection = db.getCollection("userCollection");
		    
		    Document user = ((MongoIterable<Document>) collection.find(eq("firstName", f))).first();
		    collection.deleteOne(user);
		    return "deleted";
		} catch (Exception e) {
		    e.printStackTrace();
		} finally{
		    mongoClient.close();
		}
		return "failed";
	}
	
	public static  String updateUserToMongo(User us, String newFirst) {
		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient();
		    MongoDatabase db = mongoClient.getDatabase("userDb");
		    MongoCollection<Document> collection = db.getCollection("userCollection");

		    ObjectMapper mapper = new ObjectMapper();
		    String jsonString = mapper.writeValueAsString(us);
		    Document doc2 = Document.parse(jsonString);
		    Document doc1 = new Document("firstName",newFirst); ///name to modifiy
		    collection.updateOne(doc1, new Document("$set", doc2));
		    return "Utilisateur "+doc2+" " + us.getFirstName() + " " + us.getLastName() + " updated successfully.";
		} catch (Exception e) {
		    e.printStackTrace();
		} finally{
		    mongoClient.close();
		}
		return "failed";
	}
	
	public static  ArrayList<User> showUsers() {
		ArrayList<User> users = new ArrayList<User>();
		MongoClient mongoClient = null;

		try {
			mongoClient = new MongoClient();
		    MongoDatabase db = mongoClient.getDatabase("userDb");
		    MongoCollection<Document> collection = db.getCollection("userCollection");
		    List<Document> Docs = collection.find().into(new ArrayList<Document>());
		    
		    for (Document document : Docs) {
				users.add(new User(document.getString("firstName"),document.getString("lastName")));
			}
		    
		    return users;
		} catch (Exception e) {
		    e.printStackTrace();
		} finally{
		    mongoClient.close();
		}
		return  null;
	}
	
	public static  User showUser(String f) {
		MongoClient mongoClient = null;
		
		try {
			mongoClient = new MongoClient();
			MongoDatabase db = mongoClient.getDatabase("userDb");
			MongoCollection<Document> collection = db.getCollection("userCollection");
		    
		    Document user = ((MongoIterable<Document>) collection.find(eq("firstName", f))).first();
		    
		    return new User(user.getString("firstName"),user.getString("lastName"));
		} catch (Exception e) {
		    e.printStackTrace();
		} finally{
		    mongoClient.close();
		}
		return null;
	}
}
