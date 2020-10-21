// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import java.io.Serializable;

import com.shopify.graphql.support.Query;

public class AddressInput implements Serializable {
    private String city;

    private String street;

    private String zip;

    public AddressInput(String city, String street, String zip) {
        this.city = city;

        this.street = street;

        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public AddressInput setCity(String city) {
        this.city = city;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public AddressInput setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getZip() {
        return zip;
    }

    public AddressInput setZip(String zip) {
        this.zip = zip;
        return this;
    }

    public void appendTo(StringBuilder _queryBuilder) {
        String separator = "";
        _queryBuilder.append('{');

        _queryBuilder.append(separator);
        separator = ",";
        _queryBuilder.append("city:");
        Query.appendQuotedString(_queryBuilder, city.toString());

        _queryBuilder.append(separator);
        separator = ",";
        _queryBuilder.append("street:");
        Query.appendQuotedString(_queryBuilder, street.toString());

        _queryBuilder.append(separator);
        separator = ",";
        _queryBuilder.append("zip:");
        Query.appendQuotedString(_queryBuilder, zip.toString());

        _queryBuilder.append('}');
    }
}
