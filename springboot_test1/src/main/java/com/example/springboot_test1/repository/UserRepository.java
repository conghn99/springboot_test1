package com.example.springboot_test1.repository;

import com.example.springboot_test1.model.User;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private List<User> userList = new ArrayList<>(List.of(
            new User(1, "user1", "user1@gmail.com", "0987654321", "Ha Noi", "avatar1", "password1"),
            new User(2, "user2", "user2@gmail.com", "0912345678", "Ho Chi Minh", "avatar2", "password2"),
            new User(3, "user3", "user3@gmail.com", "0949948170", "Hai Phong", "avatar3", "password3"),
            new User(4, "user4", "user4@gmail.com", "0914342225", "Da Nang", "avatar4", "password4"),
            new User(5, "user5", "user5@gmail.com", "0912354734", "Thanh Hoa", "avatar5", "password5"),
            new User(6, "user6", "user6@gmail.com", "0914342225", "Da Nang", "avatar6", "password6"),
            new User(7, "user7", "user7@gmail.com", "0914342225", "Hung Yen", "avatar7", "password7"),
            new User(8, "user8", "user8@gmail.com", "0914342225", "Phu Tho", "avatar8", "password8"),
            new User(9, "user9", "user9@gmail.com", "0914342225", "Vinh Phuc", "avatar9", "password9"),
            new User(10, "user10", "user10@gmail.com", "0914342225", "Quang Binh", "avatar10", "password10"),
            new User(11, "user11", "user11@gmail.com", "0914342225", "Quang Nam", "avatar11", "password11"),
            new User(12, "user12", "user12@gmail.com", "0914342225", "Vinh", "avatar12", "password12"),
            new User(13, "user13", "user13@gmail.com", "0914342225", "Hai Duong", "avatar13", "password13"),
            new User(14, "user14", "user14@gmail.com", "0914342225", "Bac Giang", "avatar14", "password14"),
            new User(15, "user15", "user15@gmail.com", "0914342225", "Ha Giang", "avatar15", "password15"),
            new User(16, "user16", "user16@gmail.com", "0914342225", "Binh Duong", "avatar16", "password16"),
            new User(17, "user17", "user17@gmail.com", "0914342225", "Can Tho", "avatar17", "password17"),
            new User(18, "user18", "user18@gmail.com", "0914342225", "Da Lat", "avatar18", "password18"),
            new User(19, "user19", "user19@gmail.com", "0914342225", "Kien Giang", "avatar19", "password19"),
            new User(20, "user20", "user20@gmail.com", "0914342225", "Tien Giang", "avatar20", "password20"),
            new User(21, "user21", "user21@gmail.com", "0914342225", "Hau Giang", "avatar21", "password21"),
            new User(22, "user22", "user22@gmail.com", "0914342225", "Quang Ngai", "avatar22", "password22")
    ));

    public List<User> getUserList() {
        return userList;
    }
}
