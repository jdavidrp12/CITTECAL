package com.jdev.myapplication.Calzados.adapter;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class SeccionesAdapater extends FragmentStatePagerAdapter {

    private final List<android.support.v4.app.Fragment> listaFragments= new ArrayList<>();
    private final List<String> listaTitulos= new ArrayList<>(  );

    public SeccionesAdapater(FragmentManager fm) {
        super( fm );
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listaTitulos.get( position );
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return listaFragments.get( position );
    }
    public  void addFragment(android.support.v4.app.Fragment fragment, String titulo){
        listaFragments.add( fragment );
        listaTitulos.add( titulo );

    }

    @Override
    public int getCount() {
        return listaFragments.size();
    }
}
