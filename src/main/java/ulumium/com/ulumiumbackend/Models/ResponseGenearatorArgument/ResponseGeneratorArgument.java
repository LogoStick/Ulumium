package ulumium.com.ulumiumbackend.Models.ResponseGenearatorArgument;

import org.springframework.http.HttpHeaders;
import ulumium.com.ulumiumbackend.Models.User.User;

public class ResponseGeneratorArgument {

    private boolean operationSuccessful;
    private HttpHeaders extraHttpHeaders;

    //this field is only used by UserResponseGenerator
    private User user;

    public boolean isOperationSuccessful() {
        return operationSuccessful;
    }

    public ResponseGeneratorArgument setSuccessfulOperation(boolean operationStatus) {
        this.operationSuccessful = operationStatus;
        return this;
    }

    public HttpHeaders getExtraHttpHeaders() {
        return extraHttpHeaders;
    }

    public ResponseGeneratorArgument setExtraHttpHeaders(HttpHeaders extraHttpHeaders) {
        this.extraHttpHeaders = extraHttpHeaders;
        return this;
    }

    public User getUser() {
        return user;
    }

    public ResponseGeneratorArgument setUser(User user) {
        this.user = user;
        return this;
    }
}
