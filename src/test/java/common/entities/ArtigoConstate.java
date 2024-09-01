package common.entities;

import com.blog.blog.domain.entities.Artigo;
import com.blog.blog.dto.ArtigoDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class ArtigoConstate {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    public static final ArtigoDTO ARTIGO = new ArtigoDTO(254L,"BMW", "CARRO", "www.com", "carro do ano","leonardo", Set.of("LUXO", "ALEMANHA"),
            LocalDateTime.parse("31/08/2024 14:30:45", formatter),
            LocalDateTime.parse("18/08/2024 14:30:45", formatter));
}
