# Trying_Arrows
“Trying Arrows” creates visuals based on an arrow geometry reacting with sound input. 

It is programmed with processing version 3+ https://processing.org/download/
Additionally the papaya library, for matrix multiplication must be installed http://adilapapaya.com/papayastatistics/

The basic shape is an arrow, which is defined by 7 points. Depending on the frequencies of the sound input 
the parameters of the arrow are changing and can either be identified as an arrow, or as an abstract geometry. 
New arrows can be added on the right and left side while keeping the same properties but scaled and rotated 
in a way that they fit into the mother-arrow. This can be continued for up to 9 layers. It can be controlled 
during the run by typing the respective number in your keybord. On some machines it will be slow when running 
with 9 layers. 

The developing structure is self similar like a mathematical fractal, but depending on the sound frequencies 
the structure can change completely to a chaotic field of lines. The fragile construct needs a certain input 
to remain ordered as regular arrows.

The coordinates of the vortex of the arrows are stored in a matrix (7*N*2), N is the number of arrows, with the indicees.


  
                           1
                       /       \
                    /            \
                 /                 \
              /                       \
           /                            \
         /                                \
       /                                     \
    0-----------------6         3----------------2
                      |         |
                      |         |
                      |         |
                      |         |
                      5---------4
                      
                      
