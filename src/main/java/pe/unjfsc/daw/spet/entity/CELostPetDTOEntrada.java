package pe.unjfsc.daw.spet.entity;

import java.util.Date;

public class CELostPetDTOEntrada {

	private int idPet;
	private String user;
	private String pet;
	private double latitude;
	private double longitude;
	private String description;
	private boolean located;
	private int status;
	private Date createdAt;
	
	public int getIdPet() {
		return idPet;
	}
	public void setIdPet(int idPet) {
		this.idPet = idPet;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPet() {
		return pet;
	}
	public void setPet(String pet) {
		this.pet = pet;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isLocated() {
		return located;
	}
	public void setLocated(boolean located) {
		this.located = located;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CELostPetDTOEntrada [idPet=");
		builder.append(idPet);
		builder.append(", user=");
		builder.append(user);
		builder.append(", pet=");
		builder.append(pet);
		builder.append(", latitude=");
		builder.append(latitude);
		builder.append(", longitude=");
		builder.append(longitude);
		builder.append(", description=");
		builder.append(description);
		builder.append(", located=");
		builder.append(located);
		builder.append(", status=");
		builder.append(status);
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append("]");
		return builder.toString();
	}
	
	
}
