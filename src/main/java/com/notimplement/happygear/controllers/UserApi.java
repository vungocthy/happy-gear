package com.notimplement.happygear.controllers;

import com.notimplement.happygear.model.dto.AccountDto;
import com.notimplement.happygear.model.dto.OrderDto;
import com.notimplement.happygear.model.dto.UserDto;
import com.notimplement.happygear.model.dto.UserInfoDto;
import com.notimplement.happygear.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUserName(@PathVariable(name = "username") String username){
        UserDto userDto = userService.getByUserName(username);
        if(userDto==null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AccountDto accountDto){
        UserDto userDto = userService.login(accountDto);
        if(userDto!=null)
            return ResponseEntity.ok(userDto);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/google/auth")
    public ResponseEntity<?> googleAuth(@RequestBody UserInfoDto userInfoDto){
        UserDto existUserDto = userService.getByUserName(userInfoDto.getUid());
        if(existUserDto==null){
            UserDto userDto = UserDto.builder()
                    .username(userInfoDto.getUid())
                    .fullName(userInfoDto.getDisplayName())
                    .email(userInfoDto.getEmail())
                    .status(true)
                    .build();
            UserDto user = userService.signup(userDto);
            return ResponseEntity.created(null).body(user);
        }
        return ResponseEntity.ok(existUserDto);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto){
        UserDto existUserDto = userService.getByUserName(userDto.getUsername());
        UserDto existEmail = userService.getUserByEmail(userDto.getEmail());
        if(existUserDto==null){
            userDto.setStatus(true);
            userDto.setRoleId(2);
            UserDto user = userService.signup(userDto);
            return ResponseEntity.created(null).body(user);
        }
        else if(existEmail!=null){
            return ResponseEntity.badRequest().body("Email already exists");
        }
        return ResponseEntity.ok(existUserDto);
    }

    @GetMapping("/{username}/orders")
    public ResponseEntity<?> getOrdersByUsername(@PathVariable(name = "username") String username){
        List<OrderDto> list = userService.getOrdersByUsername(username);
        return ResponseEntity.ok(list);
    }
}
