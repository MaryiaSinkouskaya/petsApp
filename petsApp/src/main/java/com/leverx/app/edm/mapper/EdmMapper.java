package com.leverx.app.edm.mapper;

import com.leverx.app.edm.cat.CatEdm;
import com.leverx.app.edm.dog.DogEdm;
import com.leverx.app.edm.pet.PetEdm;
import com.leverx.app.edm.user.UserEdm;
import com.leverx.app.entity.cat.Cat;
import com.leverx.app.entity.dog.Dog;
import com.leverx.app.entity.dog.enums.PawColour;
import com.leverx.app.entity.pet.Pet;
import com.leverx.app.entity.user.User;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.leverx.app.entity.dog.enums.PawColour.BLACK;
import static com.leverx.app.entity.dog.enums.PawColour.PINK;
import static java.util.Collections.emptyList;
import static java.util.Objects.isNull;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class EdmMapper {

    public static CatEdm convertCat(Cat cat) {
        if (isNull(cat)) {
            return null;
        }
        return CatEdm.catEdmBuilder()
                .id(cat.getId())
                .clippedClaws(cat.isClippedClaws())
                .name(cat.getName())
                .userId(cat.getUser().getId())
                .build();
    }

    public static Cat convertCat(CatEdm cat) {
        if (isNull(cat)) {
            return null;
        }
        return Cat.catBuilder()
                .id(cat.getId())
                .clippedClaws(cat.getClippedClaws())
                .name(cat.getName())
                .user(User.builder()
                        .id(cat.getUserId())
                        .build())
                .build();
    }

    public static DogEdm convertDog(Dog dog) {
        if (isNull(dog)) {
            return null;
        }
        if (isNull(dog.getUser())) {
            return DogEdm.dogEdmBuilder()
                    .id(dog.getId())
                    .name(dog.getName())
                    .pawColour(dog.getPawColour().getColour())
                    .build();
        }
        return DogEdm.dogEdmBuilder()
                .id(dog.getId())
                .name(dog.getName())
                .pawColour(dog.getPawColour().getColour())
                .userId(dog.getUser().getId())
                .build();
    }

    public static Dog convertDog(DogEdm dog) {
        if (isNull(dog)) {
            return null;
        }
        PawColour pawColour = PINK;
        if (dog.getPawColour().equals(BLACK.getColour())) {
            pawColour = BLACK;
        }
        return Dog.dogBuilder()
                .pawColour(pawColour)
                .name(dog.getName())
                .user(User.builder()
                        .id(dog.getUserId())
                        .build())
                .build();
    }

    public static UserEdm convertUser(User user) {
        if (isNull(user)) {
            return null;
        }
        List<Pet> pets = user.getPets();
        if (isNull(pets)) {
            pets = emptyList();
        }
        return UserEdm.builder()
                .id(user.getId())
                .name(user.getName())
                .password(user.getPassword())
                .pets(convertPets(pets))
                .build();
    }

    public static User convertUser(UserEdm user) {
        if (isNull(user)) {
            return null;
        }
        List<PetEdm> pets = user.getPets();
        if (isNull(pets)) {
            pets = emptyList();
        }
        return User.builder()
                .id(user.getId())
                .name(user.getName())
                .password(user.getPassword())
                .pets(convertPetsEdm(pets))
                .build();
    }

    public static PetEdm convertPet(Pet pet) {
        if (isNull(pet)) {
            return null;
        }
        return PetEdm.builder()
                .id(pet.getId())
                .name(pet.getName())
                .build();
    }

    public static Pet convertPet(PetEdm pet) {
        if (isNull(pet)) {
            return null;
        }
        return Pet.builder()
                .id(pet.getId())
                .name(pet.getName())
                .build();
    }

    public static List<PetEdm> convertPets(List<Pet> pets) {
        if (pets.isEmpty())
            return emptyList();
        return pets
                .stream()
                .map(EdmMapper::convertPet)
                .collect(Collectors.toList());
    }

    public static List<Pet> convertPetsEdm(List<PetEdm> pets) {
        if (pets.isEmpty())
            return emptyList();
        return pets
                .stream()
                .map(EdmMapper::convertPet)
                .collect(Collectors.toList());
    }

    public static List<CatEdm> convertCats(List<Cat> cats) {
        if (cats.isEmpty())
            return emptyList();
        return cats
                .stream()
                .map(EdmMapper::convertCat)
                .collect(Collectors.toList());
    }

    public static List<DogEdm> convertDogs(List<Dog> dogs) {
        if (dogs.isEmpty())
            return emptyList();
        return dogs
                .stream()
                .map(EdmMapper::convertDog)
                .collect(Collectors.toList());
    }

    public static List<UserEdm> convertUsers(List<User> users) {
        if (users.isEmpty())
            return emptyList();
        return users
                .stream()
                .map(EdmMapper::convertUser)
                .collect(Collectors.toList());
    }
}
