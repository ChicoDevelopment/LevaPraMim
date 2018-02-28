package math.levapramim.dao;

import android.app.Fragment;
import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import math.levapramim.modelo.Veiculo;

/**
 * Created by txring on 21/02/2018.
 */

public class VeiculoDAO extends Fragment {

    private String pesquisarUrl = "";
    private String alterarUrl = "";
    private String excluirUrl = "";
    private String inserirUrl = "http://192.168.43.199/insereVeiculo.php";
    private RequestQueue requestQueue;
    private boolean confirmar;
    private Veiculo veiculo = new Veiculo();

    public Veiculo pesquisar(Veiculo v) {

        Map<String, String> params = new HashMap();
        params.put("idVeiculo", v.getIdVeiculo());

        JSONObject parameters = new JSONObject(params);

        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                pesquisarUrl, parameters, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response.toString());
                try {
                    JSONObject veiculoJSON = response.getJSONObject("veiculo");
                    veiculo.setPlaca(response.getString("placa"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.append(error.getMessage());

            }
        });
        requestQueue.add(jsonObjectRequest);
        return veiculo;
    }

    public boolean alterar(Veiculo v) {

        Map<String, String> params = new HashMap();
        params.put("idVeiculo", v.getIdVeiculo());

        JSONObject parameters = new JSONObject(params);

        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                alterarUrl, parameters, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                confirmar = true;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                confirmar = false;

            }
        });
        requestQueue.add(jsonObjectRequest);
        return confirmar;
    }

    public boolean excluir(Veiculo v) {

        Map<String, String> params = new HashMap();
        params.put("idVeiculo", v.getIdVeiculo());

        JSONObject parameters = new JSONObject(params);

        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                excluirUrl, parameters, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                confirmar = true;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                confirmar = false;

            }
        });
        requestQueue.add(jsonObjectRequest);
        return confirmar;
    }

    public boolean inserir(final Veiculo v, final Context c) {

        requestQueue = Volley.newRequestQueue(c);

        StringRequest request = new StringRequest(Request.Method.POST, inserirUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response.toString());
                confirmar = true;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                confirmar = false;
                System.out.println(error.getMessage());

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("marca", v.getPlaca().toString());
                parameters.put("modelo", v.getModelo().toString());
                parameters.put("placa", v.getPlaca().toString());
                parameters.put("cor", v.getCor().toString());

                return parameters;
            }
        };
        requestQueue.add(request);
        return (confirmar);
    }
}
