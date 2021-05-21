package br.gov.sp.fatec.saloon.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity // Habilita seguranÃ§a e configuraÃ§Ãµes padrÃµes
@EnableGlobalMethodSecurity(prePostEnabled = true) // Significa que a seguranÃ§a serÃ¡ habilitada por anotaÃ§Ã£o.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService; // Coloca a classe do SegurancaServiceImpl aqui

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Este CSRF Ã© para quando temos o prÃ³prio spring gerando as pÃ¡ginas html (back
        // e front misturados)
        // Basicamente garante que somente as pÃ¡ginas rodando dentro do spring podem
        // acessar o back-end
        // Como estamos usando o REST isto nÃ£o Ã© interessante.
        http.csrf().disable() //.httpBasic().and() isso foi tirado porque nÃ£o Ã© mais autenticaÃ§Ã£o bÃ¡sica
                // this disables session creation on Spring Secutiry
                .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // IrÃ¡ usar nova requisiÃ§Ã£o
                                                                                             // com credenciais.
    }

    /**
     * Configurando como o spring faz autenticaÃ§Ã£o (Login) Estou dizendo para o
     * spring que toda vez que for fazer login deverÃ¡ usar o serviÃ§o
     * userDetailsService Neste caso irÃ¡ buscar do BD.
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    /**
     * MÃ©todo que instancia a classe BCryptPasswordEncoder(); Indica para o Spring
     * Boot Security que nÃ£o vamos usar qualquer outro Passwordencoder. Vamos usar o
     * padrÃ£o do SB. Usando o @Bean poderemos instanciar esta classe usando o mÃ©todo
     * Autowire. Usanos isso no lugar do @Service porque esta classe nÃ£o Ã© do
     * projeto, mas do spring.
     * 
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder(); // Outros MD5, SHA
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}