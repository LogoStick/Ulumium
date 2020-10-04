package ulumium.com.ulumiumbackend.Models.Orders;

import javax.persistence.*;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;



}
