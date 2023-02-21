package es.eukariotas.giame.persistence.data.apiclient

import es.eukariotas.giame.persistence.data.model.PartyModel
import es.eukariotas.giame.persistence.data.model.TurnModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PartyApiClient {

    /**
     * Función que se usará para crear una partida
     */
    @GET("/partida/create")
    suspend fun createParty():Response<PartyModel>
    /**
     * Función que se usará para obtener el turno actual de una partida
     */
    @GET("partida/turnos/{idPartida}")
    suspend fun getTurn(@Path("idPartida") idPartida: Int): Response<TurnModel>
    /**
     * Función que se usará para unirse a una partida
     */
    @GET("partida/join/{id_partida}")
    suspend fun joinParty(@Path("id_partida") id_partida: Int): Response<PartyModel>
    /**
     * Función que se usará para obtener una partida
     */
    @GET("partida/{idPartida}")
    suspend fun getParty(@Path("idPartida") idPartida: Int): Response<PartyModel>
    /**
     * Función que se usará para obtener las partidas abiertas
     */
    @GET("partida/open")
    suspend fun getOpenParties(): Response<List<PartyModel>>
}