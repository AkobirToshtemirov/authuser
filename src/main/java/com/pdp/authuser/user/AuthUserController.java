package com.pdp.authuser.user;

import com.pdp.authuser.exception.NotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth-users")
public class AuthUserController {

    private final AuthUserRepository authUserRepository;
    private final AuthUserModelAssembler authUserModelAssembler;

    public AuthUserController(AuthUserRepository authUserRepository, AuthUserModelAssembler authUserModelAssembler) {
        this.authUserRepository = authUserRepository;
        this.authUserModelAssembler = authUserModelAssembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<AuthUser>> getAllAuthUsers() {
        List<AuthUser> authUsers = authUserRepository.findAll();
        return authUserModelAssembler.toCollectionModel(authUsers);
    }

    @GetMapping("/{id}")
    public EntityModel<AuthUser> getAuthUser(@PathVariable Long id) {
        AuthUser authUser = authUserRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("AuthUser not found with id: " + id));

        return authUserModelAssembler.toModel(authUser);
    }

    @PostMapping
    public EntityModel<AuthUser> createAuthUser(@RequestBody AuthUser newAuthUser) {
        AuthUser savedAuthUser = authUserRepository.save(newAuthUser);
        return authUserModelAssembler.toModel(savedAuthUser);
    }

    @PutMapping("/{id}")
    public EntityModel<AuthUser> updateAuthUser(@RequestBody AuthUser newAuthUser, @PathVariable Long id) {
        return authUserRepository.findById(id)
                .map(authUser -> {
                    authUser.setUsername(newAuthUser.getUsername());
                    authUser.setPassword(newAuthUser.getPassword());
                    return authUserModelAssembler.toModel(authUserRepository.save(authUser));
                })
                .orElseThrow(() -> new NotFoundException("AuthUser not found with id: " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteAuthUser(@PathVariable Long id) {
        if (authUserRepository.existsById(id)) {
            authUserRepository.deleteById(id);
        } else {
            throw new NotFoundException("AuthUser not found with id: " + id);
        }
    }
}