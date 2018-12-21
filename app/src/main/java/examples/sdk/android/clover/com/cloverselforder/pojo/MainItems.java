package examples.sdk.android.clover.com.cloverselforder.pojo;


public class MainItems
{
    private String price;

    private String description;

    private String item;

    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getItem ()
    {
        return item;
    }

    public void setItem (String item)
    {
        this.item = item;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [price = "+price+", description = "+description+", item = "+item+"]";
    }
}
