package de.florianbeetz.ma.graphql.order.service;

import java.util.ArrayList;
import java.util.List;

import de.florianbeetz.ma.graphql.order.service.model.ItemPrice;
import de.florianbeetz.ma.graphql.order.service.model.OrderPosition;
import de.florianbeetz.ma.graphql.order.service.model.ReservationPosition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InventoryService {

    private final ShopApiService shopApiService;

    @Autowired
    public InventoryService(ShopApiService shopApiService) {
        this.shopApiService = shopApiService;
    }

    public List<ReservationPosition> reserveItems(List<OrderPosition> positions) throws ServiceException {
        List<ReservationPosition> reservationPositions = new ArrayList<>();
        try {
            for (OrderPosition position : positions) {
                reservationPositions.addAll(shopApiService.reserveItem(position.getItemId(), position.getAmount()));
            }
            return reservationPositions;
        } catch (ApiException e) {
            // TODO: revert positions
            log.error("Failed to reserve positions.", e);
            throw new ServiceException(1, "Failed to reserve items: " + e.getMessage());
        }
    }

    public List<ItemPrice> getItemPrices(List<Long> itemIds) throws ServiceException {
        try {
            return shopApiService.getAllItemPrices(itemIds);
        } catch (ApiException e) {
            throw new ServiceException(1000, "Failed to fetch item prices.", e);
        }
    }

}
