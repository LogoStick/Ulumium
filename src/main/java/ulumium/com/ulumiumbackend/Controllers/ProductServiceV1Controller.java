package ulumium.com.ulumiumbackend.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ulumium.com.ulumiumbackend.Models.Product.Product;
import ulumium.com.ulumiumbackend.Services.AbstractProductService;

import java.util.List;

@RestController("ProductServiceControllerV1")
@RequestMapping("product-service/v1/")
@AllArgsConstructor
public class ProductServiceV1Controller{

    private AbstractProductService productService;

    @GetMapping("get-product-by-id/{id}")
    @PreAuthorize("permitAll()")
    public Product getProductById(@PathVariable int id) {
        return this.productService.getProductById(id);
    }

    @GetMapping("does-product-exist-by-id/{id}")
    @PreAuthorize("permitAll()")
    public boolean doesProductExistById(@PathVariable int id) {
        return this.productService.doesProductExistById(id);
    }

    @PostMapping("does-product-exist")
    @PreAuthorize("permitAll()")
    public boolean doesProductExist(@RequestBody Product product) {
        return this.productService.doesProductExist(product);
    }

    @GetMapping("search-for-products-by-name/{name}")
    @PreAuthorize("permitAll()")
    public List<Product> searchForProductByName(@PathVariable String name) {
        return this.productService.searchForProductByName(name);
    }

    @GetMapping("search-for-products-by-name/")
    @PreAuthorize("permitAll()")
    public List<Product> searchForProductByName() {
        return this.productService.getAllProducts();
    }

    @GetMapping("list-products-from-a-to-n/{from}/{to}")
    @PreAuthorize("permitAll()")
    public List<Product> listProductsFromAtoN(@PathVariable int from, @PathVariable int to) {
        return this.productService.listProductsFromAtoN(from, to);
    }

    @PostMapping("add-product")
    @PreAuthorize("hasRole('ADMIN')")
    public void addProduct(@RequestBody Product product) {
        this.productService.addProduct(product);
    }

    @PostMapping("remove-product")
    @PreAuthorize("hasRole('ADMIN')")
    public void removeProduct(@RequestBody Product product) {
        this.productService.removeProduct(product);
    }
}
