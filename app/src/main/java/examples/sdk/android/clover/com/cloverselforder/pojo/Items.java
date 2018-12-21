package examples.sdk.android.clover.com.cloverselforder.pojo;

public class Items
{
    private MainItems[] MainItems;

    public MainItems[] getMainItems ()
    {
        return MainItems;
    }

    public void setMainItems (MainItems[] MainItems)
    {
        this.MainItems = MainItems;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [MainItems = "+MainItems+"]";
    }
}


