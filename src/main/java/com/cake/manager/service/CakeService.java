package com.cake.manager.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cake.manager.entity.Cake;
import com.cake.manager.entity.UserInfo;
import com.cake.manager.repository.CakeRepository;
import com.cake.manager.repository.UserInfoRepository;

import jakarta.annotation.PostConstruct;

/**
 * The Class CakeService.
 */
@Service
public class CakeService {

	/** The cake data url. */
	@Value("${cake.url}")
	private String cakeDataUrl;

	/** The cake repo. */
	@Autowired
	private CakeRepository cakeRepo;

	/** The repository. */
	@Autowired
	private UserInfoRepository repository;

	/** The password encoder. */
	@Autowired
	private PasswordEncoder passwordEncoder;

	/** The builder. */
	@Autowired
	private RestTemplateBuilder builder;

	/** The rest template. */
	private RestTemplate restTemplate;

	/**
	 * Initalize cake data.
	 */
	@PostConstruct
	public void initalizeCakeData() {
		restTemplate = builder.rootUri(cakeDataUrl).build();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_PLAIN));
		restTemplate.getMessageConverters().add(converter);

		for (Cake cake : getCakes()) {
			cakeRepo.save(cake);
		}
	}
	
	/**
	 * Gets the cakes.
	 *
	 * @return the cakes
	 */
	private List<Cake> getCakes() {
		return restTemplate.exchange(cakeDataUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Cake>>() {
		}).getBody();
	}

	/**
	 * Find all cakes.
	 *
	 * @return the list
	 */
	public List<Cake> findAllCakes() {
		return cakeRepo.findAll();
	}
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 */
	public Optional<Cake> findById(Integer id) {
		return cakeRepo.findById(id);
	}

	/**
	 * Creates the new cake.
	 *
	 * @param cake the cake
	 * @return the cake
	 */
	public Cake createNewCake(Cake cake) {
		return cakeRepo.save(cake);
	}

	/**
	 * Update existing cake.
	 *
	 * @param cake the cake
	 * @return the cake
	 */
	public Cake updateExistingCake(Cake cake) {
		Cake existingCake = cakeRepo.findById(cake.getId()).orElse(null);
        existingCake.setTitle(cake.getTitle());
        existingCake.setDesc(cake.getDesc());
        existingCake.setImage(cake.getImage());
        return cakeRepo.save(existingCake);
	}

	/**
	 * Delete cake.
	 *
	 * @param id the id
	 */
	public void deleteCake(Integer id) {
		cakeRepo.deleteById(id);

	}

	/**
	 * Adds the user.
	 *
	 * @param userInfo the user info
	 * @return the string
	 */
	public String addUser(UserInfo userInfo) {
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		repository.save(userInfo);
		return "user added to the system";
	}

	
}
