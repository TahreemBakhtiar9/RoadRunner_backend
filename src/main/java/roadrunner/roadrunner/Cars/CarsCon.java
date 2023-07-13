package roadrunner.roadrunner.Cars;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/roadrunner")
public class CarsCon {
@Autowired
private CarsRepo carsRepo;

@GetMapping("/cars/get")
public List<Cars> getAllCars(){
    return carsRepo.findAll();
}
@GetMapping("/cars/get/{id}")
public ResponseEntity<Cars> getCarsById(@PathVariable Long id){
    Optional <Cars> optionalCars = carsRepo.findById(id);
    if(optionalCars.isPresent()){
        Cars cars = optionalCars.get();
        return ResponseEntity.ok(cars);
    }
    else {
        return ResponseEntity.notFound().build();
    }
}

@PostMapping("/cars/post")
public void addCars(@RequestBody Cars cars){
    carsRepo.save(cars);
}
    
}
