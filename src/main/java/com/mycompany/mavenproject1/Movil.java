/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author anibal
 */
public class Movil {

    private int Id;
    private String Marca;
    private int AnhoCreacion;
    private String SO;

    public Movil(int id, String marca, int anhoCreacion, String so) {
        this.Id = id;
        this.Marca = marca;
        this.AnhoCreacion = anhoCreacion;
        this.SO = so;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public int getAnhoCreacion() {
        return AnhoCreacion;
    }

    public void setAnhoCreacion(int AnhoCreacion) {
        this.AnhoCreacion = AnhoCreacion;
    }

    public String getSO() {
        return SO;
    }

    public void setSO(String SO) {
        this.SO = SO;
    }
}
