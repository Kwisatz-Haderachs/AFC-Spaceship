package com.example.Space;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.servlet.http.Cookie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc()
class SpaceApplicationTests {

	@Autowired
	MockMvc mvc;
	@Test
	public void getSpaceship() throws Exception {
		this.mvc.perform(get("/spaceship/")
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("Voyager")));
	}
	@Test
	public void getCrew() throws Exception {
		this.mvc.perform(get("/spaceship/crew")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name", is("John")))
				.andExpect(jsonPath("$[10].name", is("Jessica")));

	}

	@Test
	public void getCrewByID() throws Exception {
		this.mvc.perform(get("/spaceship/crew/1")
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("name", is("John")));
		this.mvc.perform(get("/spaceship/crew/11")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
				.andExpect(jsonPath("name", is("Jessica")));
	}

	ObjectMapper objectMapper = new ObjectMapper();
	@Test
	public void postCrewMembers() throws Exception{
		List<CrewMember> crewMembers = new ArrayList<>();
		CrewMember c = new CrewMember(12, "Johnathan");
		CrewMember d = new CrewMember(13, "Jean");
		crewMembers.add(c);
		crewMembers.add(d);
		HashMap<String, List<CrewMember>> newCrew = new HashMap<>(){  // 2
			{
				put("newCrew", crewMembers);
			}
		};

		String json = objectMapper.writeValueAsString(newCrew);

		MockHttpServletRequestBuilder request = post("/spaceship/crew/new")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json);

		this.mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[11].name", is("Johnathan")))
				.andExpect(jsonPath("$[11].id", is(12)))
				.andExpect(jsonPath("$[12].name", is("Jean")))
				.andExpect(jsonPath("$[12].id", is(13)));
	}

	@Test
	public void setCrewRole() throws Exception {
		this.mvc.perform(get("/spaceship/crew/1/Engineer")
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("name", is("John")))
				.andExpect(jsonPath("role", is(Role.ENGINEER.toString())));
		this.mvc.perform(get("/spaceship/crew/11/Pilot")
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("name", is("Jessica")))
				.andExpect(jsonPath("role", is(Role.PILOT.toString())));
	}


	@Test
	public void testCookie() throws Exception{
		this.mvc.perform(get("/spaceship/current").cookie(new Cookie("current", "12")))
				.andExpect(status().isOk())
				.andExpect(content().string("Your current spaceship has the id of 12"));
		this.mvc.perform(get("/spaceship/current"))
				.andExpect(status().isOk())
				.andExpect(content().string("<empty>"));
	}

	@Test
	public void exceedCapacity() throws Exception{
		List<CrewMember> crewMembers = new ArrayList<>();
		CrewMember a = new CrewMember(12, "Johnathan");
		CrewMember b = new CrewMember(13, "Jean");
		CrewMember c = new CrewMember(14, "Jo");
		CrewMember d = new CrewMember(15, "Jay");
		CrewMember e = new CrewMember(16,"Bo");
		crewMembers.add(a);
		crewMembers.add(b);
		crewMembers.add(c);
		crewMembers.add(d);
		crewMembers.add(e);
		HashMap<String, List<CrewMember>> newCrew = new HashMap<>(){  // 2
			{
				put("newCrew", crewMembers);
			}
		};

		String json = objectMapper.writeValueAsString(newCrew);

		MockHttpServletRequestBuilder request = post("/spaceship/crew/new")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json);

		this.mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[13].name", is("Jo")))
				.andExpect(jsonPath("$[13].id", is(14)))
				.andExpect(jsonPath("$[14].name", is("Jay")))
				.andExpect(jsonPath("$[14].id", is(15)));

		/*
			Note: setCrew is being called
		 */

	}

	@Test
	public void upgradeShip() throws Exception{

		HashMap<String, String> upgrades = new HashMap<>(){  // 2
			{
				put("Armor", "88.00");
				put("Weapons", "120.00");
				put("Crew Size", "35");
				put("Shield", "89.00");
				put("TypeOf", "Destroyer");
			}
		};

		String json = objectMapper.writeValueAsString(upgrades);
		MockHttpServletRequestBuilder request = post("/spaceship/upgrade")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json);

		this.mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.armorRating", is(88.00)))
				.andExpect(jsonPath("$.weaponsRating", is(120.00)))
				.andExpect(jsonPath("$.crewSize", is(35)))
				.andExpect(jsonPath("$.shieldRating", is(89.00)))
				.andExpect(jsonPath("$.type", is(Type.DESTROYER.toString())));
	}


}
