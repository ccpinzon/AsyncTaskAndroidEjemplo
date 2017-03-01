package cristhianpinzon.com.webserviceejemplo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button boton;
    private TextView textView;

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

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                for (int i = 0;i<=100;i++){
//                    cargarDatos("Numero : " + i);
//                }
                MyTask task = new MyTask();
                task.execute();

            }
        });

    }

    public void cargarDatos(String datos) {

        textView.append(datos + "\n");

    }

    //tarea en segundo plano que trae datos
    private class MyTask extends AsyncTask <String,String,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            cargarDatos("Iniciar de Cargar");
        }

        @Override
        protected String doInBackground(String... strings) {
            return "Terminamos";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            cargarDatos(s);
        }
    }
}
