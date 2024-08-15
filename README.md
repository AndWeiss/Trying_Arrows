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

The coordinates of the vortex of the arrows are stored in a matrix (7*N*2), N is the number of arrows, with the indices.


  
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
                      
                      
Sensitivity on the sound reaction can be changed by manupulating parameter with the keybord of your computer.
The following parameters can be manipulated:


## keys:

### number of layers
0 - 9	: Number of Arrow-layers, more layers means mor arrows
### colors
w		: white backround with black lines   
b		: black backround with white lines  
ENTER	: turn on/off colorfull lines 
### general size 
f		: increase the factor for general size of the geometry  
d		: decrease the factor for general size of the geometry  
### asymmetry
m		: increase asymmetry factor to the right side  
n		: increase asymmetry factor to the left side  
### width arrow head
h		: increase width of the arrow head (from point 0 to point 2)  
g		: increase width of the arrow head (from point 0 to point 2)  
### length of arrow stem (distance from point 5 to 6 and 3 to 4) 
l		: increase length factor  
k		: decrease length factor  
### width of arrow-stem (distance from point 6 to 3 and 5 to 4) 
p		: increase width of arrow-stem  
o		: increase width of arrow-stem  
### angle of the arrow-tip (alpha)
v		: increase angle factor
c		: decrease angle factor
### line thickness
right	: increase line thickness  
left	: decrease line thickness  
### slowness
up		: increase slow factor  
down	: decrease slow factor (make the reaktion faster)  
### rotation
x		: increase rotation factor  
y		: decrease rotation factor  
### distance of child arrows to parant
s		: increase distance factor  
a		: decrease distance factor  
### random arrow angle
r		: the angle of the arrow tip (and simultaniously the angle of the head) is changed randomly  
### saving screenshots 
q 		: each sequence is saved from pushing until pushing again - is disabled in code to avoid unwanted large data amound  
### turn off sensitivity on parameters
,		: turn off / on sensitivity of arrow-head-width (b1)   
.		: turn off / on sensitivity of shaft-width (b2)   
-		: turn off / on sensitivity of arrow-shaft-length (l) 
*		: turn off / on sensitivity on arrow-tip-angle (alpha)
### change shape type
q		: switch to different shape-types, straight lines, splines, ellipses

