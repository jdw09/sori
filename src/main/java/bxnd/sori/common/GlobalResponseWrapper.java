package bxnd.sori.common;

import bxnd.sori.dto.BaseRes;
import bxnd.sori.exception.ApiResponseError;
import bxnd.sori.exception.ErrorResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class GlobalResponseWrapper implements ResponseBodyAdvice<Object> {

  @Override
  public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
    return true; // 모든 컨트롤러에 적용
  }

  @Override
  public Object beforeBodyWrite(Object body, MethodParameter returnType,
      MediaType selectedContentType,
      Class<? extends HttpMessageConverter<?>> selectedConverterType,
      ServerHttpRequest request,
      ServerHttpResponse response) {
    if (body instanceof String) {
      return body;
    }
    if (body instanceof BaseRes<?>) {
      return body; // 이미 감싸진 경우 pass
    }
    if(body instanceof ErrorResponse || body instanceof ApiResponseError) {
      return new BaseRes<>(false, "에러가 발생했습니다.", body);
    }
    return new BaseRes<>(true, "성공", body);
  }
}

