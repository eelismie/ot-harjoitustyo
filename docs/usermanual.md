## How to use the current version of the UI 

The UI is still a work in progress. There is no load functionality or config properties, nor can one view detailed
information about the current state of the simulation. However, one may first add nodes (events) into the simulation and advance 
the simulation by one step at a time. 

To run, give the command:
mvn compile exec:java -Dexec.mainClass=markovsimulation.ui.markovUI

You will then be in the load screen. Since there is no functionality here yet, click next. 

Add nodes into the network by writing into the left hand box and pressing "add".
Add connections between these nodes by providing their indices, then pressing "add". 
The connection wont be added for bad input. However, one can still add the same 
connection twice, which gives the wrong behaviour. This will be corrected later!

When you have created nodes you can press "run".
After this you can view the effects of one step on the state matrix of the network 
by pressing the right hand button repeatedly. The results are currently just printed on 
the command line. 

