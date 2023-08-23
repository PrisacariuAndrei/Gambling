package gambling.project.microservice.wallet;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    WALLET_NOT_FOUND("WALLET_NOT_FOUND", "The wallet requested was not found", HttpStatus.NOT_FOUND),
    INSUFFICIENT_FOUNDS("INSUFFICIENT_FOUND", "The send founds requested is not valid", HttpStatus.NOT_FOUND),
    GENERAL_VALIDATION_ERROR("GENERAL_VALIDATION_ERROR", "The request is not valid", HttpStatus.BAD_REQUEST),
    WALLET_NOT_EMPTY("WALLET_NOT_EMPTY", "The request condition for delete wallet is not valid" , HttpStatus.BAD_REQUEST);

    private final String code;
    private final String message;
    private final HttpStatus status;

    ErrorCode(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
