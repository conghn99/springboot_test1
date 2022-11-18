package com.example.springboot_test1.service;

import com.example.springboot_test1.exception.BadRequestException;
import com.example.springboot_test1.exception.NotFoundException;
import com.example.springboot_test1.model.User;
import com.example.springboot_test1.model.UserDTO;
import com.example.springboot_test1.repository.UserRepository;
import com.example.springboot_test1.request.CreateUserRequest;
import com.example.springboot_test1.request.UpdateAvatarRequest;
import com.example.springboot_test1.request.UpdatePasswordRequest;
import com.example.springboot_test1.request.UpdateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Map<String, Object> getUserList(int page, int limit) {
        PagedListHolder pages = new PagedListHolder(userRepository.getUserList());
        pages.setPageSize(limit);
        pages.setPage(page - 1);

        Map<String, Object> response = new HashMap<>();
        response.put("data", pages.getPageList());
        response.put("currentPage", pages.getPage() + 1);
        response.put("size", limit);
        response.put("totalPage", pages.getPageCount());

        return response;
    }

    public List<UserDTO> getUserByName(String name) {
        List<UserDTO> uDtoList = new ArrayList<>();
        List<User> uList = userRepository
                .getUserList()
                .stream()
                .filter(user -> user.getName().toLowerCase(Locale.ROOT).contains(name))
                .collect(Collectors.toList());
        for (User dto : uList) {
            uDtoList.add(convertToDTO(dto));
        }
        return uDtoList;
    }

    public UserDTO getUserById(int id) {
        return convertToDTO(userRepository
                .getUserList()
                .stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null));
    }

    public UserDTO createUser(CreateUserRequest request) {
        int id = userRepository.getUserList().get(userRepository.getUserList().size() - 1).getId() + 1;
        User user = new User(
                id,
                request.getName(),
                request.getEmail(),
                request.getPhone(),
                request.getAddress(),
                null,
                request.getPassword()
        );
        userRepository.getUserList().add(user);
        return convertToDTO(user);
    }

    public UserDTO updateUser(int id, UpdateUserRequest request) {
        for (User user : userRepository.getUserList()) {
            if(user.getId() == id) {
                user.setName(request.getName());
                user.setPhone(request.getPhone());
                user.setAddress(request.getAddress());
                return convertToDTO(user);
            }
        }
        throw new NotFoundException("Not found user with id " + id);
    }

    public void deleteUser(int id) {
        userRepository.getUserList().removeIf(user -> user.getId() == id);
    }

    public void updateAvatar(int id, UpdateAvatarRequest request) {
        for (User user : userRepository.getUserList()) {
            if(user.getId() == id) {
                user.setAvatar(request.getAvatar());
            }
        }
    }

    public void updatePassword(int id, UpdatePasswordRequest request) {
        for (User user : userRepository.getUserList()) {
            if(user.getId() == id) {
                if(user.getPassword().equals(request.getOldPassword())) {
                    if(!request.getOldPassword().equals(request.getNewPassword())) {
                        user.setPassword(request.getNewPassword());
                    } else {
                        throw new BadRequestException("New password must not be the same with old password");
                    }
                } else {
                    throw new BadRequestException("Wrong password");
                }
            }
        }
    }

    public String forgotPassword(int id) {
        for (User user : userRepository.getUserList()) {
            if(user.getId() == id) {
                int leftLimit = 97;
                int rightLimit = 122;
                int len = 10;
                Random random = new Random();
                user.setPassword(random.ints(leftLimit, rightLimit + 1)
                        .limit(len)
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString());
                return user.getPassword();
            }
        }
        throw new NotFoundException("Not found user with id " + id);
    }

    private UserDTO convertToDTO(User model) {
        UserDTO dto = new UserDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setEmail(model.getEmail());
        dto.setPhone(model.getPhone());
        dto.setAddress(model.getAddress());
        dto.setAvatar(model.getAvatar());
        return dto;
    }
}
