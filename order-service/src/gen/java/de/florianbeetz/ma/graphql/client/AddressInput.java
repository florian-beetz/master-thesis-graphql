// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.shopify.graphql.support.AbstractResponse;
import com.shopify.graphql.support.Arguments;
import com.shopify.graphql.support.Error;
import com.shopify.graphql.support.Query;
import com.shopify.graphql.support.SchemaViolationError;
import com.shopify.graphql.support.TopLevelResponse;
import com.shopify.graphql.support.Input;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
