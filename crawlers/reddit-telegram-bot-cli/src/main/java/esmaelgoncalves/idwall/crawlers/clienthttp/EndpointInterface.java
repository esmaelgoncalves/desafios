package esmaelgoncalves.idwall.crawlers.clienthttp;

import esmaelgoncalves.idwall.crawlers.dto.ThreadReddit;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface EndpointInterface {

    @GET("api/v1/threads/{threads}")
    Call<List<ThreadReddit>> getThreads(@Path("threads") String threads);
}
