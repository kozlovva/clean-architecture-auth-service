package ru.foodtechlab.callcenter.auth.credential;

import com.rcore.rest.api.commons.routes.BaseRoutes;

public class Routes {

    public static final String ROOT = BaseRoutes.API + BaseRoutes.V1 + "/credentials";
    public static final String BY_ID = ROOT + "/{id}";

}
