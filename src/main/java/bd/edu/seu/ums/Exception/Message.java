package bd.edu.seu.ums.Exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class Message {
    private final String message ;
    private final HttpStatus httpStatus;

    public Message(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
