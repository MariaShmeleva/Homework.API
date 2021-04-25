package tinkoff.qa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import retrofit2.Response;
import tinkoff.qa.clients.petstore.PetStore;
import tinkoff.qa.clients.petstore.PetStoreService;
import tinkoff.qa.models.Category;
import tinkoff.qa.models.Pet;
import org.junit.jupiter.api.Test;
import tinkoff.qa.models.PetDelete;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestAPI {
    private static PetStore petStore;

    @BeforeAll
    public static void beforeAll() {
        petStore = new PetStoreService().getPetStore();
    }

    @Test
    public void testPost200() throws IOException {
        Pet myPet = new Pet();
        myPet.setId(1);
        myPet.setName("Kuzya");
        Category category = new Category();
        category.setName("Кот");
        myPet.setCategory(category);
        myPet.setStatus("avaliable");

        Response<Pet> petCreateR = petStore.createPet(myPet).execute(); // Тут можно было не Response<Pet> а Pet, но я хотела потом проверить и тело, и код
        Assertions.assertEquals(200, petCreateR.code());
        Assertions.assertEquals(myPet, petCreateR.body());
        Assertions.assertEquals(200, petStore.getPetByID(1).execute().code());
    }
    @Test
    public void testPost200_Exception() {
        Pet myPet1 = new Pet();
        myPet1.setId(-2);
        myPet1.setName("Alisa");
        Category category = new Category();
        category.setName("Кот");
        myPet1.setCategory(category);
        myPet1.setStatus("avaliable");

        assertThrows(com.fasterxml.jackson.databind.JsonMappingException.class,
                () -> petStore.createPet(myPet1).execute()); // С отрицательным id некорректное поведение сервера, мне кажется, что он должен ошибку выдавать :((

    }

    @Test
    public void testGet200() throws IOException {
        Pet myPet = new Pet();
        myPet.setId(10);
        myPet.setName("Cat");
        Category category = new Category();
        category.setName("Кот");
        myPet.setCategory(category);
        myPet.setStatus("avaliable");

        Pet petCreate = petStore.createPet(myPet).execute().body();
        Assertions.assertEquals(myPet, petCreate);
        Assertions.assertEquals(200, petStore.getPetByID(10).execute().code());
        Assertions.assertEquals(petCreate, petStore.getPetByID(10).execute().body());
    }
    @Test
    public void testGet404() throws IOException {
        int petId = 12763;
        petStore.deletePetById(petId);
        Assertions.assertEquals(404, petStore.getPetByID(petId).execute().code());
    }

    @Test
    public void testPut200() throws IOException {
        Pet myPet1 = new Pet();
        myPet1.setId(2);
        myPet1.setName("Alisa");
        Category category = new Category();
        category.setName("Кот");
        myPet1.setCategory(category);
        myPet1.setStatus("avaliable");

        Pet myPet2 = new Pet();
        myPet2.setId(2);
        myPet2.setName("Masha");
        Category category1 = new Category();
        category1.setName("Собака");
        myPet2.setCategory(category1);
        myPet2.setStatus("avaliable");

        Pet petCreate1 = petStore.createPet(myPet1).execute().body();
        Assertions.assertEquals(myPet1, petCreate1); //Не обязательно, так как это проверяется в testPost200, но как я поняла по лекции это все равно лучше проверять
        Assertions.assertEquals(petCreate1, petStore.getPetByID(2).execute().body());
        petCreate1 = petStore.updatePet(myPet2).execute().body();
        Assertions.assertEquals(myPet2, petCreate1);
        Assertions.assertEquals(petCreate1, petStore.getPetByID(2).execute().body());

    }

    @Test
    public void testPut404() throws IOException {
        Pet myPet1 = new Pet();
        myPet1.setId(2);
        myPet1.setName("Alisa");
        Category category = new Category();
        category.setName("Кот");
        myPet1.setCategory(category);
        myPet1.setStatus("avaliable");

        Pet myPet2 = new Pet();
        myPet2.setId(2);
        myPet2.setName("Masha");
        Category category1 = new Category();
        category1.setName("Собака");
        myPet2.setCategory(category1);
        myPet2.setStatus("avaliable");

        Assertions.assertEquals(myPet1, petStore.createPet(myPet1).execute().body());
        Assertions.assertEquals(myPet1, petStore.getPetByID(2).execute().body());
        petStore.deletePetById(2);
        Assertions.assertEquals(404, petStore.updatePet(myPet2).execute().code());
    }

    @Test
    public void testDelete200() throws IOException {
        Pet myPet1 = new Pet();
        myPet1.setId(10);
        myPet1.setName("Tomi");
        Category category = new Category();
        category.setName("Собака");
        myPet1.setCategory(category);
        myPet1.setStatus("avaliable");

        Assertions.assertEquals(myPet1, petStore.createPet(myPet1).execute().body());
        Assertions.assertEquals(200, petStore.getPetByID(10).execute().code());
        Assertions.assertEquals(200, petStore.deletePetById(10).execute().code());
        Assertions.assertEquals(404, petStore.getPetByID(10).execute().code());
    }

    @Test
    public void testDelete404() throws IOException {

        int petId = 100;
        petStore.deletePetById(petId);
        Assertions.assertEquals(404, petStore.deletePetById(petId).execute().code());
        Assertions.assertEquals(404, petStore.getPetByID(petId).execute().code());
    }

    // Сначала сделала проверки DELETE с Call <ResponseBody>, потом вроде поняла, как сделать их с Call <PetDelete>, но не уверена, что правильно, поэтому оставила два варианта)

    @Test
    public void testDelete404R() throws IOException {

        int petId = 100;
        petStore.deletePetById1(petId);
        Assertions.assertNull(petStore.deletePetById1(petId).execute().body());
        Assertions.assertEquals(404, petStore.getPetByID(petId).execute().code());
    }

    @Test
    public void testDelete200R() throws IOException {

        PetDelete petDelete = new PetDelete();
        petDelete.setType("unknown");
        petDelete.setMessage("19");
        petDelete.setCode(200);

        Pet myPet1 = new Pet();
        myPet1.setId(19);
        myPet1.setName("Tomi");
        Category category = new Category();
        category.setName("Собака");
        myPet1.setCategory(category);
        myPet1.setStatus("avaliable");

        Assertions.assertEquals(myPet1, petStore.createPet(myPet1).execute().body());
        Assertions.assertEquals(200, petStore.getPetByID(19).execute().code());
        Assertions.assertEquals(petDelete, petStore.deletePetById1(19).execute().body());
        Assertions.assertEquals(404, petStore.getPetByID(19).execute().code());
    }


}
