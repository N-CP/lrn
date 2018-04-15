package mongostream.pkgaggregate;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;

import org.bson.Document;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import com.mongodb.MongoClient; 
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable; 

import org.bson.json.*;

import java.util.Properties;
import java.util.concurrent.ExecutionException;


public class clsaggregate {

	public static void main(String[] args) {
		String user1; // the user name
		String db; // the name of the database in which the user is defined
		char[] password1; // the password as a character array
		String startdate;
		String enddate;
		if (args.length > 0){
		  startdate=args[0];
		  enddate=args[1];
		}
			// ...
			password1 = new char[]{'S','t','r','i','n','g','P','a','s','s','w','o','r','d'};
		    user1 = "analytics";
			db="analytics";
			
			MongoCredential credential = MongoCredential.createCredential(user1, db, password1);
		     MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27016),Arrays.asList(credential));
			MongoDatabase database1 = mongoClient.getDatabase("analytics");
			MongoCollection<Document> collsessions = database1.getCollection("sessions");
			MongoCollection<Document> collDashboarcoursetemp = database1.getCollection("Dashboarcoursetemp");
		
		//FindIterable<Document> iterateDoc1 = collsessions.aggregate();// TODO Auto-generated method stub
		//FindIterable<Document> iterateDoc2 = collDashboarcoursetemp.aggregate();

		AggregateIterable<Document> resultset1= collsessions.aggregate(Arrays.asList(
		        new Document("$match",
				new Document("date" ,
							new Document("$gt","startdate") 
				            .append("$lte", "enddate")))),   
				new Document("$project",
							new Document("_id", 1)
							.append("date","$subtract")
							.append("$dateFromString","dateString")
							.append("$dateToString","format")
							.append("%Y-%m-%d","date")
							.append("$created_at","$dateFromString")
							.append("dateString","1970-01-01")));
				 
			AggregateIterable<Document> resultset2= collDashboarcoursetemp.aggregate(Arrays.asList(
					 new Document("$match",
					 new Document("date" ,
					 new Document("$gt","startdate") 
								  .append("$lte", "enddate")))),   
					 new Document("$project",
					 new Document("_id", 1)
								 .append("date","$subtract")
								 .append("$dateFromString","dateString")
								 .append("$dateToString","format")
								 .append("%Y-%m-%d","date")
								 .append("$created_at","$dateFromString")
								 .append("dateString","1970-01-01")));
	}

	
