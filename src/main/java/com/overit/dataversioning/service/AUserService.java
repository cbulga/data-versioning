package com.overit.dataversioning.service;

import com.overit.dataversioning.exception.BindingException;
import com.overit.dataversioning.exception.DuplicateException;
import com.overit.dataversioning.exception.NotFoundException;
import com.overit.dataversioning.model.AUser;

/**
 * Service to manage {@link AUser} instances.
 *
 * @author cristian.bulgarelli
 */
public interface AUserService {

    /**
     * Creates a new {@link AUser} in the application database, using the provided {@code AUser}.<br/>
     * Returns the inserted {@link AUser} instance.
     *
     * @param aUser {@link AUser} containing new values to be used to create a new corresponding user record.
     * @return the inserted {@link AUser} instance.
     * @throws DuplicateException exception thrown in case there is another user having the same {@link AUser#getUsername()}.
     * @throws BindingException exception thrown in case some validation error occurs.
     */
    AUser insert(AUser aUser) throws DuplicateException, BindingException;

    /**
     * Updates a {@link AUser} identified by the provided {@code id}, using the provided {@code aUser}.<br/>
     * Returns the updated {@link AUser} instance.
     *
     * @param id    {@link Long} about the {@link AUser#getId()} filter.
     * @param aUser {@link AUser} containing new values to be used to update the corresponding user record.
     * @return the updated {@link AUser} instance.
     * @throws NotFoundException exception thrown in case no user is found corresponding to the provided id.
     * @throws BindingException exception thrown in case some validation error occurs.
     */
    AUser updateById(Long id, AUser aUser) throws NotFoundException, BindingException;

    /**
     * Returns a {@link AUser} about the user identified by the provided {@code username}.
     *
     * @param username {@link String} about the username filter.
     * @return a {@link AUser} about the user identified by the provided {@code username}.
     * @throws NotFoundException exception thrown in case no user is found corresponding to the provided username.
     */
    AUser findByUsername(String username) throws NotFoundException;

    /**
     * Deletes the provided {@link AUser} from the application database.
     *
     * @param aUser {@link AUser} to be deleted.
     * @throws NotFoundException exception thrown in case no user is found corresponding to the provided id.
     * @throws BindingException exception thrown in case some validation error occurs.
     */
    void delete(AUser aUser) throws NotFoundException, BindingException;
}
