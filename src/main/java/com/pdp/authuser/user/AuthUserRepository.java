package com.pdp.authuser.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "auth-users", collectionResourceRel = "authUsers")
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
}