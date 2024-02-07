package Business;

import Dao.ProductDao;
import Model.Product;

import java.util.List;

public class ProductBusiness {
    private final ProductDao productDao;

    public ProductBusiness() {
        this.productDao = new ProductDao();
    }

    public void createProduct(Product product) {
        productDao.creatProduct(product);
    }

    public Product getProductById(int productId) {
        return productDao.getProductById(productId);
    }

    public void updateProduct(Product product) {
        productDao.updateProduct(product);
    }

    public void deleteProduct(int productId) {
        productDao.deleteProduct(productId);
    }

    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    public double calculateAveragePrice() {
        List<Product> products = productDao.getAllProducts();
        if (products.isEmpty()) {
            return 0.0;
        }

        double totalPrice = 0.0;
        for (Product product : products) {
            totalPrice += product.getPrice();
        }

        return totalPrice / products.size();
    }

    public void discount(Product product){
     int productQuantity = product.getQuantity();
     if (productQuantity > 90){
         System.out.println("include 3% discount");
     }
    }
}


