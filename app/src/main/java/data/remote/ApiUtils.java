package data.remote;




public class ApiUtils {

    private ApiUtils() {}

    public enum HTTPUrls {
        MAIN, CHAT
    }

    public static final String BASE_URL = "https://auth.uppsale.com/";
    public static final String CHAT_URL = "https://simpleretrtofitapp.firebaseio.com/";




    public static APIService getAPIService(HTTPUrls type) {
        String baseURL = "";
        switch (type){
            case MAIN:
                baseURL = BASE_URL;
                break;
            case CHAT:
                baseURL = CHAT_URL;
        }
        return RetrofitClient.getClient(baseURL).create(APIService.class);


    }

}
