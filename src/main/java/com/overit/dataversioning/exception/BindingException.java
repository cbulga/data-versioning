package com.overit.dataversioning.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BindingException extends Exception {

    private static final long serialVersionUID = -1646083143194195402L;

    public BindingException(String message) {
        super(message);
    }
}
