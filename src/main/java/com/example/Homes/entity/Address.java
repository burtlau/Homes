package com.example.Homes.entity;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Address {
    private String country;
    private String city;
    private String postCode;

    public Address(String country, String city, String postCode) {
        this.country = country;
        this.city = city;
        this.postCode = postCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Address)) return false;
        var address = (Address) obj;
        return country.equals(address.country) &&
                city.equals(address.city) &&
                postCode.equals(address.postCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), country, city, postCode);
    }
}
