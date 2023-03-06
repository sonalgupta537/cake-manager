package com.cake.manager.entity;

/**
 * The Class AuthRequest.
 */
public class AuthRequest {

	/** The name. */
	private String name;
	
	/** The password. */
	private String password;

	/**
	 * Instantiates a new auth request.
	 */
	public AuthRequest() {
		super();
	}

	/**
	 * Instantiates a new auth request.
	 *
	 * @param name the name
	 * @param password the password
	 */
	public AuthRequest(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
