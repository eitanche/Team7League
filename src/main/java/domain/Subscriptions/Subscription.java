package domain.Subscriptions;

import java.util.Objects;

/**
 * This abstract class represents a subscription of the system, each subscription have this class attributes and
 * capabilities.
 */

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

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "name='" + name + '\'' +
                '}';
    }

    /**
     * @param o
     * @return true if that's the same object or his class members equals else false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return id.equals(that.id) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
