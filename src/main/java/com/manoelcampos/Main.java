package com.manoelcampos;

import com.manoelcampos.products.Product;
import com.manoelcampos.products.Products;
import com.manoelcampos.xmlparsing.XmlParser;

import java.io.IOException;

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
            System.out.println("# Convertendo XML contendo vários objetos Product para um objeto Products");
            final Products products = parser.unmarshal(XML_URL);
            for (Product product : products.getProducts()) {
                System.out.printf("\t%s%n", product);
            }

            System.out.println("\n# Convertendo o objeto Products (contendo uma lista de Product) de volta para XML");
            System.out.println(parser.marshal(products));;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
