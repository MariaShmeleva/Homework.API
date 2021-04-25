package tinkoff.qa.clients.petstore;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class PetStoreService {
    private PetStore petStore;

    public PetStore getPetStore() {
        if(petStore == null) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://petstore.swagger.io/").addConverterFactory(JacksonConverterFactory.create()).build();
        petStore = retrofit.create(PetStore.class);
        return petStore;
        } else return petStore;
    }
}
