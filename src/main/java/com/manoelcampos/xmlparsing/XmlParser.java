package com.manoelcampos.xmlparsing;

import javax.xml.bind.JAXB;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Classe genêrica que lê um arquivo XML a partir de uma URL e transforma seu conteúdo 
 * em um objeto java contendo os dados lidos. 
 * A classe usa a API JAXB para fazer o parse do XML.
 *
 * @author Manoel Campos da Silva Filho
 * @param <T> classe que representa a estrutura do arquivo XML (veja {@link #classe}).
 */
public class XmlParser<T> {
    /**
     * Lê o arquivo XML a partir do url e faz o parse (unmarshall),
     * convertendo tal XML para um objeto Java contendo os dados lidos do arquivo.
     *
     * @param targetClass classe que representa o tipo de dados contido no XML.
     *               Após ser feito o parse do XML, um objeto de tal classe será criado para armazenar os dados lidos.
     *               Esta classe representa a estrutura do XML.
     * @param urlStr URL do arquivo XML.
     * @return um objeto da classe indicaca no parâmetro "classe", contendo
     *         os dados lidos do arquivo XML
     * @throws IOException quando o XML não puder ser lido (por exemplo, falha de conexão).
     */
    public T unmarshal(final Class<T> targetClass, final String urlStr) throws IOException {
        final URL url = new URL(urlStr);
        final URLConnection conn = url.openConnection();
        conn.connect();

        //A stream é automaticamente fechada pelo método unmarshal
        return JAXB.unmarshal(conn.getInputStream(), targetClass);
    }

    /**
     * Converte um objeto do tipo T passado e gera uma String da
     * representação XML dele.
     * @param object objeto a ser convertido para XML
     * @return String do XML convertido
     */
    public String marshal(final T object) {
        final StringWriter writer = new StringWriter();
        JAXB.marshal(object, writer);
        return writer.toString();
    }
}
