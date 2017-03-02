package cristhianpinzon.com.webserviceejemplo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cristhianpinzon.com.webserviceejemplo.POJO.Usuario;
import cristhianpinzon.com.webserviceejemplo.Parsers.UsuarioJSONParser;
import cristhianpinzon.com.webserviceejemplo.Parsers.UsuarioXMLParser;

public class MainActivity extends AppCompatActivity {

    private Button boton;
    private TextView textView;
    private ProgressBar progressBar;
    //List<MyTask> tasksList;

    List<Usuario> usuarioList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        beginComponents();
    }

    private void beginComponents() {

        boton = (Button) findViewById(R.id.boton);
        textView = (TextView) findViewById(R.id.textview);
        textView.setMovementMethod(new ScrollingMovementMethod());

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        //tasksList = new ArrayList<>();

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isOnline()){
                   // pedirDatos("http://192.168.0.23/ejemploWebService/usuarios.xml");
                    pedirDatos("http://maloschistes.com/maloschistes.com/jose/webservice.php");
                }else {
                    Toast.makeText(getApplicationContext(),"SIN CONEXION ",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void cargarDatos() {


        //aca estan los datos :D
        //textView.append(datos + "\n");

        if (!usuarioList.isEmpty()){
            for (Usuario usuario: usuarioList ) {
                textView.append(usuario.getTwitter() + "\n");
            }

        }

    }

    public void pedirDatos(String uri){

        MyTask task = new MyTask();

        //Forma seriada
        task.execute(uri);

        // Forma Paralela
        //task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    //verificar Conectividad

    public boolean isOnline() {

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if( networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        }
        else {
            return false;
        }

    }

    //tarea en segundo plano que trae datos
    private class MyTask extends AsyncTask <String,String,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //cargarDatos("Iniciar de Cargar");
            progressBar.setVisibility(View.VISIBLE);

            // agregar task a  la lista
            //tasksList.add(this);
        }

        @Override
        protected String doInBackground(String... params) {

//              for (int i = 0;i<=10;i++){
//                    //cargarDatos("Numero : " + i);
//                  publishProgress("Numero : " + i);
//                  try {
//                      Thread.sleep(100);
//                  } catch (InterruptedException e) {
//                      e.printStackTrace();
//                  }
//               }

            String data = HttpManager.getData(params[0]);
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //usuarioList = UsuarioXMLParser.parser(result);
            usuarioList = UsuarioJSONParser.parser(result);
            cargarDatos();

//            tasksList.remove(this);
//
//            if (tasksList.isEmpty())
//                progressBar.setVisibility(View.INVISIBLE);


        }

        @Override
        protected void onProgressUpdate(String... values) {
           // cargarDatos(values[0]);
        }
    }
}
