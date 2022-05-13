package domain.Subscriptions;

public abstract class Subscription {
    protected String id;
    protected String name;

    public Subscription(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }
}
