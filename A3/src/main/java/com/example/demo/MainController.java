package com.example.demo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
/**
 *
 * @author DELL
 */
@RestController
@RequestMapping(path ="/user")
public class MainController {
    ArrayList<String> LoggedIns;
    public MainController() {
            LoggedIns=new ArrayList<>();
    }
    @Autowired
    UserService userservice;
    @RequestMapping("")
    public String greeting()
    {
        return("Welcome to Spring Boot Tutorial");
    }
    @GetMapping(path ="/list")
    public List<UserModel> getUsers()
    {
        List <UserModel> usersList = userservice.getusers();
        return usersList;
    }
    @PostMapping("/signup")
    public String SignUp(@RequestBody UserModel User)
    {
        if(userservice.validate(User.email))
        {
            return "ERROR:User Already Exists";
        }
        
            UserModel createdUser = userservice.createUser(User);
            return "User:"+createdUser.email+ " SignUp Successful";
    }
    @PostMapping("/login")
    public String LoginUser(@RequestBody ObjectNode credentials)
    {
            // FOR ObjectNode import https://fasterxml.github.io/jackson-databind/javadoc/2.6/com/fasterxml/jackson/databind/node/ObjectNode.html
           String email = credentials.get("email").asText();
           String password = credentials.get("password").asText();
           boolean responsevalidation=userservice.validate(email);
           if(responsevalidation==false)
           {
               return "Invalid Email and Maybe Password";
           }
           else if(responsevalidation==true)
           {

               if(userservice.GetPassword(email).contentEquals(password))
               {
                  UserModel u=new UserModel(userservice.getCompleteUser(email));
                  if(LoggedIns.contains(email))
                  {
                      LoggedIns.add(email);
                      return "<User Logged In>\nName:"+u.name+"\nEmail:"+u.email+"\nGender:"+u.gender+"\nDateOfBirth:"
                          +u.dobday+"/"+u.dobmonth+"/";
                  }
                  else
                  {
                      return "User Already Logged In";
                  }
               }
           }
           return "Wrong Password !";
    }
    @DeleteMapping(value="/delete/{email}")
    String deleteUser(@PathVariable("email") String email)
    {
            // FOR ObjectNode import https://fasterxml.github.io/jackson-databind/javadoc/2.6/com/fasterxml/jackson/databind/node/ObjectNode.html

               if(LoggedIns.contains(email))
               {
                  if(userservice.deleteUser(email)==false)
                  {
                    LoggedIns.remove(email);
                    return "<User with Email:"+email+" Logged Out & Deleted>";
                  }
                  else
                  {
                      return "Status Code:500";
                  }
               }
               else
               {
                    return "Please login In first";
               }
    }
    @PostMapping(value="/logout")
    String LogoutUser(@RequestBody String email)
    {
            // FOR ObjectNode import https://fasterxml.github.io/jackson-databind/javadoc/2.6/com/fasterxml/jackson/databind/node/ObjectNode.html
               if(LoggedIns.contains(email))
               {
                    LoggedIns.remove(email);
                    return "<User with Email:"+email+" Logged Out>";
               }
               else
               {
                    return "Please login In first";
               }
    }
    @PutMapping(value="/update/{email}")
    String UpdateUser(@PathVariable("email") String email,@RequestBody UserModel User)
    {
            // FOR ObjectNode import https://fasterxml.github.io/jackson-databind/javadoc/2.6/com/fasterxml/jackson/databind/node/ObjectNode.html

//               if(LoggedIns.contains(email))
//               {
                   if(User.email.contentEquals(email))
                   {
                    UserModel u = userservice.updateUser(User);
                      return "<User Updated>\nName:"+u.name+"\nEmail:"+u.email+"\nGender:"+u.gender+"\nDateOfBirth:"
                          +u.dobday+"/"+u.dobmonth+"/";
                   }
                   else
                   {
                       return "Email can't be Updated";
                   }
//               }
//               else
//               {
//                    return "Please login In first";
//               }
    }
    @PutMapping(value="/updatepassword/{email}")
    String UpdateUserPassword(@PathVariable("email") String email,@RequestBody ObjectNode credentials)
    {
           String oldpassword = credentials.get("oldpassword").asText();
           String password = credentials.get("newpassword").asText();
           String confirmpassword = credentials.get("confirmnewpassword").asText();
           if(!password.contentEquals(confirmpassword))
           {
               return "Password Confirmation Error";
           }
           boolean responsevalidation=userservice.validate(email);
           if(responsevalidation==false)
           {
               return "Invalid Email";
           }
           if(userservice.GetPassword(email).contentEquals(oldpassword))
           {
                UserModel u=new UserModel(userservice.getCompleteUser(email));
                u.setPassword(password);
                u=userservice.updateUser(u);
                return "<User Password Updated>\nName:"+u.name+"\nEmail:"+u.email+"\nPassword:"+u.password;
           }
           else
           {
               return "Wrong Password(Old) !";
           }
    }
//@PostMapping("/")
//public ResponseEntity<Student> create(@RequestBody Student student) throws URISyntaxException {
//    Student createdStudent = service.create(student);
//    if (createdStudent == null) {
//        return ResponseEntity.notFound().build();
//    } else {
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//          .path("/{id}")
//          .buildAndExpand(createdStudent.getId())
//          .toUri();
// 
//        return ResponseEntity.created(uri)
//          .body(createdStudent);
//    }
//}


}
