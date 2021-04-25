package tinkoff.qa.clients.petstore;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.*;
import tinkoff.qa.models.Pet;
import tinkoff.qa.models.PetDelete;

public interface PetStore {
    @POST("/v2/pet")
    Call <Pet> createPet (@Body Pet pet);

    @GET("/v2/pet/{id}")
    Call <Pet> getPetByID (@Path("id") int id);

    @PUT("/v2/pet")
    Call <Pet> updatePet(@Body Pet pet);

    @DELETE("v2/pet/{id}")
    Call <ResponseBody> deletePetById (@Path("id") int id);

    @DELETE("v2/pet/{id}")
    Call <PetDelete> deletePetById1 (@Path("id") int id);

}
