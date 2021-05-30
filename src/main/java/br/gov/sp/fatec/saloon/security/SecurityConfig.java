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

@EnableWebSecurity // Habilita segurança e configuraÃçoes padrões
@EnableGlobalMethodSecurity(prePostEnabled = true) // Significa que a segurança será habilitada por anotação.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService; // Coloca a classe do SegurancaServiceImpl aqui

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Este CSRF é para quando temos o próprio spring gerando as páginas html (back
        // e front misturados)
        // Basicamente garante que somente as páginas rodando dentro do spring podem
        // acessar o back-end
        // Como estamos usando o REST isto não é interessante.
        http.csrf().disable().httpBasic().and() //isso foi tirado porque não é mais autenticação básica
                // this disables session creation on Spring Secutiry
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Irá usar nova requisição
/*                                                                                             // com credenciais.
        http.csrf().disable() //.httpBasic().and() isso foi tirado porque não é mais autenticação básica
                // this disables session creation on Spring Secutiry
                .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Irá usar nova requisição*/
    }

    /**
     * Configurando como o spring faz autenticação (Login)
     * Dizendo para o spring que toda vez que for fazer login deverá usar o serviço
     * userDetailsService Neste caso irá buscar do BD.
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    /**
     * Método que instancia a classe BCryptPasswordEncoder(); Indica para o Spring
     * Boot Security que não vamos usar qualquer outro Passwordencoder. Vamos usar o
     * padrão do SB. Usando o @Bean poderemos instanciar esta classe usando o método
     * Autowired. Usamos isso no lugar do @Service porque esta classe não é do
     * projeto, mas do spring.
     * @return
     */

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder(); // Outros MD5, SHA -- Vai usar o padrão do spring
    }

    /**
     * Este método é para permitir que o AuthenticationManager seja carregado pelo Autowired
     * dentro da classe LoginController porque o spring não o deixa disponível por padrão.
     * Este método já existe dentro do WebSecurityConfigurerAdapter desta forma mas não está
     * anotado com o @Bean
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
 
}