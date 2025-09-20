package com.paypal.food_outlet.controller;

import com.paypal.food_outlet.model.ApiResponse;
import com.paypal.food_outlet.model.Outlet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import org.springframework.web.util.UriComponentsBuilder;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class FoodOutletController {

    RestTemplate restTemplate = new RestTemplate();

    public ApiResponse getFoodOutlets(String city, int page) {
        String url = UriComponentsBuilder
                .fromHttpUrl("https://jsonmock.hackerrank.com/api/food_outlets")
                .queryParam("city", city)
                .queryParam("page", page)
                .toUriString();

        return restTemplate.getForObject(url, ApiResponse.class);
    }

    @GetMapping("/top-outlets")
    private List<String> getTopRatedFoodOutlets(@RequestParam String city){
       List<Outlet> allOutlets = new ArrayList<>();

       int currentPage = 1;
       int totalPages = 1;

       // Fetch all paginated data
       while(currentPage <= totalPages){
           ApiResponse response = getFoodOutlets(city,currentPage);

           if(response != null && response.data != null){
               allOutlets.addAll(response.data);
               totalPages = response.total_pages;
           }
           currentPage++;
       }

        // Find max rating
       double maxRating = allOutlets.stream().mapToDouble(o -> o.user_rating.average_rating).max().orElse(0);

       // Filter outlets with maxRating
       return allOutlets.stream()
               .filter(o -> o.user_rating.average_rating == maxRating)
               .map(o->o.name)
               .collect(Collectors.toList());
    }


}
