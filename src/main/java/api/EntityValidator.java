package api;

import java.util.List;

public interface EntityValidator {
    List<Exception> validate(Object entity);
}
