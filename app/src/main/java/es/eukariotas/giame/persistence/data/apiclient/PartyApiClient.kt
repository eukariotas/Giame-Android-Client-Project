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
    @GET("/partida/create/{tipo}")
    suspend fun createParty():Response<PartyModel>
    /**
     * Función que se usará para obtener el turno actual de una partida
     */
    @GET("partida/turnos/{party_id}")
    suspend fun getTurn(@Path("party_id") idPartida: Int): Response<TurnModel>
    /**
     * Función que se usará para unirse a una partida
     */
    @GET("partida/join/{party_id}")
    suspend fun joinParty(@Path("party_id") id_partida: Int): Response<PartyModel>
    /**
     * Función que se usará para obtener una partida
     */
    @GET("partida/{party_id}")
    suspend fun getParty(@Path("party_id") idPartida: Int): Response<PartyModel>
    /**
     * Función que se usará para obtener las partidas abiertas
     */
    @GET("partida/open")
    suspend fun getOpenParties(): Response<List<PartyModel>>

    /**
     * Función que se usará para obtener el estado de las partidas
     */
    @GET("partida/open")
    suspend fun getPartiesByState(): Response<List<PartyModel>>
}