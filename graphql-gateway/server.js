const { ApolloServer } = require('apollo-server');
const { ApolloGateway } = require('@apollo/gateway');
const process = require('process');

const inventoryHost = process.env.INVENTORY_HOST;
const orderHost = process.env.ORDER_HOST;

if (!inventoryHost) {
    console.log("Host of inventory service is not defined. Use environment variable INVENTORY_HOST to set it.");
    process.exit(1);
}
if (!orderHost) {
    console.log("Host of order service is not defined. Use environment variable ORDER_HOST to set it.");
    process.exit(1);
}

const gateway = new ApolloGateway({
    serviceList: [
        { name: 'inventory', url: inventoryHost },
        { name: 'order', url: orderHost }
    ],

    __exposeQueryPlanExperimental: true
})

const server = new ApolloServer({
    gateway,
    engine: false,
    subscriptions: false,
});

server.listen().then(({url}) => {
    console.log(`🚀 Server ready at ${url}`);
});
