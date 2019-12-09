package dtt.lukuvinkkikirjasto.demo.domain;

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
