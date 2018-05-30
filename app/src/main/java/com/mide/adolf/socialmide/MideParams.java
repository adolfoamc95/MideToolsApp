package com.mide.adolf.socialmide;

import java.util.ArrayList;

public class MideParams {
    private ArrayList<String> listaMedio, listaItinerario, listaDesplazamiento;

    public MideParams(){
        createParams();
    }

    public ArrayList<String> getListaMedio() {
        return listaMedio;
    }

    public ArrayList<String> getListaItinerario() {
        return listaItinerario;
    }

    public ArrayList<String> getListaDesplazamiento() {
        return listaDesplazamiento;
    }

    private void createParams() {
        //ListaMedio

        listaMedio = new ArrayList<>();
        listaMedio.add("Exposición en el itinerario a desprendimientos espontáneos de piedras");
        listaMedio.add("Exposición en el itinerario a desprendimientos espontáneos de nieve o hielo");
        listaMedio.add("Exposición en el itinerario a desprendimientos de piedras provocados por el propio grupo u otro");
        listaMedio.add("Eventualidad de que una caída de un excursionista sobre el propio itinerario le provoque una caída al vacío o un deslizamiento por la pendiente");
        listaMedio.add("Existencia de pasos en que sea necesario el uso de las manos");
        listaMedio.add("Paso de torrentes sin puente");
        listaMedio.add("Paso por glaciares o ciénagas");
        listaMedio.add("Paso probable por neveros o glaciares, independientemente de su inclinación");
        listaMedio.add("Alta probabilidad de que por la noche la temperatura descienda de 0ºC");
        listaMedio.add("Alta probabilidad de que por la noche la temperatura descienda de 5 ºC y la humedad relativa supere el 90%");
        listaMedio.add("Alta probabilidad de que por la noche la temperatura descienda de –10ºC");
        listaMedio.add("Paso por lugares alejados a más de 1 hora de marcha (horario MIDE) de un lugar habitado, un teléfono de socorro o una carretera abierta");
        listaMedio.add("Paso por lugares alejados a más de 3 hora de marcha (horario MIDE) de un lugar habitado, un teléfono de socorro o una carretera abierta");
        listaMedio.add("La diferencia entre la duración del día (en la época considerada) y el horario del recorrido es menor de 3 horas");
        listaMedio.add("En algún tramo del recorrido, la existencia de fenómenos atmosféricos que no se juzguen infrecuentes, aumentaría considerablemente la dificultad del itinerario (niebla, viento, calor extremo, etc...)");
        listaMedio.add("El itinerario, en algún tramo, transcurre fuera de traza de camino y por terreno enmarañado o irregular que dificultaría la localización de personas");
        listaMedio.add("Exposición contrastada a picaduras de serpientes o insectos peligrosos");
        listaMedio.add("En algún tramo del recorrido existe algún otro factor de riesgo, propio de cada zona, que no ha sido tenido en cuenta en el listado anterior");

        // ListaItineraio

        listaItinerario = new ArrayList<>();

        listaItinerario.add("Caminos principales bien delimitados o señalizados con cruces claros con indicación explícita o implícita. Mantenerse sobre el camino no exige esfuerzo de identificación de la traza. Eventualmente seguimiento de una línea marcada por un accidente geográfico inconfundible (una playa, la orilla de un lago...)");
        listaItinerario.add("Existe traza clara de camino sobre el terreno o señalización para la continuidad. Se requiere atención para la continuidad y los cruces de otras trazas pero sin necesidad de una interpretación precisa de los accidentes geográficos. Esta puntuación se aplicaría a la mayoría de senderos señalizados que utilizan en un mismo recorrido distintos tipos de caminos con numerosos cruces: pistas, caminos de herradura, sendas, campo a través de señal a señal (bien emplazadas y mantenidas).");
        listaItinerario.add("Aunque el itinerario se desarrolla por trazas de sendero, líneas marcadas por accidentes geográficos (ríos, fondos de los valles, cornisas, crestas...) o marcas de paso de otras personas, la elección del itinerario adecuado depende del reconocimiento de los accidentes geográficos y de los puntos cardinales.");
        listaItinerario.add("No existe traza sobre el terreno ni seguridad de contar con puntos de referencia en el horizonte. El itinerario depende de la comprensión del terreno y del trazado de rumbos.");
        listaItinerario.add("Los rumbos y/o líneas naturales del itinerario son interrumpidos por obstáculos que hay que bordear.");

        // ListaDesplazamiento

        listaDesplazamiento = new ArrayList<>();

        listaDesplazamiento.add("Carreteras y pistas para vehículos independientemente de su inclinación. Escaleras de piso regular. Playas de arena o grava.");
        listaDesplazamiento.add("Caminos con diversos firmes pero que mantienen la regularidad del piso, no presentan gradas muy altas, y permiten elegir la longitud del paso. Terreno apto para caballerías. Campo a través por terrenos uniformes como landas, taiga y prados no muy inclinados.");
        listaDesplazamiento.add("Marcha por sendas con gradas o escalones irregulares de distinto tamaño, altura, rugosidad e inclinación. Marcha fuera de senda por terrenos irregulares. Cruces de caos de piedras. Marcha por pedreras inestables.");
        listaDesplazamiento.add("Tramos con pasos que requieren el uso de las manos hasta el I Sup.");
        listaDesplazamiento.add("Pasos de escalada de II hasta el III+ de la escala UIAA. Existencia de elementos artificiales para la progresión por tracción (clavijas, cadenas...). La existencia de estos pasos obliga a una mención explícita en el apartado Dificultades técnicas específicas aún en el caso de que el tramo (y por tanto la excursión) fueran valorados 4.");


    }
}
