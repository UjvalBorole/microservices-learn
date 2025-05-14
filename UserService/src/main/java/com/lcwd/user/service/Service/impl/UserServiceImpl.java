package com.lcwd.user.service.Service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.Service.UserService;
import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exception.ResourceNotFoundException;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.repositories.UserRepo;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomeUSerId = UUID.randomUUID().toString();
        user.setUserId(randomeUSerId);
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public User getUser(String uId) {
        User user =  userRepo.findById(uId).orElseThrow(()-> new ResourceNotFoundException("User with id is not found on server !!: " + uId));

        //http://localhost:8083/ratings/users/617c2034-aea4-42f2-a724-7ed25ae2e5af
        Rating[] ratings = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);

        List<Rating> ratingOfUser = Arrays.stream(ratings).toList();

        logger.info("{}",ratingOfUser);
        List<Rating>ratingList = ratingOfUser.stream().map(rating -> {
           //api call to hotel service to get the hotel
//            http://localhost:8082/hotels/4eba51ff-a1c1-4f07-9e09-1a48ac55398a
            // ResponseEntity<Hotel>forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
            // Hotel hotel = forEntity.getBody();
            Hotel hotel = hotelService.getHotel(rating.getHotelId()); //instead of using restTemplate use feign client
            // logger.info("response status code: {}",forEntity.getStatusCode());
            //set the hotel to rating
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;
    }
}
