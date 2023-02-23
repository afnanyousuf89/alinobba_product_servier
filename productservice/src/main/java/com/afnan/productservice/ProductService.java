package com.afnan.productservice;


import java.util.List;
public interface ProductService {
    products saveproducts(products products);
    products getproducts(Long id);
    products updateproducts(products products);
    void deleteproducts(Long id);
    List<products> getAllproductss();
}



