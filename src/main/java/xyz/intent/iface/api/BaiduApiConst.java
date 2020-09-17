package xyz.intent.iface.api;

/**
 * xyz.zzyitj.iface.constance
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/9/8 14:42
 * @since 1.0
 */
public class BaiduApiConst {
    public static final String TOKEN = "24.78c2261c73a18ccab5718ad385a9c7f2.2592000.1602666046.282335-22583648";
    /**
     * okhttp
     */
    public static final long DEFAULT_HTTP_CONNECT_TIMEOUT = 10;
    public static final long DEFAULT_HTTP_READ_TIMEOUT = 10;
    public static final long DEFAULT_HTTP_WRITE_TIMEOUT = 10;
    public static final String HOST = "https://aip.baidubce.com";
    /**
     * auth
     */
    public static final String AUTH_GET_TOKEN = "/oauth/2.0/token";
    public static final String AUTH_GRAND_TYPE = "client_credentials";
    public static final String AUTH_API_KEY = "OK2ELSIG75h9iX9bs20NZafl";
    public static final String AUTH_SECRET_KET = "4aqyLiTMxLj8mqXvhG3au0KWbPnz17aB";
    /**
     * shared_prefs
     */
    public static final String SHARED_PREFS_NAME = "baidu_api_shared_prefs";
    public static final String SHARED_PREFS_TOKEN = "token";
    public static final String SHARED_PREFS_TOKEN_EXPIRES = "token_expires";
    /**
     * face
     */
    public static final String DEFAULT_GROUP = "user";
    public static final String IMAGE_TYPE_BASE_64 = "BASE64";
    public static final String IMAGE_TYPE_URL = "URL";
    public static final String FACE_ADD_USER = "/rest/2.0/face/v3/faceset/user/add";
    public static final String FACE_SEARCH_USER = "/rest/2.0/face/v3/search";
    public static final float SAME_USER_MIN_SCORE = 80.0F;
}
