package com.example.springboot_test1.controller;

import com.example.springboot_test1.model.UserDTO;
import com.example.springboot_test1.request.CreateUserRequest;
import com.example.springboot_test1.request.UpdateAvatarRequest;
import com.example.springboot_test1.request.UpdatePasswordRequest;
import com.example.springboot_test1.request.UpdateUserRequest;
import com.example.springboot_test1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    // 1. Lấy danh sách users (có phân trang - pagination)
    @GetMapping("users")
    public Map<String, Object> geUserList(@RequestParam (required = false, defaultValue = "1") int page, @RequestParam (required = false, defaultValue = "10") int limit) {
        return userService.getUserList(page, limit);
    }

    // 2. Tìm kiếm user theo tên
    @GetMapping("search")
    public List<UserDTO> getUserByName(@RequestParam String name) {
        return userService.getUserByName(name);
    }

    // 3. Lấy chi tiết user theo id
    @GetMapping("users/{id}")
    public UserDTO getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    // 4. Tạo mới user
    @PostMapping("users")
    public UserDTO postUser(@RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

    // 5. Cập nhật thông tin user
    @PutMapping("users/{id}")
    public UserDTO updateUser(@PathVariable int id, @RequestBody UpdateUserRequest request) {
        return userService.updateUser(id, request);
    }

    // 6. Xóa user
    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

    // 7. Thay đổi ảnh avatar
    @PutMapping("users/{id}/update-avatar")
    public void updateAvatar(@PathVariable int id, @RequestBody UpdateAvatarRequest request) {
        userService.updateAvatar(id, request);
    }

    // 8. Thay đổi mật khẩu
    @PutMapping("users/{id}/update-password")
    public void updatePassword(@PathVariable int id, @RequestBody UpdatePasswordRequest request) {
        userService.updatePassword(id, request);
    }

    // 9. Quên mật khẩu
    @PostMapping("users/{id}/fotgot-password")
    public String newPassword(@PathVariable int id) {
        return userService.forgotPassword(id);
    }
}
