package com.afnan.productservice;

import java.security.PublicKey;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/product")
public class productController {
    @Autowired
    private IproductRepo repo;

    @GetMapping("")
    public Collection<products> viewAll(){
        return repo.findAll();
    }
    
@PostMapping("")
public String viewAll(@RequestBody products productss ){
    repo.save(productss);
    return "Product Saved";
}

}
