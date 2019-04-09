## How to use the current version of the UI 

To run, give the command:
mvn compile exec:java -Dexec.mainClass=markovsimulation.ui.markovUI

You will then be in the load screen. In the load screen you can load a simulation from the file selector from your computer. In the future you can hopefully also load data from google sheets.
The file must be a .csv file with an empty first line, a line with the numbern of nodes (int), and then the names 
of the nodes on the next n lines. After this the connections between nodes are given. Invalid connections are ignored. 

Example .csv:

3

event1

event2

event3

0,1

1,2

2,0

0,2

You can add nodes into the network by writing into the left hand box and pressing "add" on the edit screen and clear it, if necessary.
Add connections between these nodes by providing their indices, then pressing "add". 
The connection wont be added if the input is bad. 

When you have created nodes you can press "run".
After this you can view the effects of one step on the probabilities. You can select the starting node with a slider. In the future the results can hopefully be viewed as a network.

