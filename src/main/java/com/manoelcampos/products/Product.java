package com.manoelcampos.products;

import javax.xml.bind.annotation.*;

/**
 * Classe que representa um produto e possibilita
 * usar a biblioteca JAXB para fazer marshal e ummarshal,
 * isto é, converter um objeto da classe para XML e converter
 * uma String XML pra um objeto da classe.
 *
 * @author Manoel Campos da Silva Filho
 */
@XmlAccessorType(XmlAccessType.FIELD) //indica que os campos da classe vão ser convertidos de/para XML
public class Product {
    /**
     * Indica que o campo id da classe é definido como um atributo de uma tag no XML.
     */
    @XmlAttribute
    private int id;

    /**
     * Indica que o campo description na classe é definido como uma tag (elemento) no XML.
     */
    @XmlElement
    private String description;

    @XmlElement
    private String brand;

    @XmlElement
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format(
                "Product: id %d description: %s brand: %s price: %.2f",
                id, description, brand, price);
    }
}
