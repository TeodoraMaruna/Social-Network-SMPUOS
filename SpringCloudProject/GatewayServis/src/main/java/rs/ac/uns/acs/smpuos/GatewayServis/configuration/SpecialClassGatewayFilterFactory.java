package rs.ac.uns.acs.smpuos.GatewayServis.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class SpecialClassGatewayFilterFactory
        extends AbstractGatewayFilterFactory<SpecialClassGatewayFilterFactory.Config> {

    @Autowired
    private RouterValidator routerValidator;

    @Autowired
    private JwtUtil jwtUtil;

    public static class Config {

    }

    public SpecialClassGatewayFilterFactory() {
        super(Config.class);
    }

// dok mirkoservisi nisu podignuti, sve je okej, cim se mikroservisi podignu, gateway se zaobidje
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            System.out.println("halooo");
            if (routerValidator.isSecured.test(request)) {
                if (this.isAuthMissing(request)) {
                    System.out.println("zasticena putanja " + request.getPath());
                    System.out.println("zasticena putanja " + request.getURI());
                    return this.onError(exchange, "Authorization header is missing in request", HttpStatus.UNAUTHORIZED);
                }

                final String token = this.getAuthHeader(request);

                if (jwtUtil.isInvalid(token))
                    return this.onError(exchange, "Authorization header is invalid", HttpStatus.UNAUTHORIZED);
            } else {
                System.out.println("putanja nije zasticena " + request.getPath());
                System.out.println("putanja nije zasticena " + request.getURI());
            }

            return chain.filter(exchange);
        };

    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty("Authorization").get(0);
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }
}
