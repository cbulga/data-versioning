package com.overit.dataversioning.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.overit.dataversioning.exception.BindingException;
import com.overit.dataversioning.exception.DuplicateException;
import com.overit.dataversioning.exception.NotFoundException;
import com.overit.dataversioning.model.AUser;
import com.overit.dataversioning.service.AUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class AUserController {

    protected static final String USER_UPDATED_SUCCESSFULLY = "User %s updated successfully!";
    protected static final String USER_INSERTED_SUCCESSFULLY = "User %s inserted successfully!";
    protected static final String USER_DELETED_SUCCESSFULLY = "User %s deleted successfully!";
    private final AUserService aUserService;
    private final ResourceBundleMessageSource resourceBundleMessageSource;
    private ObjectMapper objectMapper;

    public AUserController(AUserService aUserService, ResourceBundleMessageSource resourceBundleMessageSource) {
        this.aUserService = aUserService;
        this.resourceBundleMessageSource = resourceBundleMessageSource;
        this.objectMapper = new ObjectMapper();
    }

    @GetMapping(value = "/search/username/{username}", produces = "application/json")
    public ResponseEntity<AUser> getAUserByUsername(@PathVariable("username") String username) throws NotFoundException {
        log.info("****** Searching a aUser by its username {} ******", username);
        AUser result = aUserService.findByUsername(username);
        log.debug("returning {}", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(value = "/update", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> updateAUser(@Valid @RequestBody AUser aUser, BindingResult bindingResult) throws NotFoundException, BindingException {
        log.info("****** Updating the user identified by {} ID ******", aUser.getId());

        if (bindingResult.hasErrors()) {
            String msgErr = resourceBundleMessageSource.getMessage(Objects.requireNonNull(bindingResult.getFieldError()), LocaleContextHolder.getLocale());
            log.warn(msgErr);
            throw new BindingException(msgErr);
        }

        AUser updatedAUser = aUserService.updateById(aUser.getId(), aUser);

        ObjectNode result = objectMapper.createObjectNode();
        result.put("code", HttpStatus.OK.toString());
        result.put("message", String.format(USER_UPDATED_SUCCESSFULLY, updatedAUser.getId()));

        log.debug("returning {}", result);
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }

    @PostMapping(value = "/insert", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> insertAUser(@Valid @RequestBody AUser aUser, BindingResult bindingResult) throws DuplicateException, BindingException {
        log.info("****** Inserting the user identified by {} ID ******", aUser.getId());

        if (bindingResult.hasErrors()) {
            String msgErr = resourceBundleMessageSource.getMessage(Objects.requireNonNull(bindingResult.getFieldError()), LocaleContextHolder.getLocale());
            log.warn(msgErr);
            throw new BindingException(msgErr);
        }

        AUser insertedAUser = aUserService.insert(aUser);

        ObjectNode result = objectMapper.createObjectNode();
        result.put("code", HttpStatus.OK.toString());
        result.put("message", String.format(USER_INSERTED_SUCCESSFULLY, insertedAUser.getId()));

        log.debug("returning {}", result);
        return new ResponseEntity<>(result, null, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> deleteAUser(@RequestBody AUser aUser) throws NotFoundException, BindingException {
        log.info("****** Deleting the user {} ******", aUser);

        aUserService.delete(aUser);

        ObjectNode result = objectMapper.createObjectNode();
        result.put("code", HttpStatus.OK.toString());
        result.put("message", String.format(USER_DELETED_SUCCESSFULLY, aUser.getId()));

        log.debug("returning {}", result);
        return new ResponseEntity<>(result, null, HttpStatus.CREATED);
    }
}
