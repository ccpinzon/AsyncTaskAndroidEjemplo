package cristhianpinzon.com.webserviceejemplo.Parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cristhianpinzon.com.webserviceejemplo.POJO.Usuario;

/**
 * Created by cristhian on 1/03/17.
 */

public class UsuarioJSONParser {

    public static List<Usuario> parser(String data){

        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(data);
            List<Usuario> usuarioList = new ArrayList<>();

            for (int i = 0; i < jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Usuario usuario = new Usuario();
                usuario.setUsuarioId(jsonObject.getInt("usuarioid"));
                usuario.setNombre(jsonObject.getString("nombre"));
                usuario.setTwitter(jsonObject.getString("twitter"));

                usuarioList.add(usuario);

            }
            return usuarioList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

}
