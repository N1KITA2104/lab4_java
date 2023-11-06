package org.example;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.*;

import java.util.Objects;
import java.util.Set;

public class Transport {
    @NotBlank(message = "Brand must not be blank")
    private String brand;
    @NotBlank(message = "Model must not be blank")
    private String model;
    @Positive(message = "Weight must be positive")
    @Min(1)
    private double weight;
    @Min(value = 1900, message = "Production year must be >= 1900")
    @Max(value = 2100, message = "Production year must be <= 2100")
    private int productionYear;
    @PositiveOrZero(message = "Price must be >= 0")
    private double price;

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setTransportWeight(double weight) {
        this.weight = weight;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    /**
     * Constructs a new Transport object using the provided builder.
     *
     * @param builder The builder used to construct the Transport object.
     */
//    @JsonCreator
    public Transport(Builder builder) {
        this.brand = builder.brand;
        this.model = builder.model;
        this.weight = builder.weight;
        this.productionYear = builder.productionYear;
        this.price = builder.price;
    }

    public Transport () {

    }
    /**
     * Get the brand of the transportation vehicle.
     *
     * @return The brand of the transportation vehicle.
     */

    public String getBrand() {
        return brand;
    }

    /**
     * Get the model of the transportation vehicle.
     *
     * @return The model of the transportation vehicle.
     */

    public String getModel() {
        return model;
    }

    /**
     * Get the type of the transportation vehicle.
     *
     * @return The type of the transportation vehicle.
     */

    public double getWeight() {
        return weight;
    }

    /**
     * Get the production year of the transportation vehicle.
     *
     * @return The production year of the transportation vehicle.
     */

    public int getProductionYear() {
        return productionYear;
    }

    /**
     * Get the price of the transportation vehicle.
     *
     * @return The price of the transportation vehicle.
     */

    public double getPrice() {
        return price;
    }

    /**
     * Returns a string representation of the Transport object.
     *
     * @return A string representation of the Transport object.
     */
    @Override
    public String toString() {
        return "Transport{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", weight='" + weight + '\'' +
                ", productionYear=" + productionYear +
                ", price=" + price +
                '}';
    }

    /**
     * Compares two Transport objects for equality.
     *
     * @param o The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transport transport = (Transport) o;
        return productionYear == transport.productionYear &&
                Double.compare(transport.price, price) == 0 &&
                brand.equals(transport.brand) &&
                model.equals(transport.model) &&
                Double.compare(transport.weight, weight) == 0;
    }

    /**
     * Returns the hash code of the Transport object.
     *
     * @return The hash code of the Transport object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(brand, model, productionYear, price, weight);
    }

    /**
     * The Builder class for constructing a Transport object with parameters.
     */
    public static class Builder {
        @NotBlank(message = "Brand must not be blank")
        private String brand;
        @NotBlank(message = "Model must not be blank")
        private String model;
        @Positive(message = "Weight must be positive")
        @Min(1)
        private double weight;
        @Min(value = 1900, message = "Production year must be >= 1900")
        @Max(value = 2100, message = "Production year must be <= 2100")
        private int productionYear;
        @PositiveOrZero(message = "Price must be >= 0")
        private double price;
        /**
         * Constructs a new Builder for creating a Transport object.
         *
         * @param brand        The brand of the transportation vehicle.
         * @param model        The model of the transportation vehicle.
         * @param weight The type of the transportation vehicle.
         */
        public Builder(String brand, String model, double weight) {
            this.brand = brand;
            this.model = model;
            this.weight = weight;
        }

        /**
         * Set the production year of the transportation vehicle.
         *
         * @param productionYear The production year to set.
         * @return The Builder object for method chaining.
         */
        public Builder setProductionYear(int productionYear) {
            this.productionYear = productionYear;
            return this;
        }

        /**
         * Set the price of the transportation vehicle.
         *
         * @param price The price to set.
         * @return The Builder object for method chaining.
         */
        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        /**
         * Build a Transport object using the information provided in the Builder.
         *
         * @return A new Transport object.
         */
        public Transport build() {
            String errors = validate(new Transport(this));
            if (errors.length() > 0) {
                throw new IllegalArgumentException("Validation failed:" + System.lineSeparator() + errors);
            }
            return new Transport(this);
        }

        protected String validate(Transport transport) {
            ValidatorFactory validation = Validation.buildDefaultValidatorFactory();
            Validator validator = validation.getValidator();
            Set<ConstraintViolation<Transport>> errors = validator.validate(transport);
            StringBuilder sb = new StringBuilder();

            for (ConstraintViolation<Transport> violation : errors) {
                sb.append(violation.getPropertyPath().toString()).append(": ").append(violation.getInvalidValue()).append(". ").append(violation.getMessage()).append(System.lineSeparator());
            }
            return sb.toString();
        }
    }
}

