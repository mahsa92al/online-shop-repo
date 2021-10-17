package model;

import java.util.Objects;

/**
 * @author Mahsa Alikhani m-58
 */
public class Address {
    private int id;
    private String country;
    private String state;
    private String city;
    private String additionalInfo;
    private String postalCode;
    private Customer customer;

    public Address(String country, String state, String city, String additionalInfo, String postalCode, Customer customer) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.additionalInfo = additionalInfo;
        this.postalCode = postalCode;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id == address.id && Objects.equals(country, address.country) && Objects.equals(state, address.state) && Objects.equals(city, address.city) && Objects.equals(additionalInfo, address.additionalInfo) && Objects.equals(postalCode, address.postalCode) && Objects.equals(customer, address.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, state, city, additionalInfo, postalCode, customer);
    }
}
