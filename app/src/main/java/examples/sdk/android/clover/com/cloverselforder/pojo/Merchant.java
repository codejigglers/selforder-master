package examples.sdk.android.clover.com.cloverselforder.pojo;

public class Merchant {
    private String id;

    private Items[] items;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Items[] getItems() {
        return items;
    }

    public void setItems(Items[] items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ClassPojo [id = " + id + ", items = " + items + "]";
    }
}

