/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.listacompras.bean;

import javax.ejb.Stateless;

/**
 *
 * @author Mayko
 */
@Stateless
public class TemperatureConverter {

    public double convertToCelsius(double f) {
        return ((f - 32) * 5 / 9);
    }

    public double convertToFarenheit(double c) {
        return ((c * 9 / 5) + 32);
    }
}
