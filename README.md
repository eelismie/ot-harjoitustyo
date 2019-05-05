# Markov Process Simulation

A markov process is a weighted network model of a stochastic system, where the nodes of the network are possible states of the system, and the weights of the edges correspond to the probability of the system transitioning from one state to the other. The idea of this project is to create an application that allows the user to create and save small markov chains and explore the statistics of a random walk on them. One should find that for many configurations of the nodes and connections, the probability distribution of being at node n after t steps is the same for all starting nodes, as long as t is sufficiently large.

## Instructions

To run tests and generate a report, give the command:
```
mvn test jacoco:report
```
The results of the test can be viewed with a browser;
```
google-chrome index.html 
```
To generate a checkstyle report: 
```
mvn checkstyle:checkstyle
```
To generate a javadoc:
```
mvn javadoc:javadoc
```
To build an excecutable .jar with all necessary dependencies, use:
```
mvn package 
```
This will generate two files, one with the ending '...with-dependencies.jar'. This version works. 


## Docs 

[Specifications](https://github.com/volatilequark/ot-harjoitustyo/blob/master/docs/vaatimusmaarittely.md)

[Work Hour Record](https://github.com/volatilequark/ot-harjoitustyo/blob/master/docs/workhours.md)

[User Manual](https://github.com/volatilequark/ot-harjoitustyo/blob/master/docs/usermanual.md)

[Architecture description](https://github.com/volatilequark/ot-harjoitustyo/blob/master/docs/architecture.md)

[Testing](docs/testing.md)

## Releases
[Final Release](https://github.com/volatilequark/ot-harjoitustyo/releases/tag/v2.0)

[Week 5 Release](https://github.com/volatilequark/ot-harjoitustyo/releases/tag/viikko5)
