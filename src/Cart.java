import java.util.UUID;

public class Cart {
    private String id;

    public Cart(){
        this.id = UUID.randomUUID().toString();
    }
    public String getID() {
        return this.id;
    }
}
