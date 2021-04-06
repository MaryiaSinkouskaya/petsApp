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

import static com.leverx.app.entity.dog.enums.PawColour.valueOf;
import static java.util.Objects.isNull;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class EdmMapper {

    public static CatEdm convertCat(Cat cat) {
        if (isNull(cat)) {
            throw new IllegalArgumentException("Cat entity is null");
        }
        User user = cat.getUser();
        long id = isNull(user) ? 0L : user.getId();
        return CatEdm.catEdmBuilder()
                .id(cat.getId())
                .clippedClaws(cat.isClippedClaws())
                .name(cat.getName())
                .userId(id)
                .build();
    }

    public static Cat convertCat(CatEdm cat) {
        if (isNull(cat)) {
            throw new IllegalArgumentException("Cat EDM is null");
        }
        if (isNull(cat.getUserId())) {
            return Cat.catBuilder()
                    .id(cat.getId())
                    .clippedClaws(cat.getClippedClaws())
                    .name(cat.getName())
                    .build();
        }
        User user = User.builder()
                .id(cat.getUserId())
                .build();
        return Cat.catBuilder()
                .id(cat.getId())
                .clippedClaws(cat.getClippedClaws())
                .name(cat.getName())
                .user(user)
                .build();
    }

    public static DogEdm convertDog(Dog dog) {
        if (isNull(dog)) {
            throw new IllegalArgumentException("Dog entity is null");
        }
        String pawColour = dog.getPawColour().name();
        User user = dog.getUser();
        long id = isNull(user) ? 0L : user.getId();
        return DogEdm.dogEdmBuilder()
                .id(dog.getId())
                .name(dog.getName())
                .pawColour(pawColour)
                .userId(id)
                .build();
    }

    public static Dog convertDog(DogEdm dog) {
        if (isNull(dog)) {
            throw new IllegalArgumentException("Dog EDM is null");
        }
        PawColour pawColour = valueOf(dog.getPawColour());
        if (isNull(dog.getUserId())) {
            return Dog.dogBuilder()
                    .pawColour(pawColour)
                    .name(dog.getName())
                    .build();
        }
        User user = User.builder()
                .id(dog.getUserId())
                .build();
        return Dog.dogBuilder()
                .pawColour(pawColour)
                .name(dog.getName())
                .user(user)
                .build();
    }

    public static UserEdm convertUser(User user) {
        if (isNull(user)) {
            throw new IllegalArgumentException("User entity is null");
        }
        if (isNull(user.getPets())) {
            return UserEdm.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .password(user.getPassword())
                    .build();
        }
        return UserEdm.builder()
                .id(user.getId())
                .name(user.getName())
                .password(user.getPassword())
                .pets(convertPets(user.getPets()))
                .build();
    }

    public static User convertUser(UserEdm user) {
        if (isNull(user)) {
            throw new IllegalArgumentException("User EDM is null");
        }
        if (isNull(user.getPets())) {
            return User.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .password(user.getPassword())
                    .build();
        }
        return User.builder()
                .id(user.getId())
                .name(user.getName())
                .password(user.getPassword())
                .pets(convertPetsEdm(user.getPets()))
                .build();
    }

    public static PetEdm convertPet(Pet pet) {
        if (isNull(pet)) {
            throw new IllegalArgumentException("Pet entity is null");
        }
        return PetEdm.builder()
                .id(pet.getId())
                .name(pet.getName())
                .build();
    }

    public static Pet convertPet(PetEdm pet) {
        if (isNull(pet)) {
            throw new IllegalArgumentException("Pet EDM is null");
        }
        return Pet.builder()
                .id(pet.getId())
                .name(pet.getName())
                .build();
    }

    public static List<PetEdm> convertPets(List<Pet> pets) {
        return pets
                .stream()
                .map(EdmMapper::convertPet)
                .collect(Collectors.toList());
    }

    public static List<Pet> convertPetsEdm(List<PetEdm> pets) {
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