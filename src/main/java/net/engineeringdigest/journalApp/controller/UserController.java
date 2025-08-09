package net.engineeringdigest.journalApp.controller;


import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import net.engineeringdigest.journalApp.service.UserService;
import net.engineeringdigest.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;

//    @GetMapping
//    public List<User> getAllUsers()
//    {
//        return userService.getAll();
//    }
//    @PostMapping
//    public void createUser(@RequestBody User user)
//    {
//        userService.saveEntry(user);
//    }
    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody User user)
    {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String userName = authentication.getName();
      User userInDb = userService.findByUserName(userName);
      if(userInDb != null)
      {
          userInDb.setUserName(user.getUserName());
          userInDb.setPassword(user.getPassword());
          userService.saveNewUser(userInDb);
      }
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteUserById()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping()
    public ResponseEntity<?> greeting()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("Mumbai");
        String greeting = "";
        if(weatherResponse != null){
            greeting = ",weather Feels like "+ weatherResponse.getCurrent().getIs_day() + weatherResponse.getCurrent().getLast_updated() + weatherResponse.getCurrent().getTemp_c();
        }
        return new ResponseEntity<>("Hiiii" + " " +authentication.getName()+greeting,HttpStatus.OK);
    }

}
