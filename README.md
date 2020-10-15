# Springboot REST demo

A demo springboot REST API for a fictional product catalog with orders.

# How to build/run

        mvn clean package
        java -jar target/RchMain-0.0.1-SNAPSHOT.jar

        # or
        mvn spring-boot:run

# API endpoints

### create new product

- POST /rest/product

        input: { "name": <string>, "price": <double> }
        output: { "id": <long> } # id of the newly created product
   
  e.g.
        curl http://localhost:8080/rest/product -XPOST -d '{"name": "ola", "price": 1} ' -H "Content-Type: application/json"

### list products

- GET /rest/products
        
        input: n.a.
        output: [ { 
            "createdOn": <date>, 
            "enabled": <boolean>, 
            "id": <long>, 
            "name": <string>, 
            "price": <double>
        }, ... ]

  e.g.
        curl http://localhost:8080/rest/products

### update a product

- PUT /rest/product/{productId}
        
        input: { "name": <string>, "price": <double> }
        output: { "status": <boolean> } # true if product was updated

  e.g. 
        curl http://localhost:8080/rest/product/5 -XPUT -d '{"name": "novonovo", "price": 99.9} ' -H "Content-Type: application/json"

### delete a product

- DELETE /rest/product/{productId}
        
        input: n.a.
        output: { "status": <boolean> } # true if product was deleted

  e.g. 
        curl http://localhost:8080/rest/product/5 -XDELETE
        
### create an order

- POST /rest/order

        input: { "email": <string>, "productIds": [ <long>, ... ] }
        output: { "id": <long> } # id of the newly created order

  e.g.
        curl http://localhost:8080/rest/order -XPOST -d '{"email": "foo@bar.pt", "productIds": [1,2,3]} ' -H "Content-Type: application/json"

### list orders

- POST /rest/orders?startDate=yyyy-MM-dd&endDate=yyyy-MM-dd

        input: n.a.
        output: [ { 
            "buyerEmail": <string>, 
            "createdOn": <date>, 
            "id": <long>, 
            "items": [ {
                "createdOn" : <date>,
                "enabled" : <boolean>,
                "id" : <long>,
                "name" : <string>,
                "price" : <double>
            }, ... ],
        }, ... ]

  e.g.
        curl "http://localhost:8080/rest/orders?startDate=2020-01-31&endDate=2020-12-12" 
        

