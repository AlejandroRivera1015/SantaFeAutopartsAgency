package com.autoparts.SantaFeCarsAgency.Service.User;

import com.autoparts.SantaFeCarsAgency.DTO.User.UserDTO;
import com.autoparts.SantaFeCarsAgency.Entity.User;
import com.autoparts.SantaFeCarsAgency.Repository.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements  UserService {

    @Autowired
    UserRepository userRepository;
@Override
    public UserDTO login(String email, String password) {

    try{
        Optional<User> user = userRepository.findByEmailAndPassword(email, password);

        if(user.isPresent()){
            return new UserDTO(user.get().getId(), user.get().getName());
        }
        else {
            return new UserDTO(0L, "User not found");
        }
    }catch (Exception e){
        System.out.println("Error searching user / userServiceImpl: " + e.getMessage());
        return null;

    }

    }
}
