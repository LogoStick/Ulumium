package ulumium.com.ulumiumbackend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ulumium.com.ulumiumbackend.Models.Product.Product;

public interface ProductDao extends JpaRepository<Product, Integer> {
}
