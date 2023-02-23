package com.afnan.productservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockReset;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


@SpringBootTest
class ProductserviceApplicationTests {

	@Mock
	private IproductRepo prodRepo;

	@InjectMocks
	private productsServiceWork prodService;

	@Test
	void contextLoads() {
	}

	@Test
	void testforPiD() {
		products P = new products();
		Long Id = 1L;
		P.setPid(Id);
		assertEquals(Id, P.getPid());
	}

	@Test
	void testforPname() {
		products P = new products();
		String pname = "Product";
		P.setPname(pname);
		assertEquals(pname, P.getPname());
	}

	@Test
	void testforPprice() {
		products P = new products();
		Long pprice = 225L;
		P.setPprice(pprice);
		assertEquals(pprice, P.getPprice());
	}

	@Test
	void testforPshortDes() {
		products P = new products();
		String psdes = "Product";
		P.setPshort(psdes);
		assertEquals(psdes, P.getPshort());
	}

	@Test
	void testforPlongDes() {
		products P = new products();
		String pldes = "Product";
		P.setPlong(pldes);
		assertEquals(pldes, P.getPlong());
	}

	@Test
	void testforPimage() {
		products P = new products();
		String pimage = "Product";
		P.setPimg(pimage);
		assertEquals(pimage, P.getPimg());
	}

	@Test
	void testforAllArgsConstructorproducts() {
		Long Id = 1L;
		String pname = "Product 1";
		String pshort = "Short Desc";
		String plong = "Product description is long";
		String pimg = "Product1.jpg";
		Long pprice = 1000L;

		products P = new products(Id, pname, pimg, pshort, plong, pprice);
		assertEquals(Id, P.getPid());
		assertEquals(pname, P.getPname());
		assertEquals(pimg, P.getPimg());
		assertEquals(pshort, P.getPshort());
		assertEquals(plong, P.getPlong());
		assertEquals(pprice, P.getPprice());
	}

	@Test
	void testforBuildproducts() {
		Long Id = 1L;
		String pname = "Product 1";
		String pshort = "Short Desc";
		String plong = "Product description is long";
		String pimg = "Product1.jpg";
		Long pprice = 1000L;
		products P = products.builder()
				.pid(Id)
				.pname(pname)
				.pprice(pprice)
				.pimg(pimg)
				.pshort(pshort)
				.plong(plong)
				.build();
		assertEquals(Id, P.getPid());
		assertEquals(pname, P.getPname());
		assertEquals(pimg, P.getPimg());
		assertEquals(pshort, P.getPshort());
		assertEquals(plong, P.getPlong());
		assertEquals(pprice, P.getPprice());
	}

	@Test
	void testforSaveNewproducts() {
		Long Id = 1L;
		String pname = "Product 1";
		String pshort = "Short Desc";
		String plong = "Product description is long";
		String pimg = "Product1.jpg";
		Long pprice = 1000L;
		products P = products.builder()
				.pid(Id)
				.pname(pname)
				.pprice(pprice)
				.pimg(pimg)
				.pshort(pshort)
				.plong(plong)
				.build();
		given(prodRepo.save(P)).willReturn(P);
		prodService.saveproducts(P);
		verify(prodRepo, times(1)).save(P);
	}

	@Test
	void testforViewAllproducts() {
		prodRepo.findAll();
		verify(prodRepo, times(1)).findAll();
	}

	@Test
	void testforGetSingleproducts() {
		Long Id = 1L;
		String pname = "Product 1";
		String pshort = "Short Desc";
		String plong = "Product description is long";
		String pimg = "Product1.jpg";
		Long pprice = 1000L;
		products P = products.builder()
				.pid(Id)
				.pname(pname)
				.pprice(pprice)
				.pimg(pimg)
				.pshort(pshort)
				.plong(plong)
				.build();
		given(prodRepo.findById(Id)).willReturn(Optional.of(P));
		Optional<products> pro = prodService.fetchSingleproducts(P);
		assertNotNull(pro);
	}

	@Test
	void testforDeleteproducts() {
		Long Id = 1L;
		prodRepo.deleteById(Id);
		verify(prodRepo, times(1)).deleteById(Id);
	}



	@Test
    public void testforProductAllEndpoint() {
        // Arrange
		products p1 = new products(1,"Product","p1.jpg","this is short db","this is long db",2563252);
		products p2 = new products(2,"Product 2","p2.jpg","this is short db","this is long db",2563252);
        List<products> productList = Arrays.asList(p1,p2,p1,p2,p1);
        Mockito.when(prodRepo.findAll()).thenReturn(productList);

        // Act
		RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Collection<products>> response = restTemplate.exchange("http://localhost:8080/product", HttpMethod.GET, null, new ParameterizedTypeReference<Collection<products>>() {});

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(5, response.getBody().size());
    }

    @Test
    public void testforProductSaveEndpoint() {
        // Arrange
		products p1 = new products(1,"Product","p1.jpg","this is short db","this is long db",2563252);        
        Mockito.when(prodRepo.save(p1)).thenReturn(p1);

        // Act
		RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/product", p1, String.class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product Saved", response.getBody());
    }

}
