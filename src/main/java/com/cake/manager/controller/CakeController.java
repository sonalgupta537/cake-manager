package com.cake.manager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cake.manager.entity.AuthRequest;
import com.cake.manager.entity.Cake;
import com.cake.manager.entity.UserInfo;
import com.cake.manager.service.CakeService;
import com.cake.manager.service.JwtService;

/**
 * The Class CakeController.
 */
@RestController
@RequestMapping("/cakes")
public class CakeController {

	/** The cake service. */
	@Autowired
	private CakeService cakeService;
	
	/** The jwt service. */
	@Autowired
	private JwtService jwtService;
	
	/** The authentication manager. */
	@Autowired
    private AuthenticationManager authenticationManager;
	
	/**
	 * Adds the new user.
	 *
	 * @param userInfo the user info
	 * @return the string
	 */
	@PostMapping("/newUser")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return cakeService.addUser(userInfo);
    }
	
	/**
	 * Authenticate and get token.
	 *
	 * @param authRequest the auth request
	 * @return the string
	 */
	@PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getName());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

	/**
	 * Gets the all cakes.
	 *
	 * @return the all cakes
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Cake>> getAllCakes() {
		List<Cake> cakes = cakeService.findAllCakes();
		return new ResponseEntity<>(cakes, HttpStatus.OK);
	}
	
	/**
	 * Gets the cake by id.
	 *
	 * @param id the id
	 * @return the cake by id
	 */
	@GetMapping("/getCake/{id}")
	public ResponseEntity<Cake> getCakeById(@PathVariable("id") Integer id) {
		Optional<Cake> cake = cakeService.findById(id);

		if (cake.isPresent()) {
			return new ResponseEntity<>(cake.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Adds the cake.
	 *
	 * @param cake the cake
	 * @return the response entity
	 */
	@PostMapping("/addCake")
	public ResponseEntity<Cake> addCake(@RequestBody Cake cake) {
		Cake newCake = cakeService.createNewCake(cake);
		return new ResponseEntity<>(newCake, HttpStatus.CREATED);
	}
	
	/**
	 * Update cake.
	 *
	 * @param Cake the cake
	 * @return the response entity
	 */
	@PutMapping("/updateCake")
	public ResponseEntity<Cake> updateCake(@RequestBody Cake Cake) {
		Cake updateCake = cakeService.updateExistingCake(Cake);
		return new ResponseEntity<>(updateCake, HttpStatus.OK);
	}

	/**
	 * Delete cake.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("/deleteCake/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> deleteCake(@PathVariable("id") Integer id) {
		cakeService.deleteCake(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
