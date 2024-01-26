package com.saria.solarcell;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saria.solarcell.solarcomponents.SolarCellController;
import com.saria.solarcell.solarcomponents.SolarEnergyCalculatorService;
import com.saria.solarcell.solarcomponents.SolarEnergyEntity;
import com.saria.solarcell.solarcomponents.SolarPanelRepository;
import com.saria.solarcell.solarcomponents.SolarPanelService;

//@ExtendWith(MockitoExtension.class)
@WebMvcTest(SolarCellController.class)
class SolarcellApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper = new ObjectMapper();

	@MockBean
	private SolarPanelService service;
	
	@MockBean
	private SolarEnergyCalculatorService calculatorService;
	
	@Mock
	private SolarCellController solarCellController;
	
	@Mock
	private SolarPanelRepository solarPanelRepository; 
	
	
	
	@BeforeEach
    public void setup() {
    	// MockMvc standalone approach
    	mockMvc = MockMvcBuilders.standaloneSetup(solarCellController)
                .build();	
    	JacksonTester.initFields(this, new ObjectMapper());
    }
    
    private JacksonTester<SolarEnergyEntity> jsonSolarPanel;
    
    @Test
    void getPanelById_shouldReturnPanelWithNetEnergy() throws Exception {
        // Given
        int id = 31;

        // When
        SolarEnergyEntity energyEntity = Mockito.mock(SolarEnergyEntity.class);
        List<SolarEnergyEntity> mockedPanelEntities = new ArrayList<>();
        mockedPanelEntities.add(energyEntity);
        Mockito.when(solarPanelRepository.findById(31)).thenReturn(mockedPanelEntities);
        
        List<SolarEnergyEntity> panel = solarPanelRepository.findById(id);
     // when
        MockHttpServletResponse response = mockMvc.perform(
                get("/solar-energy/panels/id/31")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
     // then
		LocalDateTime ldt = LocalDateTime.parse("2024-01-24T11:36:41");
		SolarEnergyEntity solarEnergyEntity = new SolarEnergyEntity(1109.313593970407D, 0.1992195009045623D, 23.305933250857358D,
				0.9381044918901597D, 0.04583521173994493D, 0.015672274005185144D, 0.010630031986950547D, ldt,
				4778.991913222558D, 4483.193780500829D);
        solarEnergyEntity.setId(31);
        

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
        		jsonSolarPanel.write(solarEnergyEntity).getJson() );
        
        mockMvc.perform(get("/solar-energy/panels/id/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonResultMatches(Collections.singletonList(panel)))
                .andExpect(jsonPath("$[0].netEnergy").value(4483.193780500829)); 

    }


    private ResultMatcher jsonResultMatches(Object expectedObject) throws Exception {
        return content().json(objectMapper.writeValueAsString(expectedObject));
    }
}
