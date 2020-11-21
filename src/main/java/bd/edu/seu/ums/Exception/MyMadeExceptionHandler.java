package bd.edu.seu.ums.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyMadeExceptionHandler {

    @ExceptionHandler(MyMadeException.class)
    public ResponseEntity<Object> handleDataDuplicationException(MyMadeException e){
       HttpStatus ServerError = HttpStatus.INTERNAL_SERVER_ERROR;
        Message Message = new Message(
                e.getMessage(),
                ServerError
        );
        return new ResponseEntity<>(Message,ServerError);
    }
}
