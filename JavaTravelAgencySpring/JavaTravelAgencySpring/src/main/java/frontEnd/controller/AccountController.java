package frontEnd.controller;

import business.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    //schimbam userName-ul unui cont
    @PutMapping(path = "/changeUserName")
    public String changeUserName(@RequestParam String newUserName, String userName) {
        int result = accountService.changeUserName(newUserName, userName);
        if (result == 0) {
            return "There's no account with that user name.";
        } else {
            return "User name changed;";
        }
    }

    //stergem un cont
    @DeleteMapping(path = "/deleteAccount")
    public String deleteAccount(@RequestParam String userName) {
        int result = accountService.deleteAccountByUserName(userName);
        if (result == 0) {
            return "No account found with that user name.";
        } else {
            return " Account for user " + userName + " deleted.";
        }
    }


}
