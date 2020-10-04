package ulumium.com.ulumiumbackend.HttpResponseGenerators;

import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import ulumium.com.ulumiumbackend.Models.ResponseGenearatorArgument.ResponseGeneratorArgument;
import ulumium.com.ulumiumbackend.Models.User.User;
import ulumium.com.ulumiumbackend.Models.User.UserData;

public class UserDataResponseGenerator implements ResponseGenerator {
    public ResponseEntity<String> generateResponse(ResponseGeneratorArgument arguments) {
        boolean successfulOperation = arguments.isOperationSuccessful();
        HttpHeaders extraHttpHeaders = arguments.getExtraHttpHeaders();
        User user = arguments.getUser();

        HttpHeaders responseHeaders = new HttpHeaders();

        if(extraHttpHeaders != null) {
            responseHeaders.addAll(extraHttpHeaders);
        }
        if(successfulOperation == false) {
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .headers(responseHeaders)
                    .body(
                            new JSONObject()
                                    .put("DataFetchedSuccessfully", false)
                                    .put("UserData", "")
                                    .toString()
                    );
        } else {
            return ResponseEntity
                    .ok()
                    .headers(responseHeaders)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(
                        new JSONObject()
                            .put("DataFetchedSuccessfully", true)
                            .put("UserData", new JSONObject(UserData.of(user)))
                            .toString()
                    );
        }
    }
}
