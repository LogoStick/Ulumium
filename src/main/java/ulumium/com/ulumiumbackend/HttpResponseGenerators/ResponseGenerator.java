package ulumium.com.ulumiumbackend.HttpResponseGenerators;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import ulumium.com.ulumiumbackend.Models.ResponseGenearatorArgument.ResponseGeneratorArgument;

public interface ResponseGenerator {

    ResponseEntity<String> generateResponse(ResponseGeneratorArgument responseGeneratorArgument);
}
