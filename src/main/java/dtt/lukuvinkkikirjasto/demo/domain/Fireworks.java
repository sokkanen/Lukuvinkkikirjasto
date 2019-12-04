/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtt.lukuvinkkikirjasto.demo.domain;

/**
 *
 * @author milla
 */
public class Fireworks {

    private boolean newbook;

    public Fireworks() {

        this.newbook = true;

        new Thread() {
            @Override
            public void run() {
                try {
                    this.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                newbook = false;

            }
        }.start();

    }

    public boolean isNew() {
        return newbook;
    }



}
