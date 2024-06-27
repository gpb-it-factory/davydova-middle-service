package ru.gpbf.middle.web.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.gpbf.middle.entity.ErrorEntity;
import ru.gpbf.middle.exception.ABSServerException;
import ru.gpbf.middle.exception.ConflictServerException;

public class ErrorEntityHandler {
    private static final Logger log = LoggerFactory.getLogger(ErrorEntityHandler.class);
    static void handle(ResponseEntity<ErrorEntity> errorEntity) {

        if (errorEntity.hasBody() && errorEntity.getBody() != null) {
            log.error(errorEntity.getBody().toString());
            if (errorEntity.getStatusCode() == HttpStatus.CONFLICT) {
                throw new ConflictServerException(errorEntity.getBody().getMessage(), errorEntity.getBody().getTraceId());
            }
            else  {

                throw new ABSServerException(errorEntity.getBody().getMessage(), errorEntity.getBody().getTraceId(), errorEntity.getBody().getCode());
            }
        }

        else if (!errorEntity.hasBody() && errorEntity.getStatusCode() != HttpStatus.NO_CONTENT) {
            throw new ABSServerException("Abs server exception");
        }
    }
}
