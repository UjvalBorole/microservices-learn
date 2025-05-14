package com.lcwd.rating.Services;

import com.lcwd.rating.entities.Rating;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingService {
    //create
    Rating create(Rating rating);

    //get all ratings
    List<Rating> getRatings();

    //get all by UserId
    List<Rating>getRatingByUserId(String userId);

    //get all by HotelId
    List<Rating>getRatingByHotelId(String hotelId);


}
