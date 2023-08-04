package com.example.block20SpringBootKeycloadkOAuth20.infrastructure.util;

import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;

// http://localhost:9090/realms/Spring-Boot-Realm-dev/.well-known/openid-configuration/

public class KeycloakProvider {

    // Configuración del servidor Keycloak para consumir su API
    private static final String SERVER_URL = "http://localhost:9090";
    private static final String REALM_NAME = "Spring-Boot-Realm-dev";
    private static final String REALM_MASTER = "master";
    private static final String ADMIN_CLI = "admin-cli";
    private static final String USER_CONSOLE = "admin";
    private static final String PASSWORD_CONSOLE = "admin";
    private static final String CLIENT_SECRET = "XCUD3aqMdg6G5JOrm3GiJRrL3YLepdfx";

    public static RealmResource getRealmResource() {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(SERVER_URL)
                .realm(REALM_MASTER) // Vamos al MASTER ya que dentro de los clientes a parte de nuestro Realm, existen otros al mismo nivel que utiliza para operar como el admin-cli
                .clientId(ADMIN_CLI)
                .username(USER_CONSOLE)
                .password(PASSWORD_CONSOLE)
                .clientSecret(CLIENT_SECRET)
                .resteasyClient(new ResteasyClientBuilderImpl()
                        .connectionPoolSize(10) // Permitimos un máximo de 10 conexiones simultáneamente
                        .build())
                .build();

        return keycloak.realm(REALM_NAME);
    }

    // Manejar los USUARIOS
    public static UsersResource getUserResource() {
        // RealmResource
        RealmResource realmResource = getRealmResource();

        // Retornamos el UserResource que es el módulo que nos permite gestionar los usuarios
        return realmResource.users();
    }
}
