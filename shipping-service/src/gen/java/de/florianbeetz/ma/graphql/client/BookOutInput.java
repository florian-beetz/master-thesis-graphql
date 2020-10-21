// Generated from graphql_java_gen gem

package de.florianbeetz.ma.graphql.client;

import java.io.Serializable;
import java.util.List;

public class BookOutInput implements Serializable {
    private List<StockPosition> positions;

    public BookOutInput(List<StockPosition> positions) {
        this.positions = positions;
    }

    public List<StockPosition> getPositions() {
        return positions;
    }

    public BookOutInput setPositions(List<StockPosition> positions) {
        this.positions = positions;
        return this;
    }

    public void appendTo(StringBuilder _queryBuilder) {
        String separator = "";
        _queryBuilder.append('{');

        _queryBuilder.append(separator);
        separator = ",";
        _queryBuilder.append("positions:");
        _queryBuilder.append('[');
        {
            String listSeperator1 = "";
            for (StockPosition item1 : positions) {
                _queryBuilder.append(listSeperator1);
                listSeperator1 = ",";
                item1.appendTo(_queryBuilder);
            }
        }
        _queryBuilder.append(']');

        _queryBuilder.append('}');
    }
}
