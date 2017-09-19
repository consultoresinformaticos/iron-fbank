package py.edu.una.ironbank.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import py.edu.una.ironbank.entity.House;

@Service
public class HouseService {

	private static final String HOUSE_LIST = "http://localhost:8088/house";
	private static final String HOUSE_ADD = "http://localhost:8088/house/add";

	public List<House> getHouse() {
		List<House> forObject = (List<House>) new RestTemplate().getForObject(HOUSE_LIST, List.class);
		return forObject;
	}

	public House save(House house) {
		try {
			House forObject = (House) new RestTemplate().postForObject(HOUSE_ADD, house, House.class);
			return forObject;
		} catch (Exception e) {
			return null;
		}
		
	}

}
