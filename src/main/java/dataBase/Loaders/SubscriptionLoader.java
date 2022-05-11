package dataBase.Loaders;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import domain.Subscriptions.Subscription;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.eq;

public class SubscriptionLoader implements ILoader{
    private MongoCollection collection;

    public SubscriptionLoader() {
        MongoClient client = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = client.getDatabase("Database");
        collection = database.getCollection("Users");
    }

    @Override
    public Subscription authenticate(String userName, String hashedPassword) {
        Document desiredUserDocument = new Document();
        desiredUserDocument.put("userName", userName);

        Bson projectionFields = Projections.fields(
                Projections.include("userName", "password", "name", "roles"),
                Projections.excludeId());
        Document doc = (Document) collection.find(eq("userName", "userName"))
                .projection(projectionFields)
                .first();
        return null;
    }

    @Override
    public boolean isUserExists(String userName) {
        return false;
    }
}
