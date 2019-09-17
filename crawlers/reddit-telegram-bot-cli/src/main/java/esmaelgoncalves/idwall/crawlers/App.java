package esmaelgoncalves.idwall.crawlers;

import esmaelgoncalves.idwall.crawlers.service.ApiClientService;

/**
 * Hello world!
 */
public class App {
    public static final String DEFAULT_BASE_URL = "http://localhost:8080/";
    public static final String DEFAULT_THREAD = "cats";

    public static void main(String[] args) {

        String baseUrl = DEFAULT_BASE_URL;
        String thread = DEFAULT_THREAD;

        switch (args.length) {
            case 1:
                baseUrl = args[0];
                break;
            case 2:
                baseUrl = args[0];
                thread = args[1];
                break;
        }

        System.out.println("Inputs: ");
        System.out.println("Base URL: " + baseUrl);
        System.out.println("Threads: " + thread);
        System.out.println("=========================\n");

        System.out.println("Aguarde estamos buscando o conteúdo solicitado.\n");

        ApiClientService apiClientService = new ApiClientService(baseUrl);
        apiClientService.getThreadsInfo(thread);
    }
}
