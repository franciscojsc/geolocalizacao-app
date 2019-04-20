package br.com.franciscochaves.geolocalizaoip;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClienteGeoIP {

    private static String readStream(InputStream in){
        BufferedReader r = new BufferedReader(new InputStreamReader(in));
        StringBuilder total = new StringBuilder();
        String line;

        try {
            while ((line = r.readLine()) != null) {
                total.append(line).append('\n');
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return total.toString();
    }

    private static String request(String stringUrl){
        URL url = null;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(stringUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            return readStream(in);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            urlConnection.disconnect();
        }
        return "";
    }

    public static Localizacao retornarLocalizacaoPorIp(String ip) throws JSONException {
        String responseBody = request("http://ip-api.com/json/" + ip);
        JSONObject obj = new JSONObject(responseBody);
        String codigoPais = obj.get("countryCode").toString();
        String pais = obj.get("country").toString();
        String estado = obj.get("regionName").toString();
        String cidade = obj.get("city").toString();
        String cep = obj.get("zip").toString();
        return new Localizacao(codigoPais, pais, cidade, estado, cep  );
    }
}