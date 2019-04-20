package br.com.franciscochaves.geolocalizaoip;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);
    }

    public void carregarLocalizacao(String ip) {
        try {
            Localizacao localizacao = ClienteGeoIP.retornarLocalizacaoPorIp(ip);
            TextView resultado = findViewById(R.id.text_resultado);

            StringBuilder builder = new StringBuilder();
            builder.append("Resultado:\n");
            builder.append("Código do país: ");
            builder.append(localizacao.getCountryCode());
            builder.append("\n");
            builder.append("Nome do país: ");
            builder.append(localizacao.getCountry());
            builder.append("\n");
            builder.append("Estado: ");
            builder.append(localizacao.getRegionName());
            builder.append("\n");
            builder.append("Cidade: ");
            builder.append(localizacao.getCity());
            builder.append("\n");
            builder.append("CEP: ");
            builder.append(localizacao.getZip());

            resultado.setText(builder.toString());
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    public void btnLocalizarOnclick(View view){
        EditText txtIp = findViewById(R.id.edit_ip);
        String ip = txtIp.getText().toString();
        try {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 1);
            }else{
                //carregar localização
                carregarLocalizacao(ip);
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //carregar localização
                    EditText txtIp = findViewById(R.id.edit_ip);
                    String ip = txtIp.getText().toString();
                    carregarLocalizacao(ip);
                } else {
                    Toast.makeText(this, "Não vai funcionar!!!", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }
}
