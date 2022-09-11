/*
package dat3.cars.service;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.dto.MemberRequest;
import dat3.cars.dto.MemberResponse;
import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.repository.CarRepository;
import dat3.cars.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class CarServiceMockWithH2Test {

    public  CarService carService;

    public static CarRepository carRepository;

    @BeforeAll
    public static void setupData(@Autowired CarRepository car_Repository){


        carRepository = car_Repository;
        //carRepository.deleteAll();
        List<Car> cars = List.of(
                new Car("c1", "pw", "m1@a.dk", "aa", "aaa", "aaaa", "aaaa", "1234"),
                new Car("c2", "pw", "mm@a.dk", "bb", "bbb", "bbbb", "bbbb", "1234")
        );
        carRepository.saveAll(cars);
    }

    @BeforeEach
    public void setCarService(){
        carService = new CarService(carRepository);
    }


    @Test
    void addCar() {
        Car c = new Car("m3", "pw", "m3@a.dk", "cc", "ccc", "bbbb", "bbbb", "1234");
        CarRequest request = new CarRequest(c);
        carService.addCar(request);
        assertEquals(3,carRepository.count());
    }


    @Test
    void editCar() throws Exception {
        //Create a member, just as a quick way to get a MemberRequest --> Observe new address for m1
        CarRequest request = new CarRequest(new Car("c1", "pw", "m1@a.dk", "aa", "aaa", "xxxx", "yyyy", "2000"));
        carService.editCar(request,"c1");
        //find m1 and verify that address has been changed
        CarResponse response = carService.findCarById("m1");
        assertEquals("xxxx", response.getStreet());
        assertEquals("yyyy", response.getCity());
        assertEquals("2000", response.getZip());
    }

    @Test
    void getMembers() {
        List<MemberResponse> response = memberService.getMembers();
        assertEquals(2,response.size());
        assertThat(response, containsInAnyOrder(hasProperty("email", is("m1@a.dk")), hasProperty("email", is("mm@a.dk"))));
    }

    @Test
    void findMemberByUsername() throws Exception {
        MemberResponse response = memberService.findMemberByUsername("m1");
        assertEquals("m1@a.dk",response.getEmail());
    }
    @Test
    void findMemberByNotExistingUsername() throws Exception {
        ResponseStatusException ex = Assertions.assertThrows(ResponseStatusException.class,()-> memberService.findMemberByUsername("i-dont-exist"));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
    }

    @Test
    void deleteByUsername() {
        memberRepository.deleteById("m1");
        assertEquals(1,memberRepository.count());
    }

    @Test
    void setRankingForUser() {
    }
}*/
