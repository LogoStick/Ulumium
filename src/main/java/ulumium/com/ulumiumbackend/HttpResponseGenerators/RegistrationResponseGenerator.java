package ulumium.com.ulumiumbackend.HttpResponseGenerators;

import com.google.common.collect.Lists;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import ulumium.com.ulumiumbackend.Models.ResponseGenearatorArgument.ResponseGeneratorArgument;

public class RegistrationResponseGenerator implements ResponseGenerator {

    @Override
    public ResponseEntity<String> generateResponse(ResponseGeneratorArgument arguments) {
        boolean successfulOperation = arguments.isOperationSuccessful();
        HttpHeaders extraHttpHeaders = arguments.getExtraHttpHeaders();
        if(successfulOperation) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setContentType(MediaType.APPLICATION_JSON);
            if(extraHttpHeaders != null) {
                responseHeaders.addAll(extraHttpHeaders);
            }
            return ResponseEntity
                    .ok()
                    .headers(responseHeaders)
                    .body(new JSONObject().put("SuccessfulRegistration", true).toString());
        } else {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setContentType(MediaType.APPLICATION_JSON);
            return ResponseEntity
                    .ok()
                    .headers(responseHeaders)
                    .body(new JSONObject().put("SuccessfulRegistration", false).toString());
        }
    }
}
