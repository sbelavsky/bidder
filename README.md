[![Coverage](https://img.shields.io/badge/coverage-90-green.svg)](https://shields.io/)
# Bidder

Bidder is a java library for dealing with two party blind bid auction.

## Prerequisites
- Java 11+
- maven 3+

## Usage
You can use either local maven installation or project's maven wrapper:
```sh
#windows
mvnw.cmd test

#unix
./mvnw test
```
Test coverage report is in the `target/site` folder
## Structure
```
.
├── README.md
├── mvnw
├── mvnw.cmd
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── optimax
    │   │           ├── Copyable.java
    │   │           ├── Messages.java
    │   │           ├── account
    │   │           │   ├── BidderAccount.java
    │   │           │   ├── BidderAccountFactory.java
    │   │           │   └── DefaultBidderAccount.java
    │   │           ├── auction
    │   │           │   ├── Auction.java
    │   │           │   ├── TwoPartiesBlindBidAuction.java
    │   │           │   ├── processor
    │   │           │   │   ├── BidsProcessor.java
    │   │           │   │   ├── BidsProcessorFactory.java
    │   │           │   │   ├── FirstPartyWinBidsProcessor.java
    │   │           │   │   ├── SecondPartyWinBidsProcessor.java
    │   │           │   │   └── TieBidsProcessor.java
    │   │           │   └── result
    │   │           │       ├── TwoPartiesAuctionResult.java
    │   │           │       └── provider
    │   │           │           ├── AuctionResultProvider.java
    │   │           │           ├── AuctionResultProviderFactory.java
    │   │           │           └── DefaultAuctionResultProvider.java
    │   │           ├── bidder
    │   │           │   ├── AbstractBidder.java
    │   │           │   ├── AlwaysZeroBidder.java
    │   │           │   ├── Bidder.java
    │   │           │   ├── BidderFactory.java
    │   │           │   ├── HalfCashBidder.java
    │   │           │   └── RandomBidder.java
    │   │           ├── exception
    │   │           │   ├── DoubleInitializationException.java
    │   │           │   └── UninitializedException.java
    │   │           ├── participant
    │   │           │   ├── AuctionParticipant.java
    │   │           │   ├── AuctionParticipantComparatorFactory.java
    │   │           │   ├── AuctionParticipantFactory.java
    │   │           │   └── DefaultAuctionParticipant.java
    │   │           ├── product
    │   │           │   ├── DefaultProduct.java
    │   │           │   ├── Product.java
    │   │           │   └── ProductFactory.java
    │   │           └── strategy
    │   │               ├── AlwaysZeroBidStrategy.java
    │   │               ├── BidStrategy.java
    │   │               ├── HalfCashBidStrategy.java
    │   │               └── RandomBidStrategy.java
    │   └── resources
    │       └── messages_en.properties
    └── test
        └── java
            └── com
                └── optimax
                    ├── account
                    │   └── DefaultBidderAccountTest.java
                    ├── auction
                    │   └── TwoPartiesBlindBidAuctionTest.java
                    ├── bidder
                    │   ├── AlwaysZeroBidderTest.java
                    │   ├── BaseBidderTest.java
                    │   ├── HalfCashBidderTest.java
                    │   └── RandomBidderTest.java
                    ├── participant
                    │   └── DefaultAuctionParticipantTest.java
                    ├── product
                    │   └── DefaultProductTest.java
                    └── strategy
                        ├── AlwaysZeroBidStrategyTest.java
                        ├── HalfCashBidStrategyTest.java
                        └── RandomBidStrategyTest.java

```