package org.example;

import com.opencsv.CSVWriter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        String apiUrl = "https://api.github.com/repos/twbs/bootstrap/releases";
        List<String[]> data = new ArrayList<>();

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.connect();

            InputStream responseStream = connection.getInputStream();
            JSONTokener tokener = new JSONTokener(responseStream);

            JSONArray releases = new JSONArray(tokener);

            for (int i = 0; i < releases.length(); i++) {
                JSONObject release = releases.getJSONObject(i);
                String createdDate = release.getString("created_at");
                String tagName = release.getString("tag_name");

                // Fetching the URL for the distribution zip file
                JSONArray assets = release.getJSONArray("assets");
                String zipUrl = null;
                for (int j = 0; j < assets.length(); j++) {
                    JSONObject asset = assets.getJSONObject(j);
                    if (asset.getString("name").endsWith(".zip")) {
                        zipUrl = asset.getString("browser_download_url");
                        break;
                    }
                }

                data.add(new String[]{createdDate, tagName, zipUrl});
            }

            writeToCSV(data);
            System.out.println("Data written to CSV file successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToCSV(List<String[]> data) throws IOException {
        String csvFile = "bootstrap_releases.csv";
        FileWriter outputfile = new FileWriter(csvFile);
        CSVWriter writer = new CSVWriter(outputfile);

        String[] header = {"Created Date", "Tag name", "URL for the distribution zip file"};
        writer.writeNext(header);

        writer.writeAll(data);

        writer.close();
    }
}
