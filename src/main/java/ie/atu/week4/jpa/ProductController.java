package ie.atu.week4.jpa;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getProducts")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(@Valid @RequestBody Product newProduct) {
        productService.addProduct(newProduct);
        return new ResponseEntity<>("Product successfully created\n", HttpStatus.CREATED);
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody Product updatedProduct) {
        boolean status = productService.editProduct(id, updatedProduct);

        if (status) {
            return new ResponseEntity<>("Product successfully Edited\n", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Product not found\n", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product Deleted\n", HttpStatus.CREATED);

    }
}
