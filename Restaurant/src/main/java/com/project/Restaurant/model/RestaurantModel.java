package com.project.Restaurant.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="restaurant")
public class RestaurantModel  {
	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "resId", nullable = false, updatable = false)
		private Long resId;
		
		@Column(name="resName")
		private String resName;
		
		@Column(name="registrationId")
		private String registrationId;
		
		@Column(name="resBranch")
		private String resBranch;
		
		@Column(name="resType")
		private String resType;
		
		@Column(name="address")
		private String address;
		
		@Column(name="canton")
		private String canton;
		
		@Column(name="district")
		private String district;
		
		@Column(name="province")
		private String province;
		
		@Column(name="zipcode")
		private Integer zipcode;
		
		@Column(name="resTelephone")
		private String resTelephone;
		
		@Column(name="resEmail")
		private String resEmail;
		
		@Column(name="resWebsite")
		private String resWebsite;
		
		@Column(name="resImage", length = 1000)
		private byte[] resImage;
		
		@Column(name="description", length = 1000)
		private String description;
		
		@Column(name="resOpen")
		private String resOpen;
		
		@Column(name="resClose")
		private String resClose;
		
		@Column(name="imageURL")
		private String imageURL;
		
//		@OneToMany(mappedBy = "restaurantModel", cascade = CascadeType.ALL)
//		private Set<BookingModel> bookingModel;
		
		public RestaurantModel() {}

		public RestaurantModel(String resName, String registrationId, String resBranch, String resType, String address,
				String canton, String district, String province, Integer zipcode, String resTelephone, String resEmail,
				String resWebsite, byte[] resImage, String description, String resOpen, String resClose, String imageURL) {
			super();
			this.resName = resName;
			this.registrationId = registrationId;
			this.resBranch = resBranch;
			this.resType = resType;
			this.address = address;
			this.canton = canton;
			this.district = district;
			this.province = province;
			this.zipcode = zipcode;
			this.resTelephone = resTelephone;
			this.resEmail = resEmail;
			this.resWebsite = resWebsite;
			this.resImage = resImage;
			this.description = description;
			this.resOpen = resOpen;
			this.resClose = resClose;
			this.imageURL = imageURL;
//			this.bookingModel = (Set<BookingModel>) bookingModel;
		}

		public Long getResId() {
			return resId;
		}

		public void setResId(Long resId) {
			this.resId = resId;
		}

		public String getResName() {
			return resName;
		}

		public void setResName(String resName) {
			this.resName = resName;
		}

		public String getRegistrationId() {
			return registrationId;
		}

		public void setRegistrationId(String registrationId) {
			this.registrationId = registrationId;
		}

		public String getResBranch() {
			return resBranch;
		}

		public void setResBranch(String resBranch) {
			this.resBranch = resBranch;
		}

		public String getResType() {
			return resType;
		}

		public void setResType(String resType) {
			this.resType = resType;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getCanton() {
			return canton;
		}

		public void setCanton(String canton) {
			this.canton = canton;
		}

		public String getDistrict() {
			return district;
		}

		public void setDistrict(String district) {
			this.district = district;
		}

		public String getProvince() {
			return province;
		}

		public void setProvince(String province) {
			this.province = province;
		}

		public Integer getZipcode() {
			return zipcode;
		}

		public void setZipcode(Integer zipcode) {
			this.zipcode = zipcode;
		}

		public String getResTelephone() {
			return resTelephone;
		}

		public void setResTelephone(String resTelephone) {
			this.resTelephone = resTelephone;
		}

		public String getResEmail() {
			return resEmail;
		}

		public void setResEmail(String resEmail) {
			this.resEmail = resEmail;
		}

		public String getResWebsite() {
			return resWebsite;
		}

		public void setResWebsite(String resWebsite) {
			this.resWebsite = resWebsite;
		}

		public byte[] getResImage() {
			return resImage;
		}

		public void setResImage(byte[] resImage) {
			this.resImage = resImage;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
		
		public String getResOpen() {
			return resOpen;
		}

		public void setResOpen(String resOpen) {
			this.resOpen = resOpen;
		}
		
		public String getResClose() {
			return resClose;
		}

		public void setResClose(String resClose) {
			this.resClose = resClose;
		}
		
		public String getImageURL() {
			return imageURL;
		}
		
		public void setImageURL(String imageURL) {
			this.imageURL = imageURL;
		}
		
/*		public Set<BookingModel> getBookingModel() {
			return bookingModel;
		}
		
		public void setBookingModel(Set<BookingModel> bookingModel) {
			this.bookingModel = bookingModel;
		}
*/
		
		
/*		@Override
	    public String toString() {
	        return "RestaurantModel{" +
	                "resId=" + resId +
	                ", resName='" + resName + '\'' +
	                ", registrationId='" + registrationId + '\'' +
	                ", resBranch='" + resBranch + '\'' +
	                ", resType='" + resType + '\'' +
	                ", address='" + address + '\'' +
	                ", canton='" + canton + '\'' +
	                ", district='" + district + '\'' +
	                ", province='" + province + '\'' +
	                ", zipcode='" + zipcode + '\'' +
	                ", resTelephone='" + resTelephone + '\'' +
	                ", resEmail='" + resEmail + '\'' +
	                ", resWebsite='" + resWebsite + '\'' +
	                ", imageURL='" + imageURL + '\'' +
	                ", resImage='" + resImage + '\'' +
	                ", description='" + description + '\'' +
	                ", resOpen='" + resOpen + '\'' +
	                ", resClose='" + resClose + '\'' +
	                '}';
	    } 
*/		
}
