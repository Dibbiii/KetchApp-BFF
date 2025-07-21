package alessandra_alessandro.ketchapp_bff.annotations;

import java.lang.annotation.*;

/**
 * Annotation to inject the currently authenticated user into a controller method parameter.
 * Typically used in Spring-based applications to access user details from the security context.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {}
