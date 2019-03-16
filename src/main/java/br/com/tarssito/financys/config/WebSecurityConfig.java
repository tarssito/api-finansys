package br.com.tarssito.financys.config;

import br.com.tarssito.financys.jwt.JWTAuthenticationFilter;
import br.com.tarssito.financys.jwt.JWTLoginFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] PUBLIC_MATCHERS = { "/h2-console/**" };
    private static final String[] PUBLIC_MATCHERS_POST = { "/auth" };
    private static final String[] PUBLIC_MATCHERS_GET = { "/users/**" };

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Habilitando acesso ao h2-console
        http.headers().frameOptions().disable();

        http.csrf().disable().authorizeRequests()
            .antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
            .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
            .antMatchers(PUBLIC_MATCHERS).permitAll()
            .anyRequest().authenticated()
            .and()

            // filtra requisições de login
            .addFilterBefore(new JWTLoginFilter("/auth", authenticationManager()),
                    UsernamePasswordAuthenticationFilter.class)

            // filtra outras requisições para verificar a presença do JWT no header
            .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        /*
         * Utilizado para assegurar que o back end não vai criar sessão de usuário
         */
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // cria uma conta default
        auth.inMemoryAuthentication().withUser("admin").password("{noop}password").roles("ADMIN");
    }
}
