package domain.entities;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import com.blog.blog.domain.entities.Artigo;
import com.blog.blog.dto.ArtigoDTO;
import com.blog.blog.repository.ArtigoRepository;
import com.blog.blog.service.entities.ArtigoService;
import common.entities.ArtigoConstate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;

import static common.entities.ArtigoConstate.ARTIGO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = Artigo.class)
@ExtendWith(MockitoExtension.class)
public class ArtigoTest {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    @InjectMocks
    private ArtigoService artigoService;

    @Captor
    private ArgumentCaptor<Artigo> artigoArgumentCaptor;
    @Mock
    private ArtigoRepository artigoRepository;
    //operacao_estado_retorno

    @Test
    void criarArtigo_ValidaNoBanco_RetornaArtigo(){
        var input = new Artigo(
                254L,
                "BMW",
                "CARRO",
                "www.com",
                "carro do ano",
                "leonardo",
                Set.of("LUXO", "ALEMANHA"),
                LocalDateTime.parse("31/08/2024 14:30:45", formatter),
                LocalDateTime.parse("18/08/2024 14:30:45", formatter)
        );

        doReturn(input).when(artigoRepository.save(artigoArgumentCaptor.capture()));

        var inputDTO = new ArtigoDTO(
                254L,
                "BMW",
                "CARRO",
                "www.com",
                "carro do ano",
                "leonardo",
                Set.of("LUXO", "ALEMANHA"),
                LocalDateTime.parse("31/08/2024 14:30:45", formatter),
                LocalDateTime.parse("18/08/2024 14:30:45", formatter)
        );

        var result_service = artigoService.save(inputDTO);
        var artigoCaptured = artigoArgumentCaptor.getValue();
        assertEquals(input. , inputDTO.ge);
    }
}
