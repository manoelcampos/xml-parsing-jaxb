package com.manoelcampos.xmlparsing;

import javax.xml.bind.JAXB;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;

/**
 * Classe genêrica que lê um arquivo XML a partir de uma URL e transforma seu conteúdo 
 * em um objeto java contendo os dados lidos. 
 * Ela usa a <a href="https://javaee.github.io/jaxb-v2/>API JAXB</a> para fazer o parse de XML.
 *
 * <p>Com o uso de generics, quem for utilizar a classe não precisa fazer typecast
 * do objeto gerado pelo parse do XML para um tipo específico.
 * O método {@link #unmarshal(String)} que faz tal conversão de XML para um objeto Java
 * retorna o tipo específico definido na declaração da classe.</p>
 *
 * @author Manoel Campos da Silva Filho
 * @param <T> classe que representa a estrutura do arquivo XML (veja {@link #targetClass}).
 */
public class XmlParser<T> {
    /**
     * Classe que representa o tipo de dados contido no XML.
     * Após ser feito o parse do XML, um objeto de tal classe será criado para armazenar os dados lidos.
     * Esta classe representa a estrutura do XML.
     */
    private final Class<T> targetClass;

    /**
     * Instancia um XML parser para fazer conversão de objetos de uma classe específica de/para XML.
     * @param targetClass classe de objetos a serem convertidos de/para XML.
     */
    public XmlParser(final Class<T> targetClass){
        final var msg = "A targetClass indica a classe do objeto Java para fazer conversão de/para XML. Tal parâmetro é obrigatório.";
        this.targetClass = Objects.requireNonNull(targetClass, msg);
    }

    /**
     * Lê o arquivo XML a partir do url e faz o parse (unmarshall),
     * convertendo tal XML para um objeto Java contendo os dados lidos do arquivo.
     *
     * @param urlStr URL do arquivo XML.
     * @return um objeto da classe indicaca no atributo {@link #targetClass},
     *         contendo os dados lidos do arquivo XML
     * @throws IOException quando o XML não puder ser lido (por exemplo, falha de conexão).
     */
    public T unmarshal(final String urlStr) throws IOException {
        final URL url = new URL(urlStr);
        final URLConnection conn = url.openConnection();
        conn.connect();

        //A stream é automaticamente fechada pelo método unmarshal
        return JAXB.unmarshal(conn.getInputStream(), targetClass);
    }

    /**
     * Converte um objeto do tipo T passado e gera uma String da representação XML dele.
     * @param object objeto a ser convertido para XML
     * @return String do XML convertido
     */
    public String marshal(final T object) {
        final StringWriter writer = new StringWriter();
        JAXB.marshal(object, writer);
        return writer.toString();
    }
}
