package fun7App.resource;

import fun7App.entity.Users;
import fun7App.features.AdsService;
import fun7App.features.CustumerSupport;
import fun7App.features.Multiplayer;
import fun7App.repository.UsersRepository;
import fun7App.utilities.GlobalProperties;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

//@RequestMapping(value = "/rest/users")
@RestController
public class CheckServiceResource {
    private static Object T;
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    GlobalProperties globalProperties;

    @PostMapping(value = "/checkServices")
    public <T> T checkServices(@RequestParam("timezone") String timezone,
                               @RequestParam("userId") String userId,
                               @RequestParam("cc") String countryCode) {
        JSONObject resultJson = new JSONObject();
        ZoneId zoneId = ZoneId.of("Europe/Ljubljana");
        LocalDateTime localDateTime = LocalDateTime.now(zoneId);
        persist(timezone, userId, countryCode);
        AdsService ads = new AdsService(globalProperties.getUrl(), globalProperties.getUsername(), globalProperties.getPassword(), "US");
        if (ads.getAdServieceResponse().getStatusCodeValue() != 200) {
            return (T) ads.getAdServieceResponse();
        }

        resultJson.put("multiplayer", new Multiplayer(usersRepository.findByUserId(userId)).getMultiplayerResponse());
        resultJson.put("user-support", new CustumerSupport(localDateTime).getCustomerSupportResponse());
        resultJson.put("ads", ads.getAdServieceResponse().getBody().toString());

        return (T) ResponseEntity.ok(resultJson.toString());
    }

    public void persist(String timezone, String userId, String countryCode) {
        Users foundUser = usersRepository.findByUserId(userId);

        if (foundUser == null) {
            Users user = new Users(timezone, userId, countryCode);
            user.setTimesLogged(1);
            usersRepository.save(user);
        } else {
            foundUser.setTimesLogged(foundUser.getTimesLogged() + 1);
            usersRepository.save(foundUser);
        }
    }
}

