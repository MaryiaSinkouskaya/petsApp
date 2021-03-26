package com.leverx.app.config.odata.edm.mapper;

import com.leverx.app.config.odata.edm.CatEdm;
import com.leverx.app.config.odata.edm.DogEdm;
import com.leverx.app.config.odata.edm.PetEdm;
import com.leverx.app.config.odata.edm.UserEdm;
import com.leverx.app.entity.cat.Cat;
import com.leverx.app.entity.dog.Dog;
import com.leverx.app.entity.pet.Pet;
import com.leverx.app.entity.user.User;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class EdmMapper {

    public static CatEdm convertCat(Cat cat) {
        return new CatEdm(cat.getId(), cat.getName(), convertUser(cat.getUser()), cat.isClippedClaws());
    }

    public static DogEdm convertDog(Dog dog) {
        return new DogEdm(dog.getId(), dog.getName(), convertUser(dog.getUser()), dog.getPawColour());
    }

    public static UserEdm convertUser(User user) {
        return new UserEdm(user.getId(), user.getName(), user.getPassword(), convertPets(user.getPets()));
    }

    public static PetEdm convertPet(Pet pet) {
        return new PetEdm(pet.getId(), pet.getName(), new UserEdm());
    }

    public static List<PetEdm> convertPets(List<Pet> pets) {
        return pets
                .stream()
                .map(EdmMapper::convertPet)
                .collect(Collectors.toList());
    }

    public static List<CatEdm> convertCats(List<Cat> cats) {
        return cats
                .stream()
                .map(EdmMapper::convertCat)
                .collect(Collectors.toList());
    }

    public static List<DogEdm> convertDogs(List<Dog> dogs) {
        return dogs
                .stream()
                .map(EdmMapper::convertDog)
                .collect(Collectors.toList());
    }

    public static List<UserEdm> convertUsers(List<User> users) {
        return users
                .stream()
                .map(EdmMapper::convertUser)
                .collect(Collectors.toList());
    }
}
