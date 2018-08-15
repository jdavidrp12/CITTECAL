package com.jdev.myapplication.Entidades;

public class producto {

    private int idproducto;
    private  String modelo;
    private String cuero;
    private  String color;
    private String forro;
    private  String costura;
    private  String piezas;
    private String imagen;
    private tipo Tipo;
    private  categoria Categoria;
    private String codigo;
    private String concepto;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCuero() {
        return cuero;
    }

    public void setCuero(String cuero) {
        this.cuero = cuero;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getForro() {
        return forro;
    }

    public void setForro(String forro) {
        this.forro = forro;
    }

    public String getCostura() {
        return costura;
    }

    public void setCostura(String costura) {
        this.costura = costura;
    }

    public String getPiezas() {
        return piezas;
    }

    public void setPiezas(String piezas) {
        this.piezas = piezas;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public tipo getTipo() {
        return Tipo;
    }

    public void setTipo(tipo tipo) {
        Tipo = tipo;
    }

    public categoria getCategoria() {
        return Categoria;
    }

    public void setCategoria(categoria categoria) {
        Categoria = categoria;
    }
}
