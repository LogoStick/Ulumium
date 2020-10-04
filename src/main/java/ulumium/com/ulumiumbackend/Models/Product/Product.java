package ulumium.com.ulumiumbackend.Models.Product;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int price;

    private String imageUrl;

    private int ramCapacity;

    private int storageCapacity;

    private String graphicsManufacturer;

    private String cpu;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price &&
                ramCapacity == product.ramCapacity &&
                storageCapacity == product.storageCapacity &&
                Objects.equals(name, product.name.toLowerCase()) &&
                Objects.equals(imageUrl, product.imageUrl) &&
                Objects.equals(graphicsManufacturer, product.graphicsManufacturer) &&
                Objects.equals(cpu, product.cpu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, imageUrl, ramCapacity, storageCapacity, graphicsManufacturer, cpu);
    }
}
