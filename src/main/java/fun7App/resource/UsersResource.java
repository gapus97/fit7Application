package fun7App.resource;

import fun7App.entity.Users;
import fun7App.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/users")
public class UsersResource {
    @Autowired
    UsersRepository usersRepository;

    @GetMapping(value = "all")
    public List<Users> getAll() {
        return usersRepository.findAll();
    }

    @PostMapping(value = "/login")
    public List<Users> persist(@RequestBody final Users user) {
        usersRepository.save(user);
        return usersRepository.findAll();
    }
}
