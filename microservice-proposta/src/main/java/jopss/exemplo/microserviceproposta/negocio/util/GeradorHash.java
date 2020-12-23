package jopss.exemplo.microserviceproposta.negocio.util;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class GeradorHash {

    public static String gerarCodigo() {
        String coisaVariavel = ""+new Date().getTime();
        return DigestUtils.md5DigestAsHex(coisaVariavel.getBytes(StandardCharsets.UTF_8));
    }

}
