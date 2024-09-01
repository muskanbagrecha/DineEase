package com.mb.DineEase.request.restaurant;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mb.DineEase.model.address.Address;
import com.mb.DineEase.model.ContactInformation;
import com.mb.DineEase.model.address.Location;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateRestaurantRequest {
    @NotNull
    private String name;
    @NotNull
    private Address address;
    private String description;
    private String openingHours;
    private List<String> cuisine;
    private List<String> images;
    @NotNull
    private ContactInformation contactInformation;
    @NotNull
    private Location location;
    @NotNull
    private String restaurantManagerId;

}
