package dev.feryadi.backend.bayu.interceptor;

import dev.feryadi.backend.bayu.auth.ApplicationUserDetails;
import dev.feryadi.backend.bayu.exception.ForbiddenAccessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;

@Slf4j
@Component
public class UserRouteInterceptor implements WebRequestInterceptor {
    @Override
    public void preHandle(WebRequest webRequest) throws Exception {

        log.info(webRequest.toString());
        Map<String, String> attribute = (Map<String, String>) webRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, 0);

        log.info(attribute.toString());

        String userId = attribute.get("userId");
        if (userId != null) {
            ApplicationUserDetails userDetails =(ApplicationUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            log.info("userDetails.id=" + userDetails.getId());
            log.info("userId=" + userId);

            if (userDetails.getId() != Long.parseLong(userId)) {
                throw new ForbiddenAccessException("User doesn't have permission to access this route");
            }
        }

    }

    @Override
    public void postHandle(WebRequest webRequest, ModelMap modelMap) throws Exception {

    }

    @Override
    public void afterCompletion(WebRequest webRequest, Exception e) throws Exception {

    }
}
