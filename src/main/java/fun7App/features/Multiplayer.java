package fun7App.features;

import fun7App.entity.Users;
import fun7App.utilities.ApplicationConstants;

public class Multiplayer {
    private String multiplayerResponse;

    public Multiplayer(Users users) {
        checkMultiplayerStatus(users);
    }

    public void checkMultiplayerStatus(Users user) {
        if (user.getTimesLogged() > ApplicationConstants.MULTIPLAYER_WHEN_MORE_THAN && user.getCountryCode().toLowerCase().equals("US".toLowerCase())) {
            setMultiplayerResponse(ApplicationConstants.SERVICE_ENABLED);
        } else {
            setMultiplayerResponse(ApplicationConstants.SERVICE_DISABLED);
        }
    }

    public String getMultiplayerResponse() {
        return multiplayerResponse;
    }

    public void setMultiplayerResponse(String multiplayerResponse) {
        this.multiplayerResponse = multiplayerResponse;
    }
}
