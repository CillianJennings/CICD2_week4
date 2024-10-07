package ie.atu.week4.jpa;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRep productRep;

    public ProductService(ProductRep productRep) {
        this.productRep = productRep;
    }

    public List<Product> add(Product product){
        productRep.save(product);
        return  productRep.findAll();
    }
}
