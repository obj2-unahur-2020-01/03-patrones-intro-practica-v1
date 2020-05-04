package ar.edu.unahur.obj2.ota.proveedores;

import ar.edu.unahur.obj2.ota.Boleto;
import ar.edu.unahur.obj2.ota.Pasajero;
import ar.edu.unahur.obj2.ota.Vuelo;
import org.joda.time.DateTime;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Amadeus {

    private final Set<Vuelo> vuelos = new HashSet<Vuelo>();

    public Amadeus() {
        vuelos.add(new Vuelo(new DateTime("2019-12-13"), "BUE", "MIA", "AA1000", 10, "Amadeus"));
        vuelos.add(new Vuelo(new DateTime("2019-12-14"), "BUE", "MIA", "AA1043", 10, "Amadeus"));
        vuelos.add(new Vuelo(new DateTime("2019-12-13"), "BUE", "COR", "AA1044", 10, "Amadeus"));
        vuelos.add(new Vuelo(new DateTime("2019-12-15"), "BUE", "SAO", "G31010", 10, "Amadeus"));
        vuelos.add(new Vuelo(new DateTime("2019-12-13"), "BUE", "NYC", "LA900", 10, "Amadeus"));
    }

    public List<Vuelo> executeSearch(DateTime fecha, final String origen, final String destino) {
        return vuelos.stream()
                .filter(v -> v.getFecha().equals(fecha)
                        && v.getOrigen().equals(origen)
                        && v.getDestino().equals(destino)
                        && v.getLugaresDisponibles() > 0)
                .collect(Collectors.toList());
    }


    public Boleto executeBook(Vuelo vuelo, Set<Pasajero> pasajeros) {
        Vuelo vueloAVender = vuelos.stream()
                .filter(v -> vuelo.equals(v))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Vuelo inexistente"));

        return new Boleto(vueloAVender, pasajeros);
    }

}
