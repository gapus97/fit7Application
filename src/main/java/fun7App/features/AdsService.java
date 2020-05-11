package fun7App.features;

import fun7App.utilities.ApplicationConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class AdsService {
    private ResponseEntity adServiceResponse;


    public AdsService(String url, String username, String password, String countryCode) {
        checkAreAdsEnableWithExternalApi(url, username, password, countryCode);
    }

    public void checkAreAdsEnableWithExternalApi(String externalApiurl, String username, String password, String countryCode) {
        try {
            URL url = new URL(externalApiurl + "?countryCode=" + countryCode);
            String authString = username + ":" + password;
            String encoding = Base64.getEncoder().encodeToString(authString.getBytes());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Basic " + encoding);
            int responseCode = connection.getResponseCode();
            InputStream inputStream;
            if (200 <= responseCode && responseCode <= 299) {
                inputStream = connection.getInputStream();
            } else {
                inputStream = connection.getErrorStream();
            }
            String apiCallMessage = readFromInputStrem(inputStream);
            checkResponseCode(responseCode, apiCallMessage);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public ResponseEntity getAdServieceResponse() {
        return adServiceResponse;
    }

    public void setAdServiceResponse(ResponseEntity adServiceResponse) {
        this.adServiceResponse = adServiceResponse;
    }

    public void checkResponseCode(int responseCode, String apiCallMessage) {
        switch (responseCode) {
            case 200:
                boolean addsEnabled = apiCallMessage.contains(ApplicationConstants.ADS_ENABLED_SUBSTRING);
                setAdServiceResponse(addsEnabled ? ResponseEntity.ok(ApplicationConstants.SERVICE_ENABLED) : ResponseEntity.ok(ApplicationConstants.SERVICE_DISABLED));
                break;
            case 400:
                setAdServiceResponse(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApplicationConstants.BAD_REQUEST_MESSAGE));
                break;
            case 401:
                setAdServiceResponse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApplicationConstants.UNAUTHORIZED_MESSAGE));
                break;
            case 500:
                setAdServiceResponse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApplicationConstants.INTERNAL_SERVER_ERROR_MESSAGE));
                break;
            default:
                break;
        }
    }

    public String readFromInputStrem(InputStream inputStream) throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        inputStream));

        StringBuilder response = new StringBuilder();
        String currentLine;

        while ((currentLine = in.readLine()) != null)
            response.append(currentLine);
        return response.toString();
    }
}
