package com.example.demo.controler;

import com.example.demo.model.UserRequestModel;
import com.example.demo.model.UserResponseModel;
import com.example.demo.service.UserService;
import com.example.demo.shared.UserDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserControler {

    @Autowired
    UserService userService;
    @Autowired
    Environment environment;

    @GetMapping("/status/details")
    public String status(){
        return "it's working fine :"+ 1 +"Token secret key :" + environment.getProperty("token.secret");
    }



    @PostMapping(consumes = {
            MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_XML_VALUE,  MediaType.APPLICATION_JSON_VALUE }
            )
    public ResponseEntity<UserResponseModel> createUser(@Validated @RequestBody UserRequestModel userRequestModel){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDTO userDTO =  modelMapper.map(userRequestModel,UserDTO.class);

        UserDTO returnValue = userService.createUser(userDTO);

        UserResponseModel responseModel = modelMapper.map(returnValue,UserResponseModel.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
    }
}
