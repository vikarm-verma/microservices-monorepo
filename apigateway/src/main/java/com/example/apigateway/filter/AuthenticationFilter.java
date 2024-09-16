package com.example.apigateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.apigateway.util.JwtUtil;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	@Autowired
	private RouteValidator validator;

	// @Autowired
//    private RestTemplate template;
	@Autowired
	private JwtUtil jwtUtil;

	ServerHttpRequest request=null;

	public AuthenticationFilter() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {
			if (validator.isSecured.test(exchange.getRequest())) {
				// header contains token or not
				if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new RuntimeException("missing authorization header");
				}

				String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				if (authHeader != null && authHeader.startsWith("Bearer ")) {
					authHeader = authHeader.substring(7);
					System.out.println("authHeader "+authHeader);
				}
				try {
					System.out.println("in try before token validation");
//                    //REST call to AUTH service
//                    template.getForObject("http://IDENTITY-SERVICE//validate?token" + authHeader, String.class);
					jwtUtil.validateToken(authHeader);
					
					System.out.println(" in try after token validation");

					request = exchange.getRequest().mutate().header("loggedInUser", jwtUtil.extractUsername(authHeader))
							.build();

				} catch (Exception e) {
					System.out.println("invalid access...!");
					throw new RuntimeException("un authorized access to application");
				}
			}
			return chain.filter(exchange.mutate().request(request).build());
		});
	}

	public static class Config {

	}
}
