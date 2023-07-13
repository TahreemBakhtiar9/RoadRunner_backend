package roadrunner.roadrunner;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.print.attribute.standard.Media;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import roadrunner.roadrunner.Cars.Cars;
import roadrunner.roadrunner.Cars.CarsCon;
import roadrunner.roadrunner.Cars.CarsRepo;
import roadrunner.roadrunner.UserInfo.UserInfo;
import roadrunner.roadrunner.UserInfo.UserInfoCon;
import roadrunner.roadrunner.UserInfo.UserInfoRepo;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class RoadrunnerApplicationTests {
	private MockMvc mvc, mvc1;
	@InjectMocks
	private CarsCon carsController;
	@InjectMocks
	private UserInfoCon userinfoController;

	@Mock
	CarsRepo carsRepo;
	@Mock
	UserInfoRepo userinfoRepo;

	private JacksonTester<Cars> jsonCars;
	private JacksonTester<UserInfo> jsonUserInfo;

	@Autowired
	private WebApplicationContext context;

	@BeforeEach
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void getAllCars() throws Exception {
		Cars car1 = new Cars(1L, "Alto", "this is a very bad car", "Your shouldnt buy this car","www.img.verybad.com",20000.0);
		Cars car2 = new Cars(2L, "Civic", "this is a very goot car", "Your should buy this car","www.img.verygood.com",70000.0);
		Cars car3 = new Cars(3L, "Hilux", "this is an okay car", "Your can buy this car","www.img.okaycar.com",40000.0);

		ArrayList<Cars> cars = new ArrayList<>();
		cars.add(car1);
		cars.add(car2);
		cars.add(car3);
		when(carsRepo.findAll()).thenReturn(cars);
		mvc.perform(get("/roadrunner/cars/get")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void addCars() throws Exception {
		Cars car1 = new Cars(1L, "Ferrari", "Bestest car in town", "You should buy it if you have money. hehe", "www.bestestcars.com", 8000000.0);
		Cars car2 = new Cars(2L, "Tesla", "Car for Rich People", "Dont buy it if youre poor", "www.notforpoorpeople.com", 10000000.0);
		Cars car3 = new Cars(3L, "Foxy", "Puranay zamanay ki car", "Gaarian collect krne ka shok hai tw buy kren", "www.oldcars.com", 70000.0);
		ArrayList<Cars> cars = new ArrayList<>();
		cars.add(car1);
		cars.add(car2);
		cars.add(car3);
		
		mvc.perform(post("/roadrunner/cars/post")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonCars.write(car1).getJson()))
				.andExpect(status().isOk());
	}

	@Test
	public void getCarsByIds() throws Exception {
		Cars car1 = new Cars(1L, "Alto", "this is a very bad car", "Your shouldnt buy this car","www.img.verybad.com",20000.0);

		when(carsRepo.findById(1L)).thenReturn(car1.getCars());
        mvc.perform(get("/roadrunner/cars/get/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

	}

	

}
