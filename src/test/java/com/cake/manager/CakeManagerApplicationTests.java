package com.cake.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cake.manager.entity.Cake;
import com.cake.manager.repository.CakeRepository;
import com.cake.manager.service.CakeService;

@SpringBootTest
class CakeManagerApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private CakeService service;

	@MockBean
	private CakeRepository repository;

	@Test
	public void getCakesTest() {
		when(repository.findAll()).thenReturn(Stream.of(new Cake("Pineapple Cake", "Made of Pineapple", "imageURL"),
				new Cake("Black Forest Cake", "Special Cake", "image")).collect(Collectors.toList()));
		assertEquals(2, service.findAllCakes().size());
	}

	@Test
	public void saveCakeTest() {
		Cake cake = new Cake("Chocolate Cake", "Made of Chocolate", "imageURL");
		when(repository.save(cake)).thenReturn(cake);
		assertEquals(cake, service.createNewCake(cake));
	}
	
	@Test
	public void updateCakeTest() {
		Cake cake = new Cake("Pineapple Cake", "Made of Pineapple", "imageURL");
		when(repository.save(cake)).thenReturn(cake);
        cake.setDesc("Special Cake");
        Cake cakeUpdated =  repository.save(cake);
        Assertions.assertThat(cakeUpdated.getDesc()).isEqualTo("Special Cake");
	}

	@Test
	public void deleteCakeTest() {
		Cake cake = new Cake("Pineapple Cake", "Made of Pineapple", "imageURL");
		when(repository.save(cake)).thenReturn(cake);
		service.deleteCake(cake.getId());
		verify(repository, times(1)).deleteById(cake.getId());
	}

}
