package ulumium.com.ulumiumbackend.Services;

import org.springframework.stereotype.Service;
import ulumium.com.ulumiumbackend.Models.Product.Product;

import java.util.List;

@Service
public interface AbstractProductService {

    Product getProductById(int id);
    boolean doesProductExistById(int id);
    boolean doesProductExist(Product product);
    List<Product> searchForProductByName(String name);
    List<Product> listProductsFromAtoN(int from, int to);
    List<Product> getAllProducts();
    void addProduct(Product product);
    void removeProduct(Product product);

}
