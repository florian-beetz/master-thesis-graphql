require 'graphql_java_gen'
require 'graphql_schema'
require 'json'

introspection_result = File.read("scripts/schema.json", :encoding => 'utf-8')
schema = GraphQLSchema.new(JSON.parse(introspection_result))

GraphQLJavaGen.new(schema,
                   package_name: "de.florianbeetz.ma.graphql.client",
                   nest_under: "ShopSchema",
                   version: "1",
                   custom_scalars: [
                       GraphQLJavaGen::Scalar.new(
                           type_name: 'Int',
                           java_type: 'Long',
                           deserialize_expr: ->(expr) { "jsonAsInteger(#{expr}, key).longValue()" }
                       ),
                       GraphQLJavaGen::Scalar.new(
                           type_name: 'ID',
                           java_type: 'Long',
                           deserialize_expr: ->(expr) { "Long.parseLong(jsonAsString(#{expr}, key))" }
                       )
                   ],
).save_granular("#{Dir.pwd}/src/gen/java/de/florianbeetz/ma/graphql/client/")