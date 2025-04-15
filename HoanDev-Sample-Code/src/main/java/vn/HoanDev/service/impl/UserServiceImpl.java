package vn.HoanDev.service.impl;

import org.springframework.stereotype.Service;
import vn.HoanDev.Exception.ResourceNotFoundException;
import vn.HoanDev.dto.request.UserRequestDTO;
import vn.HoanDev.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void addUser(UserRequestDTO user) {
        if(!user.getFirstName().equals("Hoan")){
            throw new ResourceNotFoundException("Hoan khong ton tai");
        }
    }
}
