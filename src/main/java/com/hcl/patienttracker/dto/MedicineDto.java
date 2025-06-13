package com.hcl.patienttracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



public class MedicineDto {
    private Long id;

    @NotBlank(message = "Medicine ID is mandatory")
    private String medicineId;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Manufacturer is mandatory")
    private String manufacturer;

    @NotNull(message = "Price is mandatory")
    private Double price;

    @NotNull(message = "Quantity is mandatory")
    private Integer stock;

    @NotBlank(message = "Expiry date is mandatory")
    private String expiryDate;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}
