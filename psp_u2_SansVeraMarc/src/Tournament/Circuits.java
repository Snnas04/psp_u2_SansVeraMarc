package Tournament;

import java.util.ArrayList;
import java.util.List;

public class Circuits {
    private final String name;
    private final int distance;
    List<Circuits> circuits;

    public Circuits(String name, int distance) {
        this.name = name;
        this.distance = distance;
    }

    private void createCircuits() {
        circuits = new ArrayList<>();
        circuits.add(new Circuits("Portimao", 1000));
        circuits.add(new Circuits("Estoril", 1200));
        circuits.add(new Circuits("Algarve", 1100));
        circuits.add(new Circuits("Alentejo", 800));
        circuits.add(new Circuits("Lisboa", 550));
        circuits.add(new Circuits("Porto", 1050));
        circuits.add(new Circuits("Braga", 720));
        circuits.add(new Circuits("Coimbra", 950));
        circuits.add(new Circuits("Aveiro", 600));
        circuits.add(new Circuits("Faro", 850));
    }
}
