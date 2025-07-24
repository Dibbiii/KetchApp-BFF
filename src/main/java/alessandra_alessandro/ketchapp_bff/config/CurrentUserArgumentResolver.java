package alessandra_alessandro.ketchapp_bff.config;

import alessandra_alessandro.ketchapp_bff.annotations.CurrentUser;
import alessandra_alessandro.ketchapp_bff.models.responses.UserResponse;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class CurrentUserArgumentResolver
    implements HandlerMethodArgumentResolver {

    /**
     * Determines if the parameter is eligible for resolution by this resolver.
     * Only parameters annotated with @CurrentUser and of type UserResponse are supported.
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return (
            parameter.hasParameterAnnotation(CurrentUser.class) &&
            parameter.getParameterType().equals(UserResponse.class)
        );
    }

    /**
     * Resolves the argument by extracting the current user's username from the security context.
     * Returns a UserResponse containing the username, or null if not authenticated.
     */
    @Override
    public Object resolveArgument(
        MethodParameter parameter,
        ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest,
        WebDataBinderFactory binderFactory
    ) {
        Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();
        if (
            authentication != null &&
            authentication.getPrincipal() instanceof String username
        ) {
            return new UserResponse(null, username, null);
        }
        return null;
    }
}
