package com.notimplement.happygear.service.imp;

import com.notimplement.happygear.entities.User;
import com.notimplement.happygear.model.dto.AccountDto;
import com.notimplement.happygear.model.dto.UserDto;
import com.notimplement.happygear.repositories.RoleRepository;
import com.notimplement.happygear.repositories.UserRepository;
import com.notimplement.happygear.service.UserService;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper mapper;

    @Override
    public List<UserDto> getAllUserDto() {
        return userRepository.findAll()
                .stream()
                .map(v -> mapper.map(v, UserDto.class))
                .toList();
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public UserDto signup(UserDto userDto) {
        String fullName = userDto.getFullName();
        String userName = userDto.getUsername();
        String address = userDto.getAddress();
        String password = userDto.getPassword();
        System.out.println(password);
        String email = userDto.getEmail();
        String phoneNumber = userDto.getPhoneNumber();
        Boolean gender = userDto.getGender();
        Boolean status = true;
        Integer roleId = 2;

        UserDto u = getByUserName(userName);
        if(u!=null){
            return null;
        }
        UserDto newUser = new UserDto(userName,fullName,address,password,email,phoneNumber,status,gender,roleId);
        return createUser(newUser);
    }

    @Override
    public UserDto login(AccountDto accountDto) {
        String username = accountDto.getUsername();
        String password = accountDto.getPassword();
        User user = userRepository.findByUsernameAndPassword(username,password);
        if(user!=null){
            return mapper.map(user, UserDto.class);
        }
        return null;
    }

    @Override
    public UserDto getByUserName(String username) {
        return mapper.map(userRepository.findByUsername(username).orElseThrow(), UserDto.class);
    }

    @Override
    public List<UserDto> getAllActiveUser() {
        return userRepository.findAllUserWithActiveStatus()
                .stream()
                .map(v -> mapper.map(v, UserDto.class))
                .toList();
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = toUser(userDto);
        return mapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public UserDto deleteUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        if(user!=null){
            User savedUser = User.builder()
                    .username(user.getUsername())
                    .fullName(user.getFullName())
                    .password(user.getPassword())
                    .address(user.getAddress())
                    .email(user.getEmail())
                    .phoneNumber(user.getPhoneNumber())
                    .gender(user.getGender())
                    .role(user.getRole())
                    .status(false)
                    .build();
            userRepository.save(savedUser);
            return mapper.map(savedUser, UserDto.class);
        }
        return null;
    }

    @Override
    public UserDto updateUser(UserDto userDto, String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        if(user!=null){
            User updatedUser = toUser(userDto);
            userRepository.save(updatedUser);
            return mapper.map(updatedUser, UserDto.class);
        }
        return null;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = toUser(userDto);
        if(user!=null){
            userRepository.save(user);
            return mapper.map(user, UserDto.class);
        }
        return null;
    }

    @Override
    public List<UserDto> searchByFullName(String name) {
        return userRepository.findByFullNameContainingIgnoreCase(name)
                .stream()
                .map(v -> mapper.map(v, UserDto.class))
                .toList();
    }

    private User toUser(UserDto dto){
        if(dto!=null){
            User u = new User();
            u.setUsername(dto.getUsername());
            u.setPassword(dto.getPassword());
            u.setFullName(dto.getFullName());
            u.setAddress(dto.getAddress());
            u.setEmail(dto.getEmail());
            u.setPhoneNumber(dto.getPhoneNumber());
            u.setStatus(dto.getStatus());
            u.setGender(dto.getGender());
            u.setRole(roleRepository.findById(dto.getRoleId()).orElse(null));
            return u;
        }
        return null;
    }

	@Override
	public Map<List<UserDto>, Long> listByPage(Pageable p) {
		Map<List<UserDto>, Long> pair = new HashMap<List<UserDto>, Long>();
		Page<User> pageList = userRepository.findAll(p);
		pair.put(
            pageList.stream().map(v -> mapper.map(v, UserDto.class)).toList(), 
            pageList.getTotalElements());
		return pair;
	}
}
