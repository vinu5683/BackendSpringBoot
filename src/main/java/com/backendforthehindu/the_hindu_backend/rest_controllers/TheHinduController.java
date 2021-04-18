package com.backendforthehindu.the_hindu_backend.rest_controllers;

import com.backendforthehindu.the_hindu_backend.entity.News;
import com.backendforthehindu.the_hindu_backend.entity.ReadLater;
import com.backendforthehindu.the_hindu_backend.entity.Users;
import com.backendforthehindu.the_hindu_backend.helper.JwtUtil;
import com.backendforthehindu.the_hindu_backend.model.JwtRequest;
import com.backendforthehindu.the_hindu_backend.model.JwtResponse;
import com.backendforthehindu.the_hindu_backend.service.UsersDetailsService;
import com.backendforthehindu.the_hindu_backend.service.rest_services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TheHinduController {

    AddressService addressService;
    CategoryService categoryService;
    NewsService newsService;
    ReadLaterService readLaterService;
    SubscriptionService subscriptionService;
    UsersService usersService;

    @Autowired
    public TheHinduController(AddressService addressService, CategoryService categoryService, NewsService newsService, ReadLaterService readLaterService, SubscriptionService subscriptionService, UsersService usersService) {
        this.addressService = addressService;
        this.categoryService = categoryService;
        this.newsService = newsService;
        this.readLaterService = readLaterService;
        this.subscriptionService = subscriptionService;
        this.usersService = usersService;
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsersDetailsService usersDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            authenticationManager.authenticate(new
                    UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }
        UserDetails userDetail = usersDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtil.generateToken(userDetail);
        System.out.println("JWT " + token);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @RequestMapping(value = "/news/{id}/{query}", method = RequestMethod.GET)
    public Iterable<News> getNewsWithCatQuery(@PathVariable("id") int id, @PathVariable("query") String q) {
        q = '%' + q + '%';
        return newsService.getNewsWithCatQuery(id, q);
    }

    @RequestMapping(value = "/sign_up/{name}/{pass}/{email}", method = RequestMethod.PUT)
    public String sign_up(@PathVariable("name") String name, @PathVariable("pass") String pass, @PathVariable("email") String email) {
        Users u = new Users();
        u.setName(name);
        u.setEmail(email);
        u.setPassword(pass);
        u.setAddress(0);
        u.setSubscription(0);
        try {
            usersService.addUser(u);
            return "success";
        } catch (Exception e) {
            return "Error";
        }
    }

    @RequestMapping(value = "/allnews", method = RequestMethod.GET)
    public Iterable<News> getAllNews() {
        return newsService.getAllNews();
    }

    @RequestMapping(value = "/newsbycategory/{id}/{str}", method = RequestMethod.GET)
    public Iterable<News> newsWithTopics(@PathVariable("id") int id, @PathVariable("str") String str) {
        return newsService.getNewsByCategory(id, '%' + str + '%');
    }

    @RequestMapping(value = "/getnewsbyid/{id}", method = RequestMethod.GET)
    public Optional<News> getById(@PathVariable("id") int id) {
        return newsService.getNewsById(id);
    }

    @RequestMapping(value = "/userpresent/{email}/{password}", method = RequestMethod.GET)
    public boolean isUserPresent(@PathVariable("email") String email, @PathVariable("password") String password) {
        return usersService.isUserPresent(email, password);
    }

    @RequestMapping(value = "/findbycategory/{id}", method = RequestMethod.GET)
    public Iterable<News> getByCategoryId(@PathVariable("id") int id) {
        return newsService.findAllWithCategory(id);
    }

    @RequestMapping(value = "/save_news/{id}", method = RequestMethod.PUT)
    public void saveNewsById(@RequestHeader(name = "Authorization") String token, @PathVariable("id") int id) {
        token = token.substring(7);
        Users u = usersService.findByName(jwtUtil.extractUsername(token)).iterator().next();
        readLaterService.saveNewsById(u.getId(), id);
        System.out.println("saved");
    }

    @RequestMapping(value = "/isnewspresent/{id}", method = RequestMethod.GET)
    public boolean checkForSavedNews(@RequestHeader(name = "Authorization") String token, @PathVariable("id") int id) {
        token = token.substring(7);
        System.out.println("called " + id);
        Users u = usersService.findByName(jwtUtil.extractUsername(token)).iterator().next();
        boolean x = readLaterService.isThisNewsPresent(id, u.getId());
        if (!x) {
            readLaterService.saveNewsById(u.getId(), id);
        }
        return x;
    }

    @RequestMapping(value = "/getSavedList/{uid}", method = RequestMethod.GET)
    public Iterable<ReadLater> getSavedList(@RequestHeader(name = "Authorization") String token, @PathVariable("uid") int uid) {
        token = token.substring(7);
        Users u = usersService.findByName(jwtUtil.extractUsername(token)).iterator().next();
        System.out.println(u.getId());
        return readLaterService.findByUserId(u.getId());
    }

    @RequestMapping(value = "/getallsavednews", method = RequestMethod.GET)
    public Iterable<News> getAllSavedNews(@RequestHeader(name = "Authorization") String token) {
        token = token.substring(7);
        Users u = usersService.findByName(jwtUtil.extractUsername(token)).iterator().next();
        Iterable<ReadLater> list = readLaterService.findByUserId(u.getId());
        List<Integer> ids = new ArrayList<>();
        for (ReadLater i : list
        ) {
            ids.add(i.getNews_id());
        }
        return newsService.findAllSavedNews(ids);
    }

    @RequestMapping(value = "/delete_entry/{id}", method = RequestMethod.DELETE)
    public boolean deleteByNewsId(@RequestHeader(name = "Authorization") String token, @PathVariable("id") int id) {
        token = token.substring(7);
        Users u = usersService.findByName(jwtUtil.extractUsername(token)).iterator().next();
        readLaterService.deleteSavedEntry(u.getId(), id);
        return true;
    }

    @RequestMapping(value = "/isuservalid", method = RequestMethod.GET)
    public boolean isUserValid(@RequestHeader(name = "Authorization") String token) {
        token = token.substring(7);
        try {
            Users u = usersService.findByName(jwtUtil.extractUsername(token)).iterator().next();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @RequestMapping(value = "/getuser", method = RequestMethod.GET)
    public Users getUser(@RequestHeader(name = "Authorization") String token) {
        token = token.substring(7);
        try {
            return usersService.findByName(jwtUtil.extractUsername(token)).iterator().next();
        } catch (Exception e) {
            System.out.println("Error");
            return null;
        }
    }

    @RequestMapping(value = "/updateuser/{a}/{b}/{c}", method = RequestMethod.PATCH)
    public void updateUser(@RequestHeader(name = "Authorization") String token, @PathVariable("a") String a
            , @PathVariable("b") String b, @PathVariable("c") String c) {
        token = token.substring(7);
        try {
            Users u = usersService.findByName(jwtUtil.extractUsername(token)).iterator().next();
            u.setName(a);
            u.setPhone(Integer.parseInt(b));
            u.setDob(Date.valueOf(LocalDate.now()));
            usersService.updateNow(u);

        } catch (Exception e) {
            System.out.println("Error");
            return;
        }
    }
}