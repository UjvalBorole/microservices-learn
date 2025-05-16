package com.lcwd.user.service.controller;

import com.lcwd.user.service.Service.UserService;

import com.lcwd.user.service.entities.User;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


// import org.hibernate.engine.jdbc.env.internal.LobCreationLogging_.logger;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    //Create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    int retryCount = 1;

    //single user get
    @GetMapping("/{userId}")
//    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    @Retry(name="ratingHotelService", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User>getSingleUser(@PathVariable String userId){
        logger.info("Get Single User Handler : UserController");
        logger.info("Retry count: {}",retryCount);
        retryCount++;
        User user = userService.getUser(userId);
        return  ResponseEntity.ok(user);

    }

    public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex){
        logger.info("Falling is executed because service is down : ",ex.getMessage());
        User user = User
                .builder()
                .email("dummy@gmail.com")
                .name("Dummy")
                .about("This user is created dummy because some service is down")
                .userId("141234")
                .build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    //all user get
    @GetMapping
    public ResponseEntity<List<User>>getAllUSer(){
        List<User>allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }
}
