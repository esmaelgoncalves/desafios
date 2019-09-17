package esmaelgoncalves.idwall.crawlers.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import esmaelgoncalves.idwall.crawlers.clienthttp.EndpointInterface;
import esmaelgoncalves.idwall.crawlers.dto.ThreadReddit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;


public class ApiClientService {
    private Gson gson;
    private Retrofit retrofit;
    private EndpointInterface apiService;

    public ApiClientService(String baseUrl) {
        this.gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
        this.retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
        this.apiService = retrofit.create(EndpointInterface.class);
    }

    public void getThreadsInfo(String threads) {
        Call<List<ThreadReddit>> call = apiService.getThreads(threads);

        try {
            Response<List<ThreadReddit>> response = call.execute();

            if(200 == response.code()){
                StringBuilder responseBuilder = new StringBuilder();
                System.out.println("Veja só o que encontramos para você:\n");

                response.body().forEach(t-> {
                    responseBuilder.append("Título: " + t.getTitle()).append("\n");
                    responseBuilder.append("Sub Reddit: " + t.getSubReddit()).append("\n");
                    responseBuilder.append("Votos: " + t.getPoints()).append("\n");
                    responseBuilder.append("Acesse a thread pelo link: " + t.getThreadUrl()).append("\n");
                    responseBuilder.append("Veja os comentarios pelo link: " + t.getCommentsUrl()).append("\n");
                    responseBuilder.append("\n");
                });

                System.out.println(responseBuilder.toString());
            } else {
                System.out.println("Nenhum conteúdo encontrado.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
