package com.manoelcampos;

import com.manoelcampos.products.Product;
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
     * URL de onde o arquivo XML será obtido.
     */
    private static final String XML_URL = "https://raw.githubusercontent.com/manoelcampos/xml-parsing-jaxb/master/products.xml";

    /**
     * Método de exemplo que mostra como usar a classe {@link XmlParser} para
     * ler um arquivo XML contendo uma lista de produtos
     * e então criar um objeto {@link Products} contendo os produtos
     * lidos do arquivo.
     * O exemplo também mostra como converter um objeto Java como {@link Products}
     * de volta para XML.
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            final XmlParser<Products> parser = new XmlParser<>(Products.class);
            System.out.println("# Convertendo XML de um URL para um objeto Products");
            final Products products = parser.unmarshal(new URL(XML_URL));
            System.out.println(products);

            System.out.println("\n# Convertendo o objeto Products de volta para XML");
            final String xml = parser.marshal(products);
            System.out.println(xml);;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
