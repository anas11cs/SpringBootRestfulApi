/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


        
import org.springframework.data.repository.CrudRepository;
import com.example.demo.UserModel;
import com.example.demo.UserRepository;


/**
 *
 * @author DELL
 */
@Service
public class UserService {
    
    @Autowired
    UserRepository userrepository;
    
    List<UserModel> getusers()
    {
        List<UserModel> usersList=userrepository.findAll();
        return usersList;
    }
    UserModel createUser(UserModel user)
    {
        return userrepository.save(new UserModel(user));
    }
    boolean validate(String email)
    {
        return userrepository.existsById(email);
    }
    String GetPassword(String email)
    {
        return userrepository.findById(email).get().password;
    }
    UserModel getCompleteUser(String email)
    {
       return userrepository.findById(email).get();
    }
    boolean deleteUser(String email)
    {
        userrepository.deleteById(email);
        return userrepository.existsById(email);
    }
    UserModel updateUser(UserModel user)
    {
        return userrepository.save(user); 
    }
}
