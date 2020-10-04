package ulumium.com.ulumiumbackend.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ulumium.com.ulumiumbackend.Models.Product.Product;
import ulumium.com.ulumiumbackend.Repositories.ProductDao;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService implements AbstractProductService {

    ProductDao productDao;

    @Override
    public Product getProductById(int id) {
        if(!this.productDao.existsById(id)) return null;
        return productDao.findById(id).get();
    }

    @Override
    public boolean doesProductExistById(int id) {
        return productDao.existsById(id);
    }

    @Override
    public boolean doesProductExist(Product product) {
        return !productDao
                .findAll().stream()
                .filter(
                        dbProduct -> dbProduct.equals(product)
                )
                .collect(Collectors.toList())
                .isEmpty();
    }

    @Override
    public List<Product> searchForProductByName(String name) {
        return productDao
                .findAll().stream()
                .filter(
                        dbProduct -> dbProduct.getName().contains(name.toLowerCase())
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> listProductsFromAtoN(int from, int to) {
        if(to < from) return null;
        return productDao
                .findAll().stream()
                .skip(from)
                .limit(to - from)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productDao.findAll();
    }

    @Override
    public void addProduct(Product product) {
        if(product == null) return;
        product.setName(product.getName().toLowerCase());
        if(productDao.findAll().stream().anyMatch(dbProduct -> product.equals(dbProduct))) return;
        productDao.save(product);
    }

    @Override
    public void removeProduct(Product product) {
        try {
            Product productToBeRemoved = this.productDao.findAll().stream()
                    .filter(dbProduct -> dbProduct.equals(product))
                    .findFirst()
                    .get();
            if(productToBeRemoved == null) return;
            this.productDao.delete(productToBeRemoved);
        } catch (NoSuchElementException e) {
            return;
        }

    }
}
