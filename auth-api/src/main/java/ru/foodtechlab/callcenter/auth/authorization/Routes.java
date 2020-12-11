package ru.foodtechlab.callcenter.auth.authorization;

import com.rcore.rest.api.commons.routes.BaseRoutes;

public class Routes {
    public static final String ROOT = BaseRoutes.NOT_SECURE + BaseRoutes.API + BaseRoutes.V1 + "/auth";

    public static final String LOGIN = ROOT + "/login";
    public static final String PASSWORD_LOGIN = LOGIN + "/password";
    public static final String TWO_FACTOR = LOGIN + "/two-factor";
    public static final String TWO_FACTOR_INIT = TWO_FACTOR + "/init";
    public static final String TWO_FACTOR_CONFIRM = TWO_FACTOR + "/confirm";

    public static final String REFRESH = ROOT + "/refresh";
    public static final String LOGOUT = ROOT + "/logout";
}
