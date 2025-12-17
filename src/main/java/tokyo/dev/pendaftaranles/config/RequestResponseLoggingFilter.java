package tokyo.dev.pendaftaranles.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class RequestResponseLoggingFilter extends OncePerRequestFilter {

    private static final List<String> EXCLUDED_PATHS =
            List.of("/login", "/auth");

    private static final int REQUEST_CACHE_LIMIT = 1024 * 10; // 10 KB

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        ContentCachingRequestWrapper requestWrapper =
                new ContentCachingRequestWrapper(request, REQUEST_CACHE_LIMIT);

        ContentCachingResponseWrapper responseWrapper =
                new ContentCachingResponseWrapper(response);

        String correlationId = requestWrapper.getHeader("X-Correlation-Id");
        if (correlationId == null || correlationId.isBlank()) {
            correlationId = UUID.randomUUID().toString();
        }

        MDC.put("correlationId", correlationId);

        long startTime = System.currentTimeMillis();

        log.info("Incoming Request: {} {}",
                requestWrapper.getMethod(),
                requestWrapper.getRequestURI());

        try {
            filterChain.doFilter(requestWrapper, responseWrapper);
        } finally {
            long duration = System.currentTimeMillis() - startTime;

            boolean logBody = EXCLUDED_PATHS.stream()
                    .noneMatch(path -> requestWrapper.getRequestURI().contains(path));

            if (logBody) {
                logRequestBody(requestWrapper);
                logResponseBody(responseWrapper);
            }

            log.info("Response Status: {} | Duration: {} ms",
                    responseWrapper.getStatus(),
                    duration);

            responseWrapper.copyBodyToResponse();
            MDC.clear();
        }
    }

    private void logRequestBody(ContentCachingRequestWrapper request) {
        String body = getStringValue(request.getContentAsByteArray());
        if (!body.isBlank()) {
            log.info("Request Body: {}", minifyJson(body));
        }
    }

    private void logResponseBody(ContentCachingResponseWrapper response) {
        String body = getStringValue(response.getContentAsByteArray());
        if (!body.isBlank()) {
            log.info("Response Body: {}", minifyJson(body));
        }
    }

    private String getStringValue(byte[] bytes) {
        if (bytes == null || bytes.length == 0) return "";
        return new String(bytes, StandardCharsets.UTF_8);
    }

    private String minifyJson(String json) {
        return json
                .replaceAll("\\s+", " ")
                .replaceAll(">\\s+<", "><")
                .trim();
    }
}
