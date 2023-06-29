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

    // @GetMapping("")
    // public ResponseEntity<?> getAllUser(){
    //     List<UserDto> listUser = userService.getAllActiveUser();
    //     return ResponseEntity.ok(listUser);
    // }

    // @GetMapping("/{username}")
    // public ResponseEntity<?> getUserByUserName(@PathVariable(name = "username") String username){
    //     UserDto userDto = userService.getByUserName(username);
    //     if(userDto==null) return ResponseEntity.noContent().build();
    //     return ResponseEntity.ok(userDto);
    // }
    
    // @GetMapping("/")
    // public ResponseEntity<?> getAllByPage(@RequestParam("p") Optional<Integer> p){
    // 	Pageable pageable = PageRequest.of(p.orElse(0), 8);
    // 	Map<List<UserDto>, Long> listMap = userService.listByPage(pageable);
    // 	List<Object> list = new ArrayList<>();
    // 	listMap.forEach((userDtos, count)->{
    // 		list.add(userDtos);
    // 		list.add(count);
    // 	});
    // 	return ResponseEntity.ok(list);
    // }

    // @PostMapping("/create")
    // public ResponseEntity<?> createUser(@RequestBody UserDto userDto){
    //     UserDto newUserDto = userService.createUser(userDto);
    //     return ResponseEntity.ok(newUserDto);
    // }

    // @DeleteMapping("/delete/{username}")
    // public ResponseEntity<?> deleteUser(@PathVariable(name = "username") String username){
    //     UserDto deletedUserDto = userService.deleteUser(username);
    //     return ResponseEntity.ok(deletedUserDto);
    // }

    // @PutMapping("/update/{username}")
    // public ResponseEntity<?> updateUser(@RequestBody UserDto userDto, @PathVariable(name = "username") String username){
    //     UserDto updateUserDto = userService.updateUser(userDto, username);
    //     return ResponseEntity.ok(updateUserDto);
    // }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AccountDto accountDto){
        UserDto userDto = userService.login(accountDto);
        if(userDto!=null)
            return ResponseEntity.ok(userDto);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserInfoDto userInfoDto){
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

    @GetMapping("/{username}/orders")
    public ResponseEntity<?> getOrdersByUsername(@PathVariable(name = "username") String username){
        List<OrderDto> list = userService.getOrdersByUsername(username);
        return ResponseEntity.ok(list);
    }
}
