package com.overit.dataversioning.repository;

import com.overit.dataversioning.model.AUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AUserRepository extends CrudRepository<AUser, Long> {

    /**
     * Returns a {@link Optional}&lt;{@link AUser}&gt; about the user identified by the provided {@code username}.
     *
     * @param username {@link String} about the username filter.
     * @return a {@link Optional}&lt;{@link AUser}&gt; about the user identified by the provided {@code username}.
     */
    Optional<AUser> findByUsername(String username);
}
