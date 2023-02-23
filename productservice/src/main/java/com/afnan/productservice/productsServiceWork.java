package com.afnan.productservice;


import java.util.List;
import java.util.Optional;

import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Service;

@Service
public class productsServiceWork implements ProductService {
	private IproductRepo productsRepository;

	public productsServiceWork(IproductRepo productsRepository){
		this.productsRepository = productsRepository;
	}

	public products saveproducts(products products) {
		return productsRepository.save(products);
	}

	public products getproducts(Long id) {
		return productsRepository.getReferenceById(id);
	}

	
	public products updateproducts(products products) {
		Optional<products> savedproducts = productsRepository.findById(products.getPid()); 
		if(savedproducts.isEmpty()){
			throw new InvalidConfigurationPropertyValueException("Id", products.getPid(), "A products id "+products.getPid()+" does not already exist in the database.");
		}
		return productsRepository.save(products);
	}


	public void deleteproducts(Long id){
		productsRepository.deleteById(id);
	}

	public List<products> getAllproductss(){
		return productsRepository.findAll();
	}

	public Optional<products> fetchSingleproducts(products p){
		return productsRepository.findById(p.getPid());
	}

	
}