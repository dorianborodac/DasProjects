package das.java.internship.springproject1;

import das.java.internship.springproject1.dto.MallDTO;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.BodyInserters;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@ExtendWith(SpringExtension.class)
@AutoConfigureWebTestClient(timeout = "PT1M")//30 seconds
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class MallApplicationTests {

    @Test
    void contextLoads() {
    }

    private String serverURL;

    @LocalServerPort
    private int port;

    private final WebTestClient webTestClient;

    @Mock
    private HttpServletRequest request;

    @BeforeAll
    public void setUp(){
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        serverURL = String.format("%s:%s", "localhost", port);

    }
    @Test
    void saveValidMall(){
        MallDTO mallDTO = new MallDTO();
        mallDTO.setName("Mall Moldova");
        mallDTO.setShortName("Mall");

        MallDTO savedMall = this.webTestClient
                .post()
                .uri(serverURL + "/api/service/mall/save")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .body(BodyInserters.fromValue(mallDTO))
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(MallDTO.class)
                .returnResult()
                .getResponseBody();
        assertNotNull(savedMall);

        HttpStatus deleteUni = this.webTestClient
                .delete()
                .uri(serverURL + "/api/service/mall/deleteById/" + savedMall.getId())
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(HttpStatus.class)
                .returnResult()
                .getResponseBody();

        assertEquals(mallDTO.getName(), savedMall.getName());
        assertEquals(mallDTO.getShortName(), savedMall.getShortName());


    }
    @Test
    void getRequest(){
        MallDTO mallDTO = new MallDTO();
        mallDTO.setName("Mall Moldova");
        mallDTO.setShortName("Mall");
        Long id = 1L;

        MallDTO savedMall = this.webTestClient
                .post()
                .uri(serverURL + "/api/service/mall/save")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .body(BodyInserters.fromValue(mallDTO))
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(MallDTO.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(savedMall);

        //act
        MallDTO getUni = this.webTestClient
                .get()
                .uri(serverURL + "/api/service/mall/getById/" + savedMall.getId())
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(MallDTO.class)
                .returnResult()
                .getResponseBody();

        HttpStatus deleteUni = this.webTestClient
                .delete()
                .uri(serverURL + "/api/service/mall/deleteById/" + savedMall.getId())
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(HttpStatus.class)
                .returnResult()
                .getResponseBody();


        assertNotNull(getUni);
        Assertions.assertEquals(savedMall.getId(), getUni.getId());
        Assertions.assertEquals(savedMall.getName(), getUni.getName());
        Assertions.assertEquals(savedMall.getId(), getUni.getId());
//		Assertions.assertEquals(universityDTO, res);
    }

    @Test
    void deleterRequest(){


        MallDTO mallDTO = new MallDTO();
        mallDTO.setId(1L);
        mallDTO.setName("Mall Moldova");
        mallDTO.setShortName("Mall");

        MallDTO savedMall = this.webTestClient
                .post()
                .uri(serverURL + "/api/service/mall/save")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .body(BodyInserters.fromValue(mallDTO))
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(MallDTO.class)
                .returnResult()
                .getResponseBody();


        //act
        HttpStatus deleteUni = this.webTestClient
                .delete()
                .uri(serverURL + "/api/service/mall/deleteById/" + savedMall.getId())
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(HttpStatus.class)
                .returnResult()
                .getResponseBody();


       // assertNotNull(savedMall);
    }


    private static List<MallDTO> getValidMall(){

        MallDTO mallDTO = new MallDTO();
        mallDTO.setId(1L);
        mallDTO.setName("Mall Moldova");
        mallDTO.setShortName("Mall");

        MallDTO mallDTO1 = new MallDTO();
        mallDTO1.setId(2L);
        mallDTO1.setName("Mall Moldova");
        mallDTO1.setShortName("Mall");

        MallDTO mallDTO2 = new MallDTO();
        mallDTO2.setId(3L);
        mallDTO2.setName("Mall Moldova");
        mallDTO2.setShortName("Mall");

        MallDTO mallDTO3 = new MallDTO();
        mallDTO3.setId(4L);
        mallDTO3.setName("Mall Moldova");
        mallDTO3.setShortName("Mall");

        List<MallDTO> mallDTOList = new ArrayList<>();
        mallDTOList.add(mallDTO);
        mallDTOList.add(mallDTO1);
        mallDTOList.add(mallDTO2);
        mallDTOList.add(mallDTO3);

        return mallDTOList;
    }

}
