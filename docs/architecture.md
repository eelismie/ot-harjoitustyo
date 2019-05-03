The program follows a simple structure. The user interacts with a UI layer, and the application logic is handled chiefly 
by the markovManager class. Simulation descriptions are contained in simDescriptor objects, which can be loaded from a data access object or created by the user in the application.

The simulation itself is contained in the simulation class. 

![Architecture](https://github.com/volatilequark/ot-harjoitustyo/blob/master/docs/architecture.jpg)

When the user presses the 'next' button in the result display, the following sequence occurs. The final result is that the current simulation state has advanced by n steps, and the results of taking these steps are displayed to the user.

![Sequence](https://github.com/volatilequark/ot-harjoitustyo/blob/master/docs/simsequence.png)

