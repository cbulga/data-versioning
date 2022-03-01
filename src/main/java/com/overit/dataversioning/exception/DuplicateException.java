package com.overit.dataversioning.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DuplicateException extends Exception {

    private static final long serialVersionUID = 6671877602598802506L;

    public DuplicateException(String message) {
        super(message);
    }
}
