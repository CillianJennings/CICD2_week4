package ie.atu.week4.jpa;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRep productRep;

    public ProductService(ProductRep productRep) {
        this.productRep = productRep;
    }

    public List<Product> getAllProducts() {
        return productRep.findAll();
    }

    public void addProduct(Product product){
        productRep.save(product);
    }

    public boolean editProduct(long id, Product updatedProduct) {
        Optional<Product> existingProduct = productRep.findById(id);

        if(existingProduct.isPresent()){
            Product oldProduct = existingProduct.get();

            oldProduct.setProductName(updatedProduct.getProductName());
            oldProduct.setProductPrice(updatedProduct.getProductPrice());

            productRep.save(oldProduct);
            return true;
        }
        return false;
    }

    public void deleteProduct(long id) {
        productRep.deleteById(id);
    }
}
