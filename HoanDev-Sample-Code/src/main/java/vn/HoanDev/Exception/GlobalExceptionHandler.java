package vn.HoanDev.Exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;

@RestControllerAdvice // để bắt được lỗi và trả về
public class GlobalExceptionHandler {

    // lưu ý: phải biết nó bắt lỗi exception nào mới chèn không thì nó không hiện ở console
    @ExceptionHandler({ConstraintViolationException.class, MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse HandlerValidationException(Exception e, WebRequest request){
        ErrorResponse error = new ErrorResponse();
        String messsage = e.getMessage();
        System.out.println(messsage);
        error.setTimestamp(new Date());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setPath(request.getDescription(false).replace("uri=",""));

        if(e instanceof ConstraintViolationException){
            error.setError("PathVariable invalid");
            System.out.println(messsage);
            error.setMessage(messsage.substring(messsage.indexOf(" ")+1));
        } else if (e instanceof MethodArgumentNotValidException) {
            int start = messsage.lastIndexOf("[");
            int end = messsage.lastIndexOf("]");
            error.setMessage(messsage.substring(start+1,end -1));
            error.setError("Payload invalid");
        }
        return error;
    }


    // lưu ý: phải biết nó bắt lỗi exception nào mới chèn không thì nó không hiện ở console
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse HandlerInternalException(Exception e, WebRequest request){
        ErrorResponse error = new ErrorResponse();
        String messsage = e.getMessage();
        System.out.println(messsage);
        error.setTimestamp(new Date());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setPath(request.getDescription(false).replace("uri=",""));
        error.setError("Parameter invalid");
        System.out.println(messsage);
        error.setMessage("Failed to convert value of type");
        return error;
    }
}
