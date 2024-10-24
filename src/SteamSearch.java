import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class SteamSearch {
    private final String apiKey;

    public SteamSearch() {
        this.apiKey = ""; //Insert api key
    }


    public static String getAppIdFromName(String gameName) {
        try {

            String query = gameName.replace(" ", "+");

            String url = "https://store.steampowered.com/search/?term=" + query;

            Document doc = Jsoup.connect(url).get();

            Element firstResult = doc.select(".search_result_row").first();

            if (firstResult != null) {
                String appId = firstResult.attr("data-ds-appid");
                return appId;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public String getGameInfo(String appId, String infoType) {
        try {

            String url = "https://store.steampowered.com/api/appdetails?appids=" + appId + "&key=" + apiKey;

            // Realiza la solicitud a la API
            Document doc = Jsoup.connect(url).ignoreContentType(true).get();
            String jsonResponse = doc.text();

            // Selecciona el tipo de información según el parámetro infoType
            switch (infoType.toLowerCase()) {
                case "description":
                    return jsonResponse
                            .replaceAll("\"", "")
                            .split("short_description:")[1].split(",")[0].trim();

                case "storelink":
                    return jsonResponse
                            .replaceAll("\"", "")
                            .split("url:")[1].split(",")[0].trim();

                case "name":
                    return jsonResponse
                            .replaceAll("\"", "")
                            .split("name:")[1].split(",")[0].trim();

                default:
                    return "Tipo de información no soportada: " + infoType;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
