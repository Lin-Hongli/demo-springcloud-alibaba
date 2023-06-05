package com.xxxcloud.common.core.constants;

/**
 * Jwt令牌的相关常量
 *
 * @author LinHongli
 */
public class JwtTokenConstant {
    /**
     * 令牌自定义标识
     */
    public static final String AUTH_KEY = "Authorization";

    /**
     * 令牌前缀
     */
    public static final String PREFIX = "Bearer ";

    /**
     * 令牌秘钥
     */
    public final static String SECRET = "linhonglidashuaibi";


    String AVATAR = "avatar";
    String HEADER = "blade-auth";
    String BEARER = "bearer";
    String ACCESS_TOKEN = "access_token";
    String REFRESH_TOKEN = "refresh_token";
    String TOKEN_TYPE = "token_type";
    String EXPIRES_IN = "expires_in";
    String ACCOUNT = "account";
    String USER_ID = "user_id";
    String ROLE_ID = "role_id";
    String DEPT_ID = "dept_id";
    String USER_NAME = "user_name";
    String ROLE_NAME = "role_name";
    String TENANT_ID = "tenant_id";
    String OAUTH_ID = "oauth_id";
    String CLIENT_ID = "client_id";
    String LICENSE = "license";
    String LICENSE_NAME = "powered by blade";
    String DEFAULT_AVATAR = "https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png";
    Integer AUTH_LENGTH = 7;
    int SIGN_KEY_LENGTH = 32;

}
