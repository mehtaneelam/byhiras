# byhiras

## to run the code
./gradlew bootRun

## via IDE open and run the main class
under configuration module
com.byhiras.test.Application

## details
- Get all bid for item http://localhost:8080/bids?item_code=CODE-1
- Get all bid for user http://localhost:8080/items?username=neelam
- Get winning bid http://localhost:8080/winning_bid?code=CODE-2
- Register bid http://localhost:8080/bids it is a POST  with body as
 {
    "item_code" : "CODE-2",
    "username":"mehta",
    "amount" : 6
   
}
