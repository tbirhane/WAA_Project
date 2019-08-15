package edu.mum.cs.onlinehabeshaclothing.config;


import edu.mum.cs.onlinehabeshaclothing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*//add for moment
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/**").permitAll();

        http.authorizeRequests().
                antMatchers("/h2-console/**","/admin/**","/buyer/**").permitAll()
                .and().authorizeRequests().antMatchers("/h2-console/**","/admin/**","/buyer/**").permitAll();

        http.headers().frameOptions().disable();*/
        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers(
                        "/registration**",
                        "/js/**",
                        "/css/**",
                        "/img/**","/images/**",
                        "/webjars/**","/h2-console/**"
                ,"/", "/login","/login-error", "/registration", "/h2-console/**","/productsList","/cart/selectItem"
                        ,"/cart/updateCart","/cart/cartItems",
                        "/buyer/products/detail","/cart/ordersHistory**","/cart/orderHistoryList**","/cart/cancelOrder","/"

                        ).permitAll()

                .antMatchers("/admin/products/approve","/admin/products/reject"
                ).hasAuthority("ADMIN")
                //.antMatchers("/admin/products/**").hasAuthority("ADMIN")
                .antMatchers("/seller/**").hasAuthority("SELLER")
                .antMatchers("/cart/checkout**","/follow**",
                        "/buyer/products/review","/cart/placeOrder**"
                ).hasAuthority("BUYER")

                .anyRequest().authenticated()
                .and()

                .formLogin()
                .loginPage("/login")

                .failureUrl("/login-error")


                .permitAll()

                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll();
        http.headers().frameOptions().disable();





        //End points

        /*http.authorizeRequests().antMatchers("/h2-console/**").permitAll();

        http.authorizeRequests()
                .antMatchers("/", "/login", "/registration", "/h2-console/**").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/seller/**").hasAuthority("ROLE_SELLER")
                .antMatchers("/buyer/**").hasAuthority("ROLE_BUYER")
                .anyRequest().authenticated() //all other urls can be access by any authenticated role
                .and()
                .formLogin() //enable form login instead of basic login
                .loginPage("/login")
                .failureUrl("/login-error")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and().csrf()
                .ignoringAntMatchers("/h2-console/**") //don't apply CSRF protection to /h2-console
                .and()
                .exceptionHandling().accessDeniedPage("/error/access-denied")
               // .and().rememberMe().rememberMeParameter("remember-me").tokenRepository(tokenRepository())
        ;
        http.rememberMe().rememberMeParameter("remember-me").key("uniqueAndSecret");
        http.headers().frameOptions().disable();*/

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

}