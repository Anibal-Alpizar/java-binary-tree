/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author anibal
 */
public class ArbolBinario {

    Nodo raiz;

    public ArbolBinario() {
        this.raiz = null;
    }

    public void insertarNodo(Movil movil) {
        if (buscarId(raiz, movil.getId())) {
            return;
        }
        raiz = insertarRec(raiz, movil);
    }

    public boolean buscarId(Nodo raiz, int id) {
        if (raiz == null) {
            return false;
        }
        if (raiz.movil.getId() == id) {
            return true;
        }
        return buscarId(raiz.izquierda, id) || buscarId(raiz.derecha, id);
    }

    private Nodo insertarRec(Nodo raiz, Movil movil) {
        if (raiz == null) {
            raiz = new Nodo(movil);
            return raiz;
        }

        if (movil.getId() < raiz.movil.getId()) {
            raiz.izquierda = insertarRec(raiz.izquierda, movil);
        } else if (movil.getId() > raiz.movil.getId()) {
            raiz.derecha = insertarRec(raiz.derecha, movil);
        }
        return raiz;
    }

    public void eliminarNodo(int id) {
        raiz = eliminarRec(raiz, id);
    }

    private Nodo eliminarRec(Nodo raiz, int id) {
        if (raiz == null) {
            return raiz;
        }

        if (id < raiz.movil.getId()) {
            raiz.izquierda = eliminarRec(raiz.izquierda, id);
        } else if (id > raiz.movil.getId()) {
            raiz.derecha = eliminarRec(raiz.derecha, id);
        } else {
            if (raiz.izquierda == null && raiz.derecha == null) {
                return null;
            } else if (raiz.izquierda == null) {
                return raiz.derecha;
            } else if (raiz.derecha == null) {
                return raiz.izquierda;
            }
            Nodo sucesor = encontrarSucesor(raiz.derecha);
            raiz.movil = sucesor.movil;
            raiz.derecha = eliminarRec(raiz.derecha, sucesor.movil.getId());
        }
        return raiz;
    }

    private Nodo encontrarSucesor(Nodo nodo) {
        Nodo actual = nodo;

        while (actual.izquierda != null) {
            actual = actual.izquierda;
        }
        return actual;
    }

    public boolean tieneDosHijos(int id) {
        return tieneDosHijosRec(raiz, id);
    }

    private boolean tieneDosHijosRec(Nodo nodo, int id) {
        if (nodo == null) {
            return false;
        }

        if (nodo.movil.getId() == id) {
            return nodo.izquierda != null && nodo.derecha != null;
        }

        if (id < nodo.movil.getId()) {
            return tieneDosHijosRec(nodo.izquierda, id);
        } else {
            return tieneDosHijosRec(nodo.derecha, id);
        }
    }

    public void buscarNodoPorId(int id) {
        Nodo nodoEncontrado = buscarNodoPorIdRec(raiz, id);
        if (nodoEncontrado != null) {
            mostrarInfoNodoEnJOptionPane(nodoEncontrado);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró un dispositivo móvil con el ID " + id, "Búsqueda", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private Nodo buscarNodoPorIdRec(Nodo nodo, int id) {
        if (nodo == null || nodo.movil.getId() == id) {
            return nodo;
        }

        if (id < nodo.movil.getId()) {
            return buscarNodoPorIdRec(nodo.izquierda, id);
        } else {
            return buscarNodoPorIdRec(nodo.derecha, id);
        }
    }

    private void mostrarInfoNodoEnJOptionPane(Nodo nodo) {
        Movil movil = nodo.movil;
        String mensaje = "ID: " + movil.getId() + "\n"
                + "Marca: " + movil.getMarca() + "\n"
                + "Año de creación: " + movil.getAnhoCreacion() + "\n"
                + "Sistema Operativo: " + movil.getSO();

        JOptionPane.showMessageDialog(null, mensaje, "Información del Dispositivo Móvil", JOptionPane.INFORMATION_MESSAGE);
    }

    public String recorridoPreorden() {
        StringBuilder stringBuilder = new StringBuilder();
        recorridoPreordenRec(raiz, stringBuilder);
        return stringBuilder.toString().trim();
    }

    private void recorridoPreordenRec(Nodo nodo, StringBuilder stringBuilder) {
        if (nodo != null) {
            stringBuilder.append(nodo.movil.getId()).append(" - ");
            recorridoPreordenRec(nodo.izquierda, stringBuilder);
            recorridoPreordenRec(nodo.derecha, stringBuilder);
        }
    }

    public String recorridoPostorden() {
        StringBuilder stringBuilder = new StringBuilder();
        recorridoPostordenRec(raiz, stringBuilder);
        return stringBuilder.toString().trim();
    }

    private void recorridoPostordenRec(Nodo nodo, StringBuilder stringBuilder) {
        if (nodo != null) {
            recorridoPostordenRec(nodo.izquierda, stringBuilder);
            recorridoPostordenRec(nodo.derecha, stringBuilder);
            stringBuilder.append(nodo.movil.getId()).append(" - ");
        }
    }

    public String recorridoInorden() {
        StringBuilder stringBuilder = new StringBuilder();
        recorridoInordenRec(raiz, stringBuilder);
        return stringBuilder.toString().trim();
    }

    private void recorridoInordenRec(Nodo nodo, StringBuilder stringBuilder) {
        if (nodo != null) {
            recorridoInordenRec(nodo.izquierda, stringBuilder);
            stringBuilder.append(nodo.movil.getId()).append(" - ");
            recorridoInordenRec(nodo.derecha, stringBuilder);
        }
    }

    public List<Nodo> obtenerNodosHojas() {
        List<Nodo> nodosHojas = new ArrayList<>();
        obtenerNodosHojasRec(raiz, nodosHojas);
        return nodosHojas;
    }

    private void obtenerNodosHojasRec(Nodo nodo, List<Nodo> nodosHojas) {
        if (nodo != null) {
            if (nodo.izquierda == null && nodo.derecha == null) {
                nodosHojas.add(nodo);
            }
            obtenerNodosHojasRec(nodo.izquierda, nodosHojas);
            obtenerNodosHojasRec(nodo.derecha, nodosHojas);
        }
    }

    public int calcularAltura() {
        return calcularAlturaRec(raiz);
    }

    private int calcularAlturaRec(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        int alturaIzquierda = calcularAlturaRec(nodo.izquierda);
        int alturaDerecha = calcularAlturaRec(nodo.derecha);
        return Math.max(alturaIzquierda, alturaDerecha) + 1;
    }

    public void mostrarArbol(Nodo raiz) {
        if (raiz != null) {
            mostrarArbol(raiz.izquierda);
            System.out.println("ID: " + raiz.movil.getId() + " - Marca: " + raiz.movil.getMarca() + " - Año: " + raiz.movil.getAnhoCreacion() + " - SO: " + raiz.movil.getSO());
            mostrarArbol(raiz.derecha);
        }
    }
}
