package com.afnan.productservice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IproductRepo extends JpaRepository<products,Long>  {
	
}
