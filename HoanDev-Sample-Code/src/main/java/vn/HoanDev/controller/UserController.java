package vn.HoanDev.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vn.HoanDev.dto.request.UserRequestDTO;
import vn.HoanDev.dto.response.ResponseSuccess;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @PostMapping("/")
    public ResponseSuccess addUser(@Valid @RequestBody UserRequestDTO user) {
        return new ResponseSuccess(HttpStatus.CREATED, "added user", 1);
    }

    @PutMapping("/{userId}")
    public ResponseSuccess updateUser(@PathVariable @Min(1) int userId, @Valid @RequestBody UserRequestDTO user) {
        System.out.println("update user" + userId);
        return new ResponseSuccess(HttpStatus.ACCEPTED, "updated user successfully");
    }

    // update một lần thì dùng method patch
    @PatchMapping("/{userId}")
    public ResponseSuccess changeStatus(@PathVariable int userId, @RequestParam(required = false) boolean status) {
//        lưu ý: requestParam để required = false là không bắt buộc phải nhập
        System.out.println("request change status, userId=" + userId);
        return new ResponseSuccess(HttpStatus.ACCEPTED, "changed user successfully");
    }

    @DeleteMapping("{userId}")
    public ResponseSuccess deleteUser(@PathVariable("userId") int id) {
        System.out.println("request delete userId =" + id);
        return new ResponseSuccess(HttpStatus.NO_CONTENT, "deleted user successfully");
    }

    @GetMapping("{userId}")
    public ResponseSuccess getUser(@PathVariable int userId) {
        System.out.println("request get userId=" + userId);
        return new ResponseSuccess(HttpStatus.OK, "user", new UserRequestDTO("Hoan", "Java", "email", "phone"));
    }

    @GetMapping("/list")

    public ResponseSuccess getAllUser(@RequestParam(defaultValue = "0", required = false) int pageNo,
                                      @Min(10) @RequestParam(defaultValue = "20", required = false) int pageSize) {
        System.out.println("Request get all of user");
        return new ResponseSuccess(HttpStatus.OK, "user all", List.of(new UserRequestDTO("Hoan", "Java", "email", "phone"),
                new UserRequestDTO("Ngoc", "Java", "email", "phone")));
    }
}
