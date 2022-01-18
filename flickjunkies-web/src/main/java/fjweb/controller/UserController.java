package fjweb.controller;


import fjdata.model.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import fjservice.service.UserService;

// @CrossOrigin
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController
{
    private final UserService userService;

    @GetMapping
    public User getUserFromToken()
    {
        return userService.getUserFromToken();
    }

    @PostMapping("/new")
    public String addUser(@RequestBody User payload)
    {
        return userService.addUser(payload);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id)
    {
        return userService.deleteUser(id);
    }
}
