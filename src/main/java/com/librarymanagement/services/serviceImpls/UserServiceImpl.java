package com.librarymanagement.services.serviceImpls;

import com.librarymanagement.entities.User;
import com.librarymanagement.mappers.UserMapper;
import com.librarymanagement.models.dtos.userdtos.UserDto;
import com.librarymanagement.models.requests.UserRegisterRequest;
import com.librarymanagement.repositories.UserRepo;
import com.librarymanagement.services.UserService;
import com.librarymanagement.utils.ResponseConstants;
import com.librarymanagement.utils.ResponseUtility;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
private UserRepo userRepo;
    @Override
    public ResponseEntity<Object> registerUser(UserRegisterRequest userRegisterRequest) {
        User user = userRepo.findUserByUsername(userRegisterRequest.getUsername());
        if(user != null){
            return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.ALREADY_EXISTS,
                    "Username should be unique"), HttpStatus.OK );
        }
        user = UserMapper.mapToUser(userRegisterRequest);
        user.setStatus(Boolean.TRUE);
        userRepo.save(user);
        return new ResponseEntity<>(ResponseUtility.successResponseWithMessage(ResponseConstants.CREATED,
                "User registered successfully"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> retrieveUser(String username) {
        User user = userRepo.findUserByUsername(username);
        if(user==null){
            return new ResponseEntity<>(ResponseUtility.failureResponseWithMessage(ResponseConstants.NOT_FOUND,
                    "User doesnt exist"), HttpStatus.OK);
        }
        UserDto userDto = UserMapper.mapUserToBaseUserDto(user);
        return new ResponseEntity<>(ResponseUtility.successResponseWithMessageAndBody(ResponseConstants.OK,
                "User information retrieved", userDto), HttpStatus.OK);
    }
}
