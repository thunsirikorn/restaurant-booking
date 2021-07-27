package com.project.Restaurant.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.project.Restaurant.model.RestaurantModel;
import com.project.Restaurant.service.RestaurantServ;


//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class RestaurantControl {
	
	private final RestaurantServ restaurantServ;
	
	private String imageURL;
	private byte[] resImage;
	
	public RestaurantControl(RestaurantServ restaurantServ) {
		this.restaurantServ = restaurantServ;
	}
	
	@GetMapping("/allrest")
	public ResponseEntity<List<RestaurantModel>> getAllRestaurants() {
		List<RestaurantModel> restaurants = restaurantServ.findAllRestaurants();
		return new ResponseEntity<>(restaurants, HttpStatus.OK);
	}
	
	@GetMapping("/findrest/{resId}")
	public ResponseEntity<RestaurantModel> getRestaurantById(@PathVariable("resId") Long resId) {
		RestaurantModel restaurant = restaurantServ.findRestaurantById(resId);
		return new ResponseEntity<>(restaurant, HttpStatus.OK);
	}
	
/*	@PostMapping("/addrest")
	public ResponseEntity<RestaurantModel> addRestaurant(@RequestBody RestaurantModel restaurantModel) {
		RestaurantModel newRestaurant = restaurantServ.addRestaurant(restaurantModel);
		return new ResponseEntity<>(newRestaurant, HttpStatus.CREATED);
	}
*/
	
/*	@PostMapping("/upload")
	public void uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
		this.img = file.getBytes();	
	}
	
	
	@PostMapping("/addrest")
	public ResponseEntity<RestaurantModel> addRestaurant(@RequestBody RestaurantModel restaurantModel) {
		
		restaurantModel.setResImage(this.img);
		RestaurantModel newRestaurant = restaurantServ.addRestaurant(this.img, restaurantModel);
		
		return new ResponseEntity<>(newRestaurant, HttpStatus.CREATED);
	}
*/
	
	@PostMapping("/upload")
	public BodyBuilder uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
		
		System.out.println("Original Image Byte Size - " + file.getBytes().length);
		this.imageURL = file.getOriginalFilename();
		this.resImage = file.getBytes();
		return ResponseEntity.status(HttpStatus.OK);
	}
	
	@PostMapping("/addrest")
	public ResponseEntity<RestaurantModel> addRestaurant(@RequestBody RestaurantModel restaurantModel) {
		
		restaurantModel.setImageURL(imageURL);
		restaurantModel.setResImage(resImage);
		RestaurantModel newRestaurant = restaurantServ.addRestaurant(restaurantModel);
		
		return new ResponseEntity<>(newRestaurant, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/uploadud")
	public BodyBuilder updateImage(@RequestParam("imageUpdate") MultipartFile file) throws IOException {
		
		System.out.println("Original Image Byte Size - " + file.getBytes().length);
		this.imageURL = file.getOriginalFilename();
		this.resImage = file.getBytes();
		return ResponseEntity.status(HttpStatus.OK);
	}
	
	@PutMapping("/updaterest")
	public ResponseEntity<RestaurantModel> updateRestaurant(@RequestBody RestaurantModel restaurantModel) {
		restaurantModel.setImageURL(imageURL);
		restaurantModel.setResImage(resImage);
		RestaurantModel updateRestaurant = restaurantServ.updateRestaurant(restaurantModel);
		return new ResponseEntity<>(updateRestaurant, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleterest/{resId}")
	public ResponseEntity<?> deleteRestaurant(@PathVariable("resId") Long resId) {
		restaurantServ.deleteRestaurant(resId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

	@GetMapping(path = {"/image/{resId}"})
	public RestaurantModel getImage(@PathVariable("resId") Long resId) throws IOException {

		final Optional<RestaurantModel> retrievedImage = restaurantServ.findImageById(resId);
		this.imageURL = retrievedImage.get().getImageURL();
		this.resImage = decompressBytes(retrievedImage.get().getResImage());

		return null;
	}
	
	
	// compress the image bytes before storing it in the database
		public static byte[] compressBytes(byte[] data) {
			Deflater deflater = new Deflater();
			deflater.setInput(data);
			deflater.finish();

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
			byte[] buffer = new byte[1024];
			while (!deflater.finished()) {
				int count = deflater.deflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			try {
				outputStream.close();
			} catch (IOException e) {
			}
			System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

			return outputStream.toByteArray();
		}
	

		// uncompress the image bytes before returning it to the angular application
		public static byte[] decompressBytes(byte[] data) {
			Inflater inflater = new Inflater();
			inflater.setInput(data);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
			byte[] buffer = new byte[1024];
			try {
				while (!inflater.finished()) {
					int count = inflater.inflate(buffer);
					outputStream.write(buffer, 0, count);
				}
				outputStream.close();
			} catch (IOException ioe) {
			} catch (DataFormatException e) {
			}
			return outputStream.toByteArray();
		}

}
