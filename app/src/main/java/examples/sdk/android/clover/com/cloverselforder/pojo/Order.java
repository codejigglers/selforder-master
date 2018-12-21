package examples.sdk.android.clover.com.cloverselforder.pojo;

public class Order {
    private Merchant merchant;

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    @Override
    public String toString() {
        return "ClassPojo [merchant = " + merchant + "]";
    }
}

