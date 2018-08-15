package com.jdev.myapplication.Calzados.Vistas;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.jdev.myapplication.Calzados.adapter.HombreAdapter;
import com.jdev.myapplication.Entidades.producto;
import com.jdev.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Calz_Hombre.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Calz_Hombre#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Calz_Hombre extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    RecyclerView recyclerCalzadosH;
    ArrayList<producto> listaCalzadosH;

  //  ProgressDialog progress;
    RequestQueue request;
    ProgressDialog dialog;
    JsonObjectRequest jsonObjectRequest;

        /****  codigo para abrir activity ***/



    public Calz_Hombre() {
        // Required empty public constructor
    }

    /*****  añadido 14.08 *****************/




    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Calz_Hombre.
     */
    // TODO: Rename and change types and number of parameters
    public static Calz_Hombre newInstance(String param1, String param2) {
        Calz_Hombre fragment = new Calz_Hombre();
        Bundle args = new Bundle();
        args.putString( ARG_PARAM1, param1 );
        args.putString( ARG_PARAM2, param2 );
        fragment.setArguments( args );
        return fragment;
        }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (getArguments() != null) {
            mParam1 = getArguments().getString( ARG_PARAM1 );
            mParam2 = getArguments().getString( ARG_PARAM2 );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_calz__hombre,container, false);
        listaCalzadosH= new ArrayList<>();
        recyclerCalzadosH= vista.findViewById(R.id.idRecyclerHombre);
        recyclerCalzadosH.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerCalzadosH.setHasFixedSize(true);

        request = Volley.newRequestQueue(getContext());

        cargarWebServiceH();



        return vista;
    }


    private void cargarWebServiceH() {

        /***Codigo añadido progres**/

        dialog=new ProgressDialog(getContext());
        dialog.setMessage("Consultando ");
        dialog.show();


       //progress.setMessage( "Consultando..." );
        String url="http://192.168.0.106:8086/citecal/calzadoHList.php";
        jsonObjectRequest= new JsonObjectRequest( Request.Method.GET, url, null, this, this);
        request.add( jsonObjectRequest );
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "No se puede conectar "+error.toString(), Toast.LENGTH_LONG).show();
        System.out.println();
        Log.d("ERROR: ", error.toString());
        dialog.hide();
    }

    @Override
    public void onResponse(JSONObject response) {
        producto productos=null;
        JSONArray json=response.optJSONArray("producto");
        try {

            for (int i=0;i<json.length();i++){
                 productos  =new producto();
                JSONObject jsonObject=null;

                jsonObject=json.getJSONObject(i);
                productos.setCodigo(jsonObject.optString("codigo"));
                productos.setImagen(jsonObject.optString("imagen"));
                listaCalzadosH.add(productos);
            }
           dialog.hide();
            HombreAdapter adapter=new HombreAdapter(listaCalzadosH, getContext());

            /*******añadidoo***********/

            adapter.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(),
                            "SELECCION: "  +listaCalzadosH.get
                                    ( recyclerCalzadosH.getChildAdapterPosition(view)).getTipo(),Toast.LENGTH_SHORT).show();
                }
            } );

            /******/
            recyclerCalzadosH.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se ha podido establecer conexión con el servidor" +
                    " "+response, Toast.LENGTH_LONG).show();
            dialog.hide();
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction( uri );
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException( context.toString()
                    + " must implement OnFragmentInteractionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
