package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.models.domain.MediosDeComunicacion.Notificador;
import ar.edu.utn.frba.dds.models.domain.MediosDeComunicacion.Whatsapp;
import ar.edu.utn.frba.dds.models.domain.comunidades.Comunidad;

import ar.edu.utn.frba.dds.models.domain.comunidades.Miembro;
import ar.edu.utn.frba.dds.models.domain.comunidades.gradosDeConfianza.Puntaje;
import ar.edu.utn.frba.dds.models.domain.incidentes.AperturaIncidente;
import ar.edu.utn.frba.dds.models.domain.incidentes.Incidente;

import ar.edu.utn.frba.dds.models.domain.incidentes.IncidenteResumido;
import ar.edu.utn.frba.dds.models.domain.localizaciones.Localizacion;
import ar.edu.utn.frba.dds.models.domain.ranking.GeneradorRanking;
import ar.edu.utn.frba.dds.models.domain.ranking.RankingMayorCantidadIncidentes;
import ar.edu.utn.frba.dds.models.domain.ranking.RankingMayorGradoImpactoProblematicas;
import ar.edu.utn.frba.dds.models.domain.ranking.RankingMayorPromedioCierre;
import ar.edu.utn.frba.dds.models.domain.servicios.*;
import ar.edu.utn.frba.dds.models.domain.serviciospublicos.*;

import ar.edu.utn.frba.dds.models.excepciones.TipoEstablecimientoInvalidoExcepcion;
import ar.edu.utn.frba.dds.repositories.*;

import ar.edu.utn.frba.dds.server.App;
import ar.edu.utn.frba.dds.server.utils.cronTasks.ConfigurationTask;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;//

import javax.persistence.EntityTransaction;

import java.util.ListIterator;
import org.jetbrains.annotations.NotNull;



public class MainExample implements WithSimplePersistenceUnit {




}







