
package dev.ceos.caloringmvp.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;


@Slf4j
public class AuthLoginInterceptor extends HandlerInterceptorAdapter {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    log.info("MyInterceptor - preHandler");

    // header에 authorization 데이터가 있는지
    String token = null;
    String authorization = request.getHeader("Authorization");
    if(authorization == null || "".equals(authorization)) {
      return false;
    }
    token = authorization.substring(7, authorization.length());
    DecodedJWT jwt = null;
    try {
      jwt = JWT.decode(token);
    } catch (JWTDecodeException e) {
      throw new JwtException("토큰을 디코딩하는데 오류가 발생했습니다");
    }

    String user = null;
    Claim userClaim = jwt.getClaims().get("userId");
    if(userClaim != null) {
      user = userClaim.asString();
    }
    if(user == null || "".equals(user)) {
      throw new JwtException("토큰에 아이디가 없습니다");
    }

    // 날짜 검사
    Date expiresAt = jwt.getExpiresAt();
    Date now = new Date();
    if(now.after(expiresAt)) {
      throw new JwtException("토큰의 유효기간이 지났습니다. 재로그인 해주세요 ");
    }
    return true;
  }
}
