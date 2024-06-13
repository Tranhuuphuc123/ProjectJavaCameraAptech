package com.camera.controllers.advices;

import com.camera.payload.responses.InvalidResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


//exception xử lý tình huống khi có lỗi sẽ quăng lỗi vào các exception này để xử lý chung tình hình lỗi
// throw, try_catch
// Controler throw Ex -> Controller Advice xử lí Ex
@ControllerAdvice(annotations = RestController.class)
public class ExeptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<InvalidResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            errors.add(error.getDefaultMessage());
        }
        InvalidResponse res = new InvalidResponse();
        res.setSuccess(false);
        res.setInvalid(true);
        res.setCode(400);
        res.setMessage("Request body is invalid data");
        res.setErrors(errors);
        return new ResponseEntity<InvalidResponse>(res, HttpStatus.BAD_REQUEST);
    }
}
