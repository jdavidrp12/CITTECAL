package com.jdev.myapplication.CartAcc;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jdev.myapplication.CartAcc.Clases.UtilidadesC;
import com.jdev.myapplication.CartAcc.Vistas.CartBolsos;
import com.jdev.myapplication.CartAcc.Vistas.CartAccesorios;

import com.jdev.myapplication.CartAcc.adapter.SeccionesAdapaterC;
import com.jdev.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CartAcc.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CartAcc#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartAcc extends Fragment {


    View vista;
    private OnFragmentInteractionListener mListener;
    private AppBarLayout appBar;
    private TabLayout pestañas;
    private ViewPager viewPager;



    public CartAcc() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CartAcc newInstance(String param1, String param2) {
        CartAcc fragment = new CartAcc();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_cart_acc, container, false  );


        if(UtilidadesC.rotacion == 0){
            View parent = (View) container.getParent();
            if(appBar== null){
                appBar = parent.findViewById( R.id.appBar);
                pestañas = new TabLayout(getActivity());
                 //             pestañas.setTabTextColors( Color.parseColor("FFFFFF"), Color.parseColor( "FFFFFF" ) );
                pestañas.setTabTextColors(Color.parseColor("#FFFFFF"),Color.parseColor("#FFFFFF"));

                     appBar.addView( pestañas );

                viewPager = (ViewPager)vista.findViewById( R.id.idviewPagerInfo);
                llenarViewPagerC(viewPager);

                viewPager.addOnPageChangeListener( new ViewPager.SimpleOnPageChangeListener(){
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        super.onPageScrolled( position, positionOffset, positionOffsetPixels );
                    }
                } );
                pestañas.setupWithViewPager(viewPager);
            }
            pestañas.setTabGravity(TabLayout.GRAVITY_FILL);
        }else{
            UtilidadesC.rotacion=1;
        }
        return vista;
    }

    private void llenarViewPagerC(ViewPager viewPager) {

        SeccionesAdapaterC adapater = new SeccionesAdapaterC( getFragmentManager() );
        adapater.addFragment( new CartBolsos(),"Bolsos" );
        adapater.addFragment( new CartAccesorios(),"Accesorios" );
        viewPager.setAdapter( adapater );
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction( uri );
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(UtilidadesC.rotacion==0)
            appBar.removeView(pestañas);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
