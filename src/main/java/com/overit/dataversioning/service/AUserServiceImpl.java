package com.overit.dataversioning.service;

import com.overit.dataversioning.exception.BindingException;
import com.overit.dataversioning.exception.DuplicateException;
import com.overit.dataversioning.exception.NotFoundException;
import com.overit.dataversioning.model.AUser;
import com.overit.dataversioning.repository.AUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class AUserServiceImpl implements AUserService {

    protected static final String IMPOSSIBLE_TO_USE_THE_USERNAME_VALUE = "Duplicated.AUser.Username.Validation";
    protected static final String ID_MUST_BE_NULL = "Null.AUser.Id.Validation";
    protected static final String AUSER_NOT_FOUND_BY_ID = "NotFound.AUser.Id";
    public static final String NOT_FOUND_AUSER_USERNAME = "NotFound.AUser.Username";
    private final AUserRepository aUserRepository;
    private final ResourceBundleMessageSource resourceBundleMessageSource;

    public AUserServiceImpl(AUserRepository aUserRepository, ResourceBundleMessageSource resourceBundleMessageSource) {
        this.aUserRepository = aUserRepository;
        this.resourceBundleMessageSource = resourceBundleMessageSource;
    }

    @Override
    public AUser insert(AUser aUser) throws DuplicateException, BindingException {
        log.debug("insert(aUser={})", aUser);
        if (aUser.getId() != null)
            throw new BindingException(resourceBundleMessageSource.getMessage(ID_MUST_BE_NULL, null, LocaleContextHolder.getLocale()));
        log.debug("looking for another user with the same username {}", aUser.getUsername());
        Optional<AUser> duplicated = aUserRepository.findByUsername(aUser.getUsername());
        if (duplicated.isPresent()) {
            log.error("Another user ({}) has been found having the same username {}. The user insertion cannot be completed",
                    duplicated.get().getId(), aUser.getUsername());
            throw new DuplicateException(
                    resourceBundleMessageSource.getMessage(IMPOSSIBLE_TO_USE_THE_USERNAME_VALUE, new String[]{aUser.getUsername()}, LocaleContextHolder.getLocale()));
        }
        aUser.setDatastamp(new Date());
        AUser result = aUserRepository.save(aUser);
        log.debug("returning {}", result);
        return result;
    }

    @Override
    public AUser updateById(Long id, AUser aUser) throws NotFoundException, BindingException {
        log.debug("updateById(id={}, aUser={})", id, aUser);
        if (aUser.getId() == null)
            throw new BindingException(resourceBundleMessageSource.getMessage("NotNull.AUser.Id.Validation", null, LocaleContextHolder.getLocale()));
        AUser aUserToUpdate = aUserRepository.findById(id).orElseThrow(NotFoundException::new);
        aUserToUpdate.setUsername(aUser.getUsername());
        aUserToUpdate.setPassword(aUser.getPassword());
        aUserToUpdate.setName(aUser.getName());
        aUserToUpdate.setSurname(aUser.getSurname());
        aUserToUpdate.setDatastamp(new Date());
        AUser result = aUserRepository.save(aUserToUpdate);
        log.debug("returning {}", result);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public AUser findByUsername(String username) throws NotFoundException {
        log.debug("findByUsername(username={})", username);
        AUser result = aUserRepository.findByUsername(username)
                .orElseThrow(() -> {
                    String errorMessage = resourceBundleMessageSource.getMessage(NOT_FOUND_AUSER_USERNAME, new String[]{username}, LocaleContextHolder.getLocale());
                    log.warn(errorMessage);
                    return new NotFoundException(errorMessage);
                });

        log.debug("returning {}", result);
        return result;
    }

    @Override
    public void delete(AUser aUser) throws NotFoundException, BindingException {
        log.debug("delete(aUser={})", aUser);
        if (aUser.getId() == null)
            throw new BindingException(resourceBundleMessageSource.getMessage("NotNull.AUser.Id.Validation", null, LocaleContextHolder.getLocale()));

        AUser aUserToDeleted = aUserRepository.findById(aUser.getId()).orElseThrow(() -> new NotFoundException(
                resourceBundleMessageSource.getMessage(AUSER_NOT_FOUND_BY_ID, new Long[]{aUser.getId()}, LocaleContextHolder.getLocale())
        ));
        aUserRepository.delete(aUserToDeleted);
    }
}
