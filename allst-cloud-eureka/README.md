# spring-boot-starter-security
在Spring Boot 2.3.x版本及更高版本中，`spring.security.basic.enabled` 配置项确实不再直接使用。Spring Security从5.3版本开始，
默认禁用了HTTP Basic认证（Basic Authentication）。如果要启用基本认证，应通过配置HttpSecurity来进行。

以下是如何在Spring Boot 2.3.1及以上版本启用基本认证的示例：
```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 启用HTTP基本认证
        http.httpBasic();
        
        // 其他安全配置...
    }
}
```

在以上配置中，重写了`configure(HttpSecurity)`方法，并调用了`httpBasic()`来开启基本认证功能。当然，具体的安全配置需要根据你的应用需求进行调整。

