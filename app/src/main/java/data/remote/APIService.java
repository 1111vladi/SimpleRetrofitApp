package data.remote;

import data.model.Post;
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


}
