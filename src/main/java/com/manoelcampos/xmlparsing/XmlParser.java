package com.manoelcampos.xmlparsing;


import com.manoelcampos.products.Product;
import com.manoelcampos.products.Products;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.bind.JAXB;

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
     * {@link URL} de onde será obtido o XML.
     */
    private final URL url;

    /**
     * Classe que representa o tipo de dados contido no XML. Após ser feito o parse do
     * XML, um objeto de tal classe será criado para armazenar os dados lidos.
     * Esta classe representa a estrutura do XML.
     */
    private final Class<T> classe;

    /**
     * Método de exemplo que mostra como usar a classe para
     * ler um arquivo XML contendo uma lista de produtos
     * e então criar um objeto {@link Products} contendo os produtos
     * lidos do arquivo.
     * 
     * @param args 
     */
    public static void main(String[] args) {
        try {
            final XmlParser<Products> parser
                    = new XmlParser(
                            "https://raw.githubusercontent.com/manoelcampos/xml-parsing-jaxb/master/products.xml",
                            Products.class);
            final Products products = parser.parse();
            for (Product product : products.getProducts()) {
                System.out.println(product);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Instancia um XmlParser para ler um arquivo XML a partir de uma URL.
     *
     * @param urlStr URL do arquivo XML.
     * @param classe classe que representa o tipo de dados contido no XML. Veja {@link #classe} para mais detalhes.
     * @throws IOException quando o XML não puder ser lido (por exemplo, falha de conexão).
     */
    public XmlParser(final String urlStr, final Class<T> classe) throws IOException {
        this.url = new URL(urlStr);
        this.classe = classe;
    }

    /**
     * Lê o arquivo XML a partir da {@link #url} e faz o parse (unmarshall),
     * convertendo tal XML para um objeto Java contendo os dados lidos do arquivo.
     * Veja {@link #classe} para mais detalhes.
     *
     * @return um objeto da classe indicaca por {@link #classe}, contendo
     *         os dados lidos do arquivo XML
     * @throws IOException quando o XML não puder ser lido (por exemplo, falha de conexão).
     */
    private T parse() throws IOException {
        final URLConnection conn = url.openConnection();
        conn.connect();

        //A stream é automaticamente fechada pelo método unmarshal
        return JAXB.unmarshal(conn.getInputStream(), classe);
    }
}
