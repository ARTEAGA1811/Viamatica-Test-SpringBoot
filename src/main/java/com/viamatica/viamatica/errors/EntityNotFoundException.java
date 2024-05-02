package com.viamatica.viamatica.errors;

import com.viamatica.viamatica.utils.ErrorCatalog;
import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {
    private ErrorCatalog errorCatalog;

    public EntityNotFoundException(ErrorCatalog errorCatalog) {
        super(errorCatalog.getMessage());
        this.errorCatalog = errorCatalog;
    }
}
