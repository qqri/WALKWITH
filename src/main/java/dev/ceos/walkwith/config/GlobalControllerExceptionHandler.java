package dev.ceos.walkwith.config;

import dev.ceos.caloringmvp.test.ResponseVO;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@Slf4j
@ControllerAdvice
public class GlobalControllerExceptionHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(JwtException.class)
  @ResponseBody
  public ErrorInfo handleBadRequest(HttpServletRequest request, Exception ex) {
    return new ErrorInfo(request.getRequestURL().toString(), ex);
  }
}
