package ru.foodtechlab.callcenter.auth.roles;

import com.rcore.rest.api.commons.routes.BaseRoutes;

public class Routes {
    public static final String ROOT = BaseRoutes.API + BaseRoutes.V1 + "/roles";
    public static final String BY_ID = ROOT + "/{id}";

}
