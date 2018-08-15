package com.jdev.myapplication.Calzados.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.jdev.myapplication.Entidades.producto;
import com.jdev.myapplication.R;


import java.util.List;

/**
 * Created by David on 09/08/2018.
 */

public class HombreAdapter

        extends RecyclerView.Adapter<HombreAdapter.HombreHolder>
    /*****AÑADIDO*********/
        implements  View.OnClickListener{

    List<producto> listaCalzadosH;
    RequestQueue request;
    Context context;

    private View.OnClickListener listener;




    public HombreAdapter(List<producto> listaCalzadosH, Context context){
        this.listaCalzadosH = listaCalzadosH;
        this.context= context;
        request = Volley.newRequestQueue(context);

    }

    @Override
    public HombreAdapter.HombreHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from( parent.getContext()).inflate( R.layout.fragment_hombre,parent,false);
        RecyclerView.LayoutParams layoutParams= new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams( layoutParams);
        /****añadido********/
        vista.setOnClickListener(this);
/*****************/
        return new HombreHolder( vista);
    }

    @Override
    public void onBindViewHolder(HombreHolder holder, int position) {

        holder.txtCodigo.setText( listaCalzadosH.get( position ).getCodigo().toString() );


        if (listaCalzadosH.get(position).getImagen()!=null){
            cargarImagenWebService(listaCalzadosH.get(position).getImagen(),holder);
           // holder.imagen.setImageBitmap(listaCalzadosH.get(position).getImagen());
          //  cargarImagenWebService(listaCalzadosH.get(position).getImagen(),holder);
        }else{
            holder.imagen.setImageResource(R.drawable.ic_menu_gallery);
        }

    }

    private void cargarImagenWebService(String rutaImagen, final HombreHolder holder) {

        String urlImagen="http://192.168.0.106:8086/citecal/"+rutaImagen;


        urlImagen=urlImagen.replace(" ","%20");

        ImageRequest imageRequest=new ImageRequest(urlImagen, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                holder.imagen.setImageBitmap(response);

            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Error al cargar la imagen", Toast.LENGTH_SHORT).show();
            }
        });
        request.add(imageRequest);

    }



    @Override
    public int getItemCount() {
        return listaCalzadosH.size();
    }

    public  void setOnClickListener(View.OnClickListener  listener){
        this.listener = listener;
    }

/********AÑADIDO********/

    @Override
    public void onClick(View view) {
        if(listener!= null){
            listener.onClick( view );
        }

    }
/****************/
    public class HombreHolder extends  RecyclerView.ViewHolder {
        TextView txtCodigo;
        ImageView imagen;
            public HombreHolder(View  itemView){
                super(itemView);
                txtCodigo = (TextView) itemView.findViewById( R.id.txtCodigo );
                imagen=(ImageView) itemView.findViewById(R.id.idImgH);

            }
    }

}
