package com.gameproject.flash.config;

import com.gameproject.flash.config.data.UserPrincipal;
import com.gameproject.flash.domian.Member;
import com.gameproject.flash.repository.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity//()    // Todo 배포 전에 False로 바꾸기
public class SecurityConfig {

    @Bean   //
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> web.ignoring()
                .requestMatchers("/error", "/favicon.ico") // "/css/**" ,
                .requestMatchers("/docs/index.html");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and()
                .cors()
                .and()
                .authorizeHttpRequests()
                    .requestMatchers("/auth/signup").permitAll() // 누구나 접근 가능
                    .requestMatchers("/auth/login").permitAll() // 누구나 접근 가능
                    .requestMatchers("/").permitAll()
                    .requestMatchers("/game/**").permitAll()

                .anyRequest().authenticated()

                .and()
                .formLogin()
                    .loginPage("/auth/login")
                    .loginProcessingUrl("/auth/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/")
                .and()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> {
                    response.sendRedirect("/auth/login"); // 인증되지 않은 사용자에게 접근이 거부될 때 로그인 페이지로 리다이렉션
                })
                .and()
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(MemberRepository memberRepository) {
        return username -> {
            Member member = memberRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException(username + "을 찾을 수 없습니다."));

            return new UserPrincipal(member);
        };
    }

    // CORS 설정을 위한 빈을 추가합니다.
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "https://5laksil.netlify.app/")); // 허용되는 도메인 목록 설정
        configuration.setAllowedMethods(Arrays.asList("*")); // 허용되는 HTTP 메서드 목록 설정
        configuration.setAllowCredentials(true); // 쿠키 등을 허용
        configuration.setAllowedHeaders(Arrays.asList("*")); // 허용되는 헤더 목록 설정
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return  new SCryptPasswordEncoder(
                16,
                8,
                1,
                32,
                64);
    }

    @Bean
    public DefaultCookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setSameSite("None"); // SameSite 설정을 None으로 변경
        serializer.setUseSecureCookie(true); // Secure 설정 활성화
        return serializer;
    }
}
