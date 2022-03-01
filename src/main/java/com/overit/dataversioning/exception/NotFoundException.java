package com.overit.dataversioning.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NotFoundException extends Exception {

    private static final long serialVersionUID = -8729169303699924451L;

    public NotFoundException(String message) {
        super(message);
    }
}
