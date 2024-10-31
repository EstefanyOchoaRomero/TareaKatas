package org.factoriaf5katasestefa.katasexamples;

public class Kataexamples {

    public class Personaje {
        private long vida = 1000;
        private static final int MUERTO = 0;
        private static final int VIVO = 1;
        private static final int MITAD_VIDA = 2;
        private static int maxAttack = 100;

        private int estado = VIVO;
        private int nivel = 1;

        public void recibirDanioDe(Personaje atacante, int danio) {
            if (this == atacante) {
                throw new IllegalArgumentException("Un personaje no puede atacarse a sí mismo");
            }
            
        
            if (this.nivel >= atacante.nivel + 5) {
                danio /= 2;
            } else if (this.nivel <= atacante.nivel - 5) {
                danio = (int) (danio * 1.5);
            }
            
        
            if (danio < 0) {
                throw new IllegalArgumentException("El daño no puede ser negativo");
            }
        
        
            vida -= danio;
            if (vida < 0) {
                vida = 0;
            }
        
            actualizarEstado();
        }
        
        
        private void actualizarEstado() {
            if (vida <= 0) {
                estado = MUERTO;
            } else if (vida <= 500) {
                estado = MITAD_VIDA;
            } else {
                estado = VIVO;
            }
        }

        public void curar(int cantidadCuracion) {
            if (cantidadCuracion < 0) {
                throw new IllegalArgumentException("La cantidad de curación no puede ser negativa");
            }

            vida += cantidadCuracion;
            if (vida > 1000) {
                vida = 1000;
            }

        
            actualizarEstado();
        }

        public void setNivel(int nuevoNivel) {
            if (nuevoNivel > 0) {
                nivel = nuevoNivel;
            }
        }

        public int getEstado() {
            return estado;
        }

        public int getNivel() {
            return nivel;
        }

        public long getVida() {
            return vida;
        }

        public static void setRangoMaximoAtaque(int rango) {
            if (rango > 0) {
                maxAttack = rango;
            }
        }

        public static int getRangoMaximoAtaque() {
            return maxAttack;
        }
    }

    public class Luchador extends Personaje {
        private int alcance = 2;

        public int getAlcance() {
            return alcance;
        }

        public void setAlcance(int nuevoAlcance) {
            if (nuevoAlcance > 0) {
                alcance = nuevoAlcance;
            }
        }

        public void atacar(Personaje objetivo) {
                int danio = Math.min(getRangoMaximoAtaque(), 100);
                objetivo.recibirDanioDe(this, danio);

        }
    }

    public class Cazador extends Personaje {
        private int alcance = 20;

        public int getAlcance() {
            return alcance;
        }

        public void setAlcance(int nuevoAlcance) {
            if (nuevoAlcance > 0) {
                alcance = nuevoAlcance;
            }
        }

        public void atacar(Personaje objetivo) {
                int danio = Math.min(getRangoMaximoAtaque(), 100);
                objetivo.recibirDanioDe(this, danio);

        }


    }
    
}




