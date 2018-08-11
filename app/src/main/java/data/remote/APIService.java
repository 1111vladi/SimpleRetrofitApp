package data.remote;

import data.model.Post;
import data.model.TextPost;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;



public interface APIService {


    @POST("/token")
    @FormUrlEncoded
    Call<Post> savePost(@Field("username") String username,
                        @Field("password") String password,
                        @Field("grant_type") String grant_type);

    @POST("Chatbox.json")
    Call<ResponseBody> sendText(@Body TextPost message);

}
