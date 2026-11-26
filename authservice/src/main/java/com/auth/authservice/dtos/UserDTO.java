package com.auth.authservice.dtos;

import com.auth.authservice.entities.Provider;
import lombok.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Transfers user account data into and out of the authentication service's user operations.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    /**
     * The unique identifier for the user account.
     */
    private UUID id;
    /**
     * The email address associated with the account.
     */
    private String email;
    /**
     * The password value supplied during account creation.
     */
    private String password;
    /**
     * The profile image associated with the account.
     */
    private String image;
    /**
     * Whether the account is enabled.
     */
    private boolean enable = true;
    /**
     * The time the account was created.
     */
    private Instant createdAt = Instant.now();
    /**
     * The time the account was last updated.
     */
    private Instant updatedAt = Instant.now();
    /**
     * The authentication provider associated with the account.
     */
    private Provider provider = Provider.LOCAL;
    /**
     * The roles assigned to the account.
     */
    private Set<RoleDTO> roles = new HashSet<>();

    private String name;
}
