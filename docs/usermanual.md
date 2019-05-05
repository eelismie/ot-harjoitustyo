# User Manual
## Running the program 

To run, give the command:
```
mvn compile exec:java -Dexec.mainClass=markovsimulation.ui.markovUI
```
Or, alternatively run the .jar with:
```
java -jar MarkovSimulator.jar
```

## Load Screen

You will then be in the load screen. In the load screen you can load a simulation from the file selector from your computer by pressing 'load from file'. The file must be a file with a .csv extension that is formatted according to this [example](https://github.com/volatilequark/ot-harjoitustyo/blob/master/docs/sample.csv). If the file is formatted correctly, a successful load should color the load button green. You can load a previous save by pressing 'load from save'. The button turns green if the load is successful. You can then press 'next'. If you want to start from scratch, just press next without loading.

![loadbuttons](docs/pictures/loadbuttons.png)

## Edit Screen

You can add nodes into the network by writing into the left hand box and pressing "add" on the edit screen. You can clear the current nodes with the 'clear' button. Add connections between these nodes by providing their start and end indices, then pressing "add". The connection wont be added if the input is bad. You can save progress by pressing 'save', and you can export the simulation you just made by pressing 'export'. **Remember to add the .csv extension** yourself when exporting if you wish to load it again with the program.    
When you have created nodes you can press "run". If no nodes are created you can't get to the next screen.

![editbuttons](docs/pictures/editscreen.png)

## Result Screen

After this, you can advance the simulation state you created. You can select the starting node with a slider. You can enable the random walk to perform jumps with the slider below it. You can also edit the step size with a third slider. If you want to reset the simulation state, press 'reset'. 

![resultbuttons](docs/pictures/resultscreen.png)


