package ru.javalab.mongo.driver;

import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import org.bson.Document;

import java.util.Arrays;

import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

public class Main {
    public static void main(String[] args) {
        MongoClient client = MongoClients.create();
        MongoDatabase database = client.getDatabase("theatre");
        MongoCollection<Document> collection1 = database.getCollection("troupes");
        MongoCollection<Document> collection2 = database.getCollection("histrionics");

        BasicDBObject query = new BasicDBObject();
        query.put("title", "Bolshoy");

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("title", "Bolshoy theatre");

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newDocument);

        database.getCollection("troupes").updateOne(query, updateObject);

        BasicDBObject query2 =
                new BasicDBObject().append("$inc",
                        new BasicDBObject().append("plannedCitiesNum", 1));

        database.getCollection("troupes").updateOne(new BasicDBObject().append("title", "Bolshoy theatre"), query2);

        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.append("city", "Kazan");

        BasicDBObject updateQuery = new BasicDBObject();
        updateQuery.append("$set",
                new BasicDBObject().append("keywords", Arrays.asList("fun", "metal", "mosh")));

        database.getCollection("histrionics").updateMany(searchQuery, updateQuery);

        Document searchTheatre = new Document()
                .append("title", "Bolshoy theatre");

        FindIterable<Document> resultDocuments = collection1.find(searchTheatre)
                .projection(fields(include("title", "peopleInGroupe", "plannedCitiesNum", "genres"), excludeId()));

        for (Document document : resultDocuments) {
            System.out.println(document.toJson());
        }

        Document searchConcert = new Document()
                .append("title", "Kachalov Theatre");

        FindIterable<Document> resultDocuments2 = collection2.find(searchConcert)
                .projection(fields(include("title", "city", "genres", "keywords"), excludeId()));

        for (Document document : resultDocuments2) {
            System.out.println(document.toJson());
        }

        collection1.drop();
        collection2.drop();

    }
}
