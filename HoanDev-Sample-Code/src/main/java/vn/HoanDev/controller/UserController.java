package vn.HoanDev.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.HoanDev.Exception.ResourceNotFoundException;
import vn.HoanDev.dto.request.UserRequestDTO;
import vn.HoanDev.dto.response.ResponseData;
import vn.HoanDev.dto.response.ResponseError;
import vn.HoanDev.dto.response.ResponseFailure;
import vn.HoanDev.dto.response.ResponseSuccess;
import vn.HoanDev.service.UserService;

import java.util.List;

@RestController
@RequestMapping("user")
@Validated
public class UserController {

//    @PostMapping("/")
//    public ResponseData<Integer> addUser(@Valid @RequestBody UserRequestDTO user) {
//        return new ResponseData<>(HttpStatus.CREATED.value(), "added user", 1);
//    }
//
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseData<Integer> addUser(@Valid @RequestBody UserRequestDTO user){
        try{
            userService.addUser(user);
            System.out.println("thành công");
            return new ResponseData<>(HttpStatus.CREATED.value(),"added user");
        }catch (ResourceNotFoundException e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "fail add user");
        }

    }

    @PutMapping("/{userId}")
    public ResponseData<?> updateUser(@PathVariable @Min(1) int userId, @Valid @RequestBody UserRequestDTO user) {
        System.out.println("update user" + userId);
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "updated user successfully");
    }

    // update một lần thì dùng method patch
    @PatchMapping("/{userId}")
    public ResponseData<?> changeStatus(@PathVariable int userId, @RequestParam(required = false) boolean status) {
//        lưu ý: requestParam để required = false là không bắt buộc phải nhập
        System.out.println("request change status, userId=" + userId);
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "changed user successfully");
    }

    @DeleteMapping("{userId}")
    public ResponseData<?> deleteUser(@PathVariable("userId") int id) {
        System.out.println("request delete userId =" + id);
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "deleted user successfully");
    }

    @GetMapping("{userId}")
    public ResponseData<UserRequestDTO> getUser(@PathVariable int userId) {
        System.out.println("request get userId=" + userId);
        return new ResponseData<>(HttpStatus.OK.value(), "user", new UserRequestDTO("Hoan", "Java", "email", "phone"));
    }

    @GetMapping("/list")

    public ResponseData<List<UserRequestDTO>> getAllUser(@RequestParam(defaultValue = "0", required = false) int pageNo,
                                                         @Min(10) @RequestParam(defaultValue = "20", required = false) int pageSize) {
        System.out.println("Request get all of user");
        return new ResponseData<>(HttpStatus.OK.value(), "user all", List.of(new UserRequestDTO("Hoan", "Java", "email", "phone"),
                new UserRequestDTO("Ngoc", "Java", "email", "phone")));
    }
}
