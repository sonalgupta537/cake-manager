package com.cake.manager.entity;

import org.springframework.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * The Class Cake.
 */
@Entity
public class Cake{
	
	/** The id. */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	/** The title. */
	@NonNull
	@Column(name = "title", nullable = false)
	private String title;

	/** The desc. */
	@NonNull
	@Column(name = "description", nullable = false)
	private String desc;

	/** The image. */
	@NonNull
	@Column(name = "image", nullable = false)
	private String image;

	/**
	 * Instantiates a new cake.
	 */
	public Cake() {
		super();
	}

	/**
	 * Instantiates a new cake.
	 *
	 * @param title the title
	 * @param desc the desc
	 * @param image the image
	 */
	public Cake(String title, String desc, String image) {
		super();
		this.title = title;
		this.desc = desc;
		this.image = image;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the desc.
	 *
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * Sets the desc.
	 *
	 * @param desc the new desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * Gets the image.
	 *
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * Sets the image.
	 *
	 * @param image the new image
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	

}