package com.sazonov.springboot.task.springboot.controller;

import com.sazonov.springboot.task.springboot.model.Role;
import com.sazonov.springboot.task.springboot.model.User;
import com.sazonov.springboot.task.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/admin/roles")
    public List<Role> loadAllRoles() {
        return userService.allRoles();
    }
    @GetMapping("/admin/users")
    public List<User> loadToUsersPage() {
        return userService.getAllUsers();
    }

    @GetMapping("/admin/users/{id}")
    public User getUser(@PathVariable("id")  int id){
        return userService.getUserById(id);
    }
    @GetMapping("/user")
    public User getAuthorizedUser(){
        return  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ;
    }
    @PostMapping("/admin/users")
    public ResponseEntity<User> addNewUser(@RequestBody User user){
        userService.create(user);
        return ResponseEntity.ok(user);
    }
    @PutMapping("/admin/users")
    public ResponseEntity<User> editUser(@RequestBody User user){
        userService.update(user);
        return ResponseEntity.ok(user);
    }
    @DeleteMapping("/admin/users/{id}")
    public void deleteUser(@PathVariable("id") int id){
        userService.delete(id);
    }



//    @PostMapping("/admin/new")
//    public  String create (@ModelAttribute("user") User user,
//                           @RequestParam(value = "addRole", required = false) String userRole, Model model){
//
//        Set<Role> roleSet = new HashSet<>();
//        if(userRole == null){
//            user.setRoles(Collections.singleton(new Role(2L,"ROLE_USER")));
//            userService.create(user);
//            return "redirect:/admin";
//        }
//        if (userRole.contains("ROLE_ADMIN")){
//            roleSet.add(new Role(1L, "ADMIN"));
//            user.setRoles(roleSet);
//        }
//        if (userRole.contains("ROLE_USER")) {
//            roleSet.add(new Role(2L, "USER"));
//            user.setRoles(roleSet);
//        }
//        userService.create(user);
//
//        return "redirect:/admin";
//    }
//
//    @PutMapping("/admin/edit")
//    public  String update( @ModelAttribute("user") User userEdit,
//                           @RequestParam(value = "addRoles", required = false) String userRole,
//                           Model model){
//
//
//        Set<Role> roleSet = new HashSet<>();
//        if(userRole == null){
//            userEdit.setRoles(Collections.singleton(new Role(2L,"ROLE_USER")));
//            userService.create(userEdit);
//            return "redirect:/admin";
//        }
//        if (userRole.contains("ROLE_ADMIN")){
//            roleSet.add(new Role(1L, "ADMIN"));
//            userEdit.setRoles(roleSet);
//        }
//        if (userRole.contains("ROLE_USER")) {
//            roleSet.add(new Role(2L, "USER"));
//            userEdit.setRoles(roleSet);
//        }
//
//        userService.update(userEdit);
//        return "redirect:/admin";
//    }
//
//
//    @PostMapping("/admin/delete/{id}")
//    public String delete(@PathVariable("id") int id){
//        userService.delete(id);
//        return "redirect:/admin";
//    }
//
//    @GetMapping("/logout")
//    public String logout() {
//        return "login";
//    }
}