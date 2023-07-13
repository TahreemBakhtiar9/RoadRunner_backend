package roadrunner.roadrunner.UserInfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/roadrunner")
public class UserInfoCon {
    @Autowired
    private UserInfoRepo userInfoRepo;
    
    @GetMapping("userinfo/get")
        public List<UserInfo> getAllUserInfo(){
            return userInfoRepo.findAll();
        }
    
        @PostMapping("userinfo/post")
        public void addUserInfo(@RequestBody UserInfo userInfo){
            userInfoRepo.save(userInfo);
        }

    
}
