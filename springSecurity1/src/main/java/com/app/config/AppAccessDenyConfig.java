package com.app.config;

import cn.hutool.json.JSONUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author junius
 * @date 2023/04/29 14:04
 * @project codeHub
 * 访问拒绝处理器
 **/
@Component
public class AppAccessDenyConfig implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        HttpResult httpResult = new HttpResult(403, "access deny", null);
        String jsonStr = JSONUtil.toJsonStr(httpResult);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset='utf-8'");
        PrintWriter writer = response.getWriter();
        writer.write(jsonStr);
        writer.flush();
    }

    class HttpResult{
        private Integer code;
        private String msg;
        private Object data;

        public HttpResult(Integer code, String msg, Object data) {
            this.code = code;
            this.msg = msg;
            this.data = data;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }
}
