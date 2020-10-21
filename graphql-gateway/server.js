const { ApolloServer } = require('apollo-server');
const { ApolloGateway, RemoteGraphQLDataSource } = require('@apollo/gateway');
const process = require('process');
const axios = require('axios');

// get configuration from environment
const inventoryHost = process.env.INVENTORY_HOST;
const orderHost = process.env.ORDER_HOST;
const paymentHost = process.env.PAYMENT_HOST;
const shippingHost = process.env.SHIPPING_HOST;

if (!inventoryHost) {
    console.log("Host of inventory service is not defined. Use environment variable INVENTORY_HOST to set it.");
    process.exit(1);
}
if (!orderHost) {
    console.log("Host of order service is not defined. Use environment variable ORDER_HOST to set it.");
    process.exit(1);
}
if (!paymentHost) {
    console.log("Host of payment service is not defined. Use environment variable PAYMENT_HOST to set it.");
    process.exit(1);
}
if (!shippingHost) {
    console.log("Host of shipping service is not defined. Use environment variable SHIPPING_HOST to set it.");
    process.exit(1);
}



/**
 * Pass through Authorization HTTP headers.
 */
class AuthenticatedDataSource extends RemoteGraphQLDataSource {
    willSendRequest(requestContext) {
        if (requestContext.context.authHeader) {
            requestContext.request.http.headers.set('Authorization', requestContext.context.authHeader);
        }
    }
}

// define services with the configured URLs
const serviceList = [
    { name: 'inventory', url: inventoryHost },
    { name: 'order', url: orderHost },
    { name: 'payment', url: paymentHost },
    { name: 'shipping', url: shippingHost },
]

const gateway = new ApolloGateway({
    serviceList,

    // use the pass-through implementation for every service
    buildService({ name, url }) {
        return new AuthenticatedDataSource({ url });
    },

    // expose query plans to show in playground
    __exposeQueryPlanExperimental: true
})

const server = new ApolloServer({
    gateway,

    // disable Apollo Studio
    engine: false,

    // disable subscriptions, as they are not supported by the gateway
    subscriptions: false,

    // pass the authorization header through the context
    context: ({ req }) => {
        const authHeader = req.headers.authorization;
        return { authHeader };
    }
});

const sleep = (ms) => new Promise(resolve => setTimeout(resolve, ms));

const isAlive = (url) => {
    return axios.post(url, {
        query: "query{_service{sdl}}"
    })
}

const retry = async (n, wait, action) => {
    try {
        return await action();
    } catch (err) {
        if (n === 1) throw err;
        await sleep(wait);
        return await retry(n - 1, wait, action);
    }
}

Promise.all(serviceList
    .map(({name, url}) => retry(10, 5, async () => {
        console.log("Checking availability of service", name)
        await isAlive(url)
    })))
    .then(() => {
        server.listen().then(({url}) => {
            console.log(`🚀 Server ready at ${url}`);
        });
    });
