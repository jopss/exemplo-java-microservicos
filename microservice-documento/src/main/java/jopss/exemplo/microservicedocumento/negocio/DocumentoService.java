package jopss.exemplo.microservicedocumento.negocio;

import jopss.exemplo.microservicedocumento.excecao.DocumentoException;
import jopss.exemplo.microservicedocumento.negocio.modelo.Arquivo;
import jopss.exemplo.microservicedocumento.negocio.modelo.Documento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository repository;

    @Value("${diretorio-documentos}")
    private String diretorio;

    @Transactional
    public Documento gravar(Long idPessoa, String nomeArquivo, InputStream inputStream) {
        nomeArquivo = this.padronizarNomeArquivo(idPessoa, nomeArquivo);
        Documento doc = repository.findByIdPessoa(idPessoa);
        if(doc == null){
            doc = new Documento(idPessoa, nomeArquivo);
        }else{
            doc.setNomeDocumento(nomeArquivo);
        }

        this.gravarArquivoFisico(nomeArquivo, inputStream);
        return this.repository.save(doc);
    }

    private String padronizarNomeArquivo(Long idPessoa, String nomeArquivo) {
        return idPessoa+"-"+nomeArquivo;
    }

    private void gravarArquivoFisico(String nomeArquivo, InputStream inputStream) {
        try{
            Path caminhoArquivo = Paths.get(diretorio).toAbsolutePath().normalize().resolve(nomeArquivo);
            Files.copy(inputStream, caminhoArquivo, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new DocumentoException("Ocorreu um erro ao armazenar '" + nomeArquivo + "'.", ex);
        }
    }

    public Arquivo retornarPorPessoa(Long idPessoa) {
        Documento doc = repository.findByIdPessoa(idPessoa);
        if(doc == null){
            throw new DocumentoException("Documento da pessoa '" + idPessoa + "' inexistente.");
        }

        try{
            Path caminhoArquivo = Paths.get(diretorio).toAbsolutePath().normalize().resolve(doc.getNomeDocumento());
            File arquivo = caminhoArquivo.toFile();

            if(arquivo == null || !arquivo.exists()){
                throw new DocumentoException("Arquivo do documento nao encontrado para pessoa '" + idPessoa + "'.");
            }

            String base64 = Base64Utils.encodeToString(new FileInputStream(arquivo).readAllBytes());
            return new Arquivo(doc, base64);
        } catch (IOException ex) {
            throw new DocumentoException("Ocorreu um erro ao tentar retornar arquivo da pessoa '" + idPessoa + "'.", ex);
        }
    }
}
