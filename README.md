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
### line thickness

  //Strichbreite
  else if (k == 39 ){//taste links
    if (strokewidth <= 1){
      strokewidth = abs(strokewidth) + 0.1; 
    }
    else{ 
        strokewidth = strokewidth+1; }
  } 
  else if (k == 37 ){      //taste rechts
    if (strokewidth <= 1){
      strokewidth = max(abs(strokewidth) - 0.1,0.1); 
    }
    else{
      strokewidth = strokewidth-1; 
    }
  }
  //the angle of the arrows taste up
  else if (k == 38){
    slowfac +=500;
  }
  //the angle of the arrows taste down
  else if (k==40) {
    slowfac += -500;
    slowfac = max(slowfac,1);
    println(slowfac);
  }
  //rotation x and y---------------------------------
  else if (k==88)  { //x
      if(abs(rotfak) < 1 ){ rotfak = rotfak + 0.1; }
      else{rotfak = rotfak + 1;}   } //taste p
  else if (k==89)  { //y
     if(abs(rotfak) <= 1 ){ rotfak = rotfak - 0.1; }
     else{                rotfak = rotfak - 1;   }  }  
  //-------------------------------------------------   
  //distance-Faktor 
  else if (k == 65){disfak = disfak - 50; } //taste a
  else if (k == 83){disfak = disfak + 50; } //taste s
  //set if the angle of the arrows should change randomly
  //key "r"
  else if (k == 82){ 
    randombool = ! randombool ;
    println("random is: ", randombool);
    }
   else if (k == 81){ //taste q for saving
    //savebool = ! savebool ;
    println("savebool is: ", savebool);
    }
   //44 = , turn off b1
   else if (k == 44){  b1bool = ! b1bool;  }
   //46 = . turn off b2
   else if (k == 46){ b2bool = ! b2bool  ; }
   //45 = - turn off l
   else if (k == 47){ lbool = ! lbool ;  }
   //521 = * turn off alpha
   else if (k == 93){ alphabool = ! alphabool ;  }
   //67 = c makes the alphafak lower
   else if (k == 67){ alphafak -= 0.1;  }
   //86 = v makes the alphafak higher
   else if (k == 86){alphafak += 0.1;  }
   // Enter key makes the lines colorfull
   else if (k == 10){
     colorbool = ! colorbool;
     stroke(linecolor);
   }
   // 113 = q makes draw balls instead of lines
   else if (k==69){shapetype = (shapetype + 1) % 4;}
   println(keyCode);
}

