package org.factoriaf5katasestefa.katasexamples;

    

import static org.junit.jupiter.api.Assertions.*;

import org.factoriaf5katasestefa.katasexamples.Kataexamples.Personaje;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
    
    public class KataexamplesTest {
        private Kataexamples kata;
        private Kataexamples.Personaje personaje1;
        private Kataexamples.Luchador luchador;
        private Kataexamples.Cazador cazador;
        private Personaje personajeDefensor;
    
        @BeforeEach
        public void setUp() {
            kata = new Kataexamples();
            personaje1 = kata.new Personaje();
            luchador = kata.new Luchador();
            cazador = kata.new Cazador();
        }

    
    
        @Test
        public void givenLuchadorWhenAttacksCazadorThenCazadorTakesDamage() {
            luchador.setNivel(1);
            cazador.setNivel(1);
            luchador.atacar(cazador);
            assertEquals(900, cazador.getVida());
        }
    
        @Test
        public void setNivelZero() {
            luchador.setNivel(1);
            luchador.setNivel(0);
            assertEquals(1, luchador.getNivel());
        }

        @Test
        public void givenCazadorWhenAttacksLuchadorThenLuchadorTakesDamage() {
            cazador.setNivel(1);
            luchador.setNivel(1);
            cazador.atacar(luchador);
            assertEquals(900, luchador.getVida());
        }
    
        @Test
        public void givenCazadorWithHigherLevelWhenAttacksLuchadorThenDamageIsReduced() {
            cazador.setNivel(6);
            luchador.setNivel(1);
            cazador.atacar(luchador);
            assertEquals(850, luchador.getVida());
        }
    
    
        @Test
        public void givenLuchadorWithHigherLevelWhenAttacksCazadorThenDamageIsIncreased() {
            luchador.setNivel(6);
            cazador.setNivel(1);
            luchador.atacar(cazador);
            assertEquals(850, cazador.getVida());
        }
    
    
        @Test
        public void givenLuchadorWhenSelfAttacksThenThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> luchador.recibirDanioDe(luchador, 50));
            assertTrue(true);
        }
    
    
        @Test
        public void givenPersonajeWhenHealsThenLifeDoesNotExceed1000() {
            personaje1.curar(200);
            assertEquals(1000, personaje1.getVida());
        }
    
        @Test
        public void givenPersonajeWhenReceivesFatalDamageThenShouldBeDead() {
            personaje1.recibirDanioDe(luchador, 1000);
            assertEquals(0, personaje1.getVida());
            assertEquals(0, personaje1.getEstado());

        }
            @Test
            public void givenNegativeDamage_whenApplyDamage_thenThrowsIllegalArgumentException() {
                
                Kataexamples kataexamples = new Kataexamples();
                Personaje atacante = kataexamples.new Personaje();
                Personaje personaje = kataexamples.new Personaje();
                int negativeDamage = -50;
        
            
                IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> personaje.recibirDanioDe(atacante, negativeDamage)
                );
                
                assertEquals("El daño no puede ser negativo", exception.getMessage());
            }

        @Test
        public void givenDamageExceedingMaxAttack_whenApplyDamage_thenDamageIsLimitedToMaxAttack() {
    
        Kataexamples kata = new Kataexamples();
        Personaje atacante = kata.new Personaje();
        int excessiveDamage = 150; 
        
    
        atacante.recibirDanioDe(personaje1, excessiveDamage);

        assertEquals(850, atacante.getVida());
                
    
        atacante.recibirDanioDe(personaje1, excessiveDamage);
                
    
        assertEquals(700, atacante.getVida());
            }
        
        @Test
        public void givenCazadorWithHigherLevel_whenAttackedByLuchador_thenDamageIsReduced() {
    
        Kataexamples kata = new Kataexamples();
        Personaje cazador = kata.new Personaje();
        cazador.setNivel(10);  // Nivel alto
        Personaje luchador = kata.new Personaje();
        luchador.setNivel(3);  // Nivel bajo
        int initialVida = (int) cazador.getVida();
        int originalDamage = 100;
    
        // When
        cazador.recibirDanioDe(luchador, originalDamage);
    
        // Then
        int expectedVida = initialVida - (originalDamage / 2);
        assertEquals(expectedVida, cazador.getVida(), "El daño debe reducirse a la mitad si el nivel del atacante es 5 o más niveles inferior al objetivo");
        assertEquals(1, cazador.getEstado(), "El estado debería ser MITAD_VIDA cuando la vida es menor a 500");
        assertEquals(1, luchador.getEstado(), "El estado debería ser MITAD_VIDA cuando la vida es menor a 500");
    }
            
        @Test
        public void givenLuchadorWithHigherLevel_whenAttacksCazador_thenDamageIsIncreased() {
            // Given
        Kataexamples kata = new Kataexamples();
        Personaje luchador = kata.new Personaje();
        cazador.setNivel(3);    // Nivel bajo
        int initialVida = (int) cazador.getVida();
        int originalDamage = 100;

        // When
        cazador.recibirDanioDe(luchador, originalDamage);

        // Then
        int expectedVida = initialVida - (int) (originalDamage * 1);  // Daño incrementado en 50%
        assertEquals(expectedVida, cazador.getVida(), "El daño debe incrementarse en un 50% si el nivel del atacante es 5 o más niveles superior al objetivo");

        }

        @Test
        public void givenPersonajeWhenLifeIsExactly500ThenEstadoIsMITAD_VIDA() {
        Kataexamples kataexamples = new Kataexamples();
        Personaje personaje = kataexamples.new Personaje();
        personaje.recibirDanioDe(kataexamples.new Personaje(), 500); // Baja la vida a exactamente 500
        assertEquals(500, personaje.getVida());
        assertEquals(2, personaje.getEstado(), "El estado debería ser MITAD_VIDA cuando la vida es exactamente 500");

        }

    @Test
    public void givenPersonajeWhenLifeIsBelow500ThenEstadoIsMITAD_VIDA() {
        Kataexamples kataexamples = new Kataexamples();
        Personaje personaje = kataexamples.new Personaje();
        personaje.recibirDanioDe(kataexamples.new Personaje(), 500); // Baja la vida a 500
        assertEquals(500, personaje.getVida());
        assertEquals(2, personaje.getEstado(), "El estado debería ser MITAD_VIDA cuando la vida es menor a 500");
    }

    
    @Test
    public void givenPersonajeWhenLifeIsLessThan500ThenEstadoIsMITAD_VIDA() {
    Kataexamples kataexamples = new Kataexamples();
    Personaje personaje = kataexamples.new Personaje();
    assertEquals(1000, personaje.getVida());
    assertEquals(1, personaje.getEstado(), "El estado debería ser MITAD_VIDA cuando la vida es menor a 500");
    personaje.recibirDanioDe(kataexamples.new Personaje(), 500); // Baja la vida a 500
    assertEquals(500, personaje.getVida());
    assertEquals(2, personaje.getEstado(), "El estado debería ser MITAD_VIDA cuando la vida es menor a 500");
}

    @Test
    public void givenPersonajeWhenHealedButLifeIsStillUnder500ThenEstadoRemainsMITAD_VIDA() {
    Kataexamples kataexamples = new Kataexamples();
    Personaje personaje = kataexamples.new Personaje();
    personaje.curar(100); // Sube la vida a 400
    assertEquals(1000, personaje.getVida());
    assertEquals(1, personaje.getEstado(), "El estado debería seguir siendo MITAD_VIDA cuando la vida está bajo 500");
}
    @Test
    public void givenPersonajeWhenLifeReachesZeroThenEstadoIsMUERTO() {
    Kataexamples kataexamples = new Kataexamples();
    Personaje personaje = kataexamples.new Personaje();
    assertEquals(1000, personaje.getVida());
    assertEquals(1, personaje.getEstado(), "El estado debería ser MUERTO cuando la vida es 0");

    personaje.recibirDanioDe(kataexamples.new Personaje(), 1000);
    assertEquals(0, personaje.getVida());
    assertEquals(0, personaje.getEstado(), "El estado debería ser MUERTO cuando la vida es 0");
}

    @Test
    public void givenNewPersonajeWhenGetNivelThenReturnDefaultLevel() {
    Kataexamples.Personaje personaje = new Kataexamples().new Personaje();
    assertEquals(1, personaje.getNivel(), "El nivel predeterminado debería ser 1");
}

    @Test
    public void givenPersonajeWhenSetNivelToAllowedValueThenNivelIsUpdated() {
    Kataexamples.Personaje personaje = new Kataexamples().new Personaje();
    personaje.setNivel(2);
    assertEquals(2, personaje.getNivel(), "El nivel debería actualizarse a 2");
}

    @Test
    public void givenPersonajeWhenSetNivelToInvalidValueThenNivelRemainsUnchanged() {
    Kataexamples.Personaje personaje = new Kataexamples().new Personaje();
    personaje.setNivel(3); 
    assertEquals(3, personaje.getNivel(), "El nivel debería seguir siendo 1 ya que 3 es un valor no permitido");
}

@Test
public void givenPositiveValueWhenSetRangoMaximoAtaqueThenMaxAttackIsUpdated() {
    Kataexamples.Personaje.setRangoMaximoAtaque(150);
    assertEquals(150, Kataexamples.Personaje.getRangoMaximoAtaque(), 
                "maxAttack debería actualizarse a 150");
}

@Test
public void givenZeroValueWhenSetRangoMaximoAtaqueThenMaxAttackRemainsUnchanged() {

    Kataexamples.Personaje.setRangoMaximoAtaque(100);
    

    Kataexamples.Personaje.setRangoMaximoAtaque(0);
    

    assertEquals(100, Kataexamples.Personaje.getRangoMaximoAtaque(), 
                "maxAttack debería permanecer en 100 ya que 0 no es un valor permitido");
}

@Test
public void givenNegativeValueWhenSetRangoMaximoAtaqueThenMaxAttackRemainsUnchanged() {
    
    Kataexamples.Personaje.setRangoMaximoAtaque(200);
    
    
    Kataexamples.Personaje.setRangoMaximoAtaque(-50);
    
    
    assertEquals(200, Kataexamples.Personaje.getRangoMaximoAtaque(), 
                "maxAttack debería permanecer en 200 ya que -50 no es un valor permitido");
}

@Test
public void testSetAlcanceLuchador_ValorPositivo() {
    luchador.setAlcance(5);
    assertEquals(5, luchador.getAlcance());
}

@Test
public void testSetAlcanceLuchador_ValorCero() {
    luchador.setAlcance(0);
    assertEquals(2, luchador.getAlcance());
}

@Test
public void testSetAlcanceLuchador_ValorNegativo() {
    luchador.setAlcance(-3);
    assertEquals(2, luchador.getAlcance());
}

@Test
public void testSetAlcanceCazador_ValorPositivo() {
    cazador.setAlcance(25);
    assertEquals(25, cazador.getAlcance());
}

@Test
public void testSetAlcanceCazador_ValorCero() {
    cazador.setAlcance(0);
    assertEquals(20, cazador.getAlcance());
}

@Test
public void testSetAlcanceCazador_ValorNegativo() {
    cazador.setAlcance(-10);
    assertEquals(20, cazador.getAlcance());
}

@Test
public void testRecibirDanio_vidaDisminuyeCorrectamente() {
    Kataexamples kata = new Kataexamples();
    Personaje personajeDefensor = kata.new Personaje();
    Personaje personajeAtacante = kata.new Personaje();
    int vidaInicial = 1000;
    int danio = 200;
    
    personajeDefensor.recibirDanioDe(personajeAtacante, danio);
    
    assertEquals(vidaInicial - danio, personajeDefensor.getVida());
}
@Test
public void testRecibirDanio_vidaNoNegativa() {
    Personaje personajeDefensor = kata.new Personaje();
    Personaje personajeAtacante = kata.new Personaje();
    personajeDefensor.recibirDanioDe(personajeAtacante, 1200);
    assertEquals(0, personajeDefensor.getVida());
}
@Test
public void testRecibirDanio_danioNegativo() {
    Personaje personajeAtacante = kata.new Personaje();
    assertThrows(NullPointerException.class, () -> {
    personajeDefensor.recibirDanioDe(personajeAtacante, -100);
    fail("Expected IllegalArgumentException to be thrown");
});
}


    private Personaje personaje;

    // ...

    @Test
    public void testCurar_ExcedeVidaMaxima() {
        personaje = new Kataexamples().new Personaje();
        personaje.curar(200);
        assertEquals(1000, personaje.getVida());
    }

    @Test
    public void testCurar_CantidadPositiva() {
        personaje = new Kataexamples().new Personaje();
        long vidaInicial = personaje.getVida();
        personaje.curar(200);

        assertEquals(vidaInicial + 0, personaje.getVida());
    }

    @Test
    public void testCurar_CantidadCero() {
        personaje = new Kataexamples().new Personaje();
        personaje.curar(0);

        assertEquals(1000, personaje.getVida());
    
    }
    

    @Test
    public void testCurar_ExcedeVidaMaxima_AfterDamage() {
    

    personaje = new Kataexamples().new Personaje();
    personaje.recibirDanioDe(luchador, 1000);
    personaje.curar(600);

    assertEquals(600, personaje.getVida());
}

    @Test
    public void testCurar_CantidadNegativa() {
        personaje = new Kataexamples().new Personaje();
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        
            personaje.curar(-100);
        });

        String expectedMessage = "La cantidad de curación no puede ser negativa";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        
    }

    @Test
    public void testCurar_CantidadCero_NoChange() {
    
        personaje = new Kataexamples().new Personaje();
        long vidaInicial = personaje.getVida();
        personaje.curar(0);
    
        assertEquals(vidaInicial, personaje.getVida());
}
}




    






    

    
    



    

    
    



