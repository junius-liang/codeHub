# 学习curl命令

# 用户认证
用户认证是判断一个用户身份是否合法的过程

# 会话
会话是保持当前用户登陆状态所提供的机制

# 用户授权
授权是通过用户认证后，根据用户权限来访问系统资源的过程

# RBAC：基于角色的访问控制
将权限打包给角色，将角色分配给用户

五张表：角色表，权限表，用户表，权限角色表，用户角色表

# springboot security
添加依赖
```xml
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
```

使用curl访问：curl -u"用户名:密码" 地址 

curl -u"user:c54818a5-8435-4832-85fd-5f51110f9c4b" 127.0.0.1:8080/student

# 使用配置文件配置用户名与密码
```yaml
spring:
  security:
    user:
      name: admin
      password: admin
```

# 基于内存的用户管理
```java
@Configuration
public class UserConfig {
    @Bean
   public UserDetailsService userDetailsService(){
        UserDetails userDetails1 = User.builder().username("user001").password("user001").build();
        UserDetails userDetails2 = User.builder().username("user002").password("user002").build();
        UserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(userDetails1);
        userDetailsManager.createUser(userDetails2);
        return userDetailsManager;
    }
}
```
>按照以上的写法，会出现报错。因为Spring Security不允许明文密码。需要配置一个加密器，下面是完整写法：
> 

```java
@Configuration
public class UserConfig {
    @Bean
   public UserDetailsService userDetailsService(){
        UserDetails userDetails1 = User.builder().username("user001").password("user001").roles("user001").build();
        UserDetails userDetails2 = User.builder().username("user002").password("user002").roles("user002").build();
        UserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(userDetails1);
        userDetailsManager.createUser(userDetails2);
        return userDetailsManager;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
```
# 密码学
在密码加密中，Spring Security推荐使用PasswordEncoder的实现类：
```java
public class Demo1 {
    @Test
    public void method1(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode1 = passwordEncoder.encode("123456");
        String encode2 = passwordEncoder.encode("123456");
        String encode3 = passwordEncoder.encode("123456");
        System.out.println(encode1);
        System.out.println(encode2);
        System.out.println(encode3);
        boolean matches1 = passwordEncoder.matches("123456", encode1);
        boolean matches2 = passwordEncoder.matches("123456", encode2);
        boolean matches3 = passwordEncoder.matches("123456", encode3);
        System.out.println(matches1+"::"+matches2+"::"+matches3);
        /*
        $2a$10$zKsV6yuJJ/s5KrlaUS63ieLt3EUINOV2AUmug/foHjEpMfST6hLou
        $2a$10$HXznQQebNko6XLzni67DUONedVGKk7dyhVnyXdtONj4HCmWyX.bLi
        $2a$10$9XQ.LMvHiAYX6G1w1vy4S.SCN3RQPjm/XrgjBDCEec.jIPc5meuPW
        true::true::true
         */
    }
}
```
使用加密密码
```java
@Configuration
public class UserConfig {
    @Bean
   public UserDetailsService userDetailsService(){
        UserDetails userDetails1 = User.builder().username("user001").password(passwordEncoder().encode("user001")).roles("user001").build();
        UserDetails userDetails2 = User.builder().username("user002").password(passwordEncoder().encode("user002")).roles("user002").build();
        UserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(userDetails1);
        userDetailsManager.createUser(userDetails2);
        return userDetailsManager;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        //无密码加密
//        return NoOpPasswordEncoder.getInstance();
        //加密
        return new BCryptPasswordEncoder();
    }
}
```


# 获取登录用户信息
```java
@RestController
public class CurLoginUserController {
    @GetMapping("/getlog1")
    public Authentication getAuthentication1(Authentication authentication) {
        return authentication;
    }

    @GetMapping("/getlog2")
    public Principal getAuthentication2(Principal principal) {
        return principal;
    }

    @GetMapping("/getlog3")
    public Authentication getAuthentication3() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
```
```json
{
"authorities": [
{
"authority": "ROLE_user002"
}
],
"details": {
"remoteAddress": "0:0:0:0:0:0:0:1",
"sessionId": "23F85DD10B9E460BE09204E89F79A5CB"
},
"authenticated": true,
"principal": {
"password": null,
"username": "user002",
"authorities": [
{
"authority": "ROLE_user002"
}
],
"accountNonExpired": true,
"accountNonLocked": true,
"credentialsNonExpired": true,
"enabled": true
},
"credentials": null,
"name": "user002"
}
```
# 授权
```java
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //给路径配置权限，上下方法等同
                .mvcMatchers("/student/**")
                .hasAuthority("ROLE_student")
                .mvcMatchers("/teacher/**")
                .hasRole("teacher")
                .mvcMatchers("/admin/**")
                .hasAnyAuthority("ROLE_teacher","ROLE_student")
                .anyRequest()
                .authenticated();
        http.formLogin().permitAll();

    }
}

```
# 针对方法进行授权
```
//第一步
  UserDetails userDetails2 = User.builder().username("teacher002")
                .password(passwordEncoder().encode("teacher002")).roles("teacher")
                .authorities() //给用户赋予权限
                .build();
                
//第二步
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .authenticated();
        http.formLogin().permitAll();

    }
}

//使用预授权注解控制
@PreAuthorize("hasAnyAuthority('ROLE_teacher')")
```

# 返回Json
## 认证成功处理器
```java
@Component
public class AppAuthenticationSuccessConfig implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpResult httpResult = new HttpResult(200,"success",null);
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
```
## 在WebSecurityConfigurerAdapter实现类中运用
```java
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private AppAuthenticationSuccessConfig appAuthenticationSuccessConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //此处运用
        http.formLogin().successHandler(appAuthenticationSuccessConfig).permitAll();

    }
}
```

## 认证失败处理器
```java
@Component
public class AppAuthenticationFailConfig implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        HttpResult httpResult = new HttpResult(444, "authentication fail", null);
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

```
```java
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private AppAuthenticationSuccessConfig appAuthenticationSuccessConfig;
    @Resource
    private AppAuthenticationFailConfig appAuthenticationFailureConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //给路径配置权限，上下方法等同
                .mvcMatchers("/student/**")
                .hasAuthority("ROLE_student")
                .mvcMatchers("/teacher/**")
                .hasRole("teacher")
                .mvcMatchers("/admin/**")
                .hasAnyAuthority("ROLE_teacher","ROLE_student")
                .anyRequest()
                .authenticated();
        http.formLogin().successHandler(appAuthenticationSuccessConfig)
                .failureHandler(appAuthenticationFailureConfig)
                .permitAll();

    }
}
```
