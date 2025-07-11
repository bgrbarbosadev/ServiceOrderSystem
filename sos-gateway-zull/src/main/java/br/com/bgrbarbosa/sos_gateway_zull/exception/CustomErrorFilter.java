package br.com.bgrbarbosa.sos_gateway_zull.exception;

import org.springframework.stereotype.Component;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomErrorFilter extends ZuulFilter {

    private static final Logger log = LoggerFactory.getLogger(CustomErrorFilter.class);

    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return -1;
    }

    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().getThrowable() != null;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        Throwable throwable = ctx.getThrowable();

        log.error("Erro capturado pelo CustomErrorFilter: {}", throwable.getMessage(), throwable);

        Throwable cause = throwable;
        while (cause != null && cause.getCause() != null) {
            cause = cause.getCause();
        }

        HttpServletResponse response = ctx.getResponse();
        response.setContentType("application/json");

        try {
            if (cause instanceof ZuulException) {
                ZuulException zuulException = (ZuulException) cause;
                if (zuulException.getCause() instanceof com.netflix.client.ClientException &&
                        zuulException.getCause().getMessage() != null &&
                        zuulException.getCause().getMessage().contains("Load balancer does not have available server for client")) {

                    String serviceName = extractServiceNameFromClientException(zuulException.getCause());
                    response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
                    response.getWriter().write(
                            String.format("{\"timestamp\":\"%s\", \"status\":%d, \"error\":\"Service Unavailable\", \"message\":\"O serviço %s está indisponível ou não encontrado.\", \"details\":\"%s\"}",
                                    new java.util.Date(), HttpStatus.SERVICE_UNAVAILABLE.value(), serviceName, zuulException.getMessage())
                    );
                    ctx.setResponseStatusCode(HttpStatus.SERVICE_UNAVAILABLE.value());
                } else {
                    // Outras ZuulExceptions
                    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                    response.getWriter().write(
                            String.format("{\"timestamp\":\"%s\", \"status\":%d, \"error\":\"Internal Server Error\", \"message\":\"Gateway routing error.\", \"details\":\"%s\"}",
                                    new java.util.Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(), zuulException.getMessage())
                    );
                    ctx.setResponseStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                }
            } else {
                // Outras exceções não tratadas especificamente pelo ZuulException
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                response.getWriter().write(
                        String.format("{\"timestamp\":\"%s\", \"status\":%d, \"error\":\"Internal Server Error\", \"message\":\"Cannot find target host.\", \"details\":\"%s\"}",
                                new java.util.Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(), throwable.getMessage())
                );
                ctx.setResponseStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
        } catch (IOException e) {
            log.error("Erro ao escrever a resposta do erro no CustomErrorFilter", e);
        } finally {
            ctx.setSendZuulResponse(false);
            ctx.remove("throwable");
        }
        return null;
    }

    private String extractServiceNameFromClientException(Throwable ex) {
        String message = ex.getMessage();
        if (message != null && message.contains("client:")) {
            int startIndex = message.indexOf("client:") + "client:".length();
            int endIndex = message.indexOf(" ", startIndex);
            if (endIndex == -1) {
                endIndex = message.length();
            }
            return message.substring(startIndex, endIndex).trim();
        }
        return "unknown";
    }
}
