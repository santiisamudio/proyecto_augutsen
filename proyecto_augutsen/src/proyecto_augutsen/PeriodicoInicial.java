package proyecto_augutsen;

public class PeriodicoInicial {
    private boolean[] partes;

    public PeriodicoInicial() {
        partes = new boolean[4];
        for(int i = 0; i < 4; i++) {
            partes[i] = false;
        }
    }

    public boolean estaCompleto() {
        return partes[0] && partes[1] && partes[2] && partes[3];
    }

    public boolean estaVacio() {
        return !partes[0] && !partes[1] && !partes[2] && !partes[3];
    }

    // Método privado que mapea id a índice y actualiza el estado
    private void setParte(int id, boolean valor) {
        switch(id) {
            case 0: partes[0] = valor; break;
            case 1: partes[1] = valor; break;
            case 2: partes[2] = valor; break;
            case 4: partes[3] = valor; break;  // id 4 mapea al índice 3
            default:
                System.out.println("ID no válido: " + id);
                break;
        }
        if(valor) {
            System.out.println("agregado " + id);
        } else {
            System.out.println("eliminado " + id);
        }
    }

    public void eliminarMapa(int id) {
        setParte(id, false);
    }

    public void agregarMapa(int id, double x, double y, float angulo) {
        switch(id) {
            case 0:
                if ((x > 0.1674) && (x < 0.5) && (y > 0.1241) && (y < 0.5)) {
                    if (angulo >= 90 && angulo < 180) {
                        System.out.println("Ángulo: " + angulo);
                        setParte(id, true);
                    }
                }
                break;
            case 1:
                if ((x > 0.5) && (x < 0.8330) && (y > 0.1241) && (y < 0.5)) {
                    if (angulo >= 180 && angulo < 270) {
                        System.out.println("Ángulo: " + angulo);
                        setParte(id, true);
                    }
                }
                break;
            case 2:
                if ((x > 0.1674) && (x < 0.5) && (y > 0.5) && (y < 0.8878)) {
                    if (angulo >= 0 && angulo < 90) {
                        System.out.println("Ángulo: " + angulo);
                        setParte(id, true);
                    }
                }
                break;
            case 4:
                if ((x > 0.5) && (x < 0.8330) && (y > 0.5) && (y < 0.8878)) {
                    if (angulo >= 270 && angulo < 360) {
                        System.out.println("Ángulo: " + angulo);
                        setParte(id, true);
                    }
                }
                break;
            default:
                System.out.println("ID no válido: " + id);
                break;
        }
    }
}
