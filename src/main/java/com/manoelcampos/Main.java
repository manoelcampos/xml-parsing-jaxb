package com.manoelcampos;

import com.manoelcampos.products.Products;
import com.manoelcampos.xmlparsing.XmlParser;

import java.io.IOException;
import java.net.URL;

/**
 * Classe principal que executa o exemplo.
 *
 * @author Manoel Campos da Silva Filho
 */
public class Main {
    /**
     * URL de um arquivo XML a ser convertido para um objeto Java.
     */
    private static final String XML_URL = "https://raw.githubusercontent.com/manoelcampos/xml-parsing-jaxb/master/products.xml";

    /**
     * String contendo outro exemplo de documento XML a ser convertido para um objeto Java.
     */
    private static final String XML = """
                                        <products>
                                            <product id="1">
                                              <description>Mouse</description>
                                              <brand>Logitech</brand>
                                              <price>100</price>
                                            </product>
                                            <product id="2">
                                              <description>Keyboard</description>
                                              <brand>Multilaser</brand>
                                              <price>80</price>
                                            </product>
                                        </products>  
                                       """;

    /**
     * Método de exemplo que mostra como usar a classe {@link XmlParser} para
     * ler um arquivo XML contendo uma lista de produtos
     * e então criar um objeto {@link Products} contendo os produtos
     * lidos do arquivo.
     * Os produtos são facilmente impressos com um único println
     * por conta do método {@link Products#toString()}.
     *
     * O exemplo também mostra como converter um objeto Java como {@link Products}
     * de volta para XML.
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            final XmlParser<Products> parser = new XmlParser<>(Products.class);
            System.out.println("# Convertendo XML de um URL para um objeto Products");
            final Products products1 = parser.unmarshal(new URL(XML_URL));
            System.out.println(products1);

            System.out.println("\n# Convertendo o objeto Products de volta para XML");
            final String xml = parser.marshal(products1);
            System.out.println(xml);

            System.out.println("# Convertendo XML armazenado em uma String para um objeto Products");
            final Products products2 = parser.unmarshal(XML);
            System.out.println(products2);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
