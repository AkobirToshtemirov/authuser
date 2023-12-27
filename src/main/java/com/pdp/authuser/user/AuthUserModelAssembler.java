package com.pdp.authuser.user;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AuthUserModelAssembler implements RepresentationModelAssembler<AuthUser, EntityModel<AuthUser>> {

    @Override
    public EntityModel<AuthUser> toModel(AuthUser authUser) {
        return EntityModel.of(
                authUser,
                linkTo(methodOn(AuthUserController.class).getAuthUser(authUser.getId())).withSelfRel(),
                linkTo(methodOn(AuthUserController.class).getAllAuthUsers()).withRel("authUsers")
        );
    }
}
