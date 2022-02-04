
import ddf.minim.analysis.*;
import ddf.minim.*;
import papaya.*;
import ddf.minim.ugens.*;
import ddf.minim.spi.*; // for AudioStream
import processing.pdf.*; //for pdf export

//soundflowers check out
Minim minim;
//AudioPlayer player;
FFT fft ;
AudioOutput out;
AudioInput in;



////initialwerte-----------------
float alpha_0 = PI/2;
float b1_0 = 100; // Breite der Pfeile gesamt
float b2_0 = 5;// Breite der stengel
float l_0 =   100 ;// Die Länge des Stiels
float mitte_0 = 0.5;
////-----------------------------------------------
float alpha = alpha_0;
float alpha1 =  alpha_0;
float b1 = b1_0; // Breite der Pfeile gesamt
float b2 = b2_0;// Breite der stengel
float l =  l_0 ;// Die Länge des Stiels
float mitte = mitte_0;
float tlauf = 10;
float vorz = 1;
float anzlauf =5;
float v1, v2, nn, beatmittel, amp;
float fak = 10;
float beat = 0;
float mittefak = 0.0;
float b1fak = 0.6;
float b2fak = 15;
float lfak = 5;
float alphafreq = 0;
float strokewidth=1;
float sign;
int backy = 0;
float lauf = 0.0;
float distance = 50.0;
float beatalt = 0.0;
float beatneu = 0.0;
float disfak = 1000;
float rotphi = 0; //is the turning angle
float rotfak = 0;
float alphafak = 0.2;
boolean randombool =false;
boolean savebool = false;
boolean b1bool = true;
boolean b2bool = true;
boolean lbool = true;
boolean alphabool = false;
//for the colors
boolean colorbool = false;
boolean balls = false;

int N, nal, jj, numarrows ;
int ebenen = 0;
float[] mousept = new float[2];
int buffer = 1024;
float[] beatarray = new float[buffer];
float[] freqarray = new float[buffer];
float[] fmittel = {0., 0. , 0.} ;
float[] fmittelalt = new float[3];
int[] freq = new int[3];
float limits[] = new float[3];

//float[][][] arrow;
float[][][] newarrows;
float[][][] newarrows1;
int M, MM, NN ;
ArrayList<float[][][]> arrows;
WindowFunction newWindow = FFT.NONE;
//--------------------------------------------------------------------
//float[][][] bla = new float[5][5][5];


void setup() 
{
  //size(1900, 1000,P2D); //FX2D  or P2D
  fullScreen(FX2D, SPAN);
  noFill();
  //background(0);
  //stroke(255);
  //strokeWeight(1);
  //strokeCap(SQUARE);
  /*size(640, 360);  // Size should be the first statement
  stroke(255);     // Set line drawing color to white
  noLoop();*/
  // we pass this to Minim so that it can load files from the data directory
  minim = new Minim(this);                                               
  // construct a LiveInput by giving it an InputStream from minim.                                                  
  in = minim.getLineIn(); //new LiveInput( inputStream );
  
  
  //player = minim.loadFile("04 Vitamin C.mp3");//"04-AudioTrack 04.mp3");//"06-sigur_ros-untitled-its.mp3");//"01 - Track 1.mp3");//"SahneHaufen-1.mp3");//"03-AudioTrack 03.mp3");//"02-Buck 65 _ Wicked And Weird.mp3");//"autechre - amber - 02 - montreal.mp3");//"08 Adagio for Strings and Organ in G minor - Arranged by Matthias Arfmann - Adagio.mp3");//"5 Alles renkt sich wieder ein.mp3");//"mundlbums2.mp3");//"bobby hutcherson- montara - 05 - little angel.mp3");//"02 Tombstone Blues.mp3");//
  
  // play the file from start to finish.
  // if you want to play the file again, 
  // you need to call rewind() first.
  //player.play();
  //player.loop();
  fft = new FFT( buffer, in.sampleRate() );
  println(in.sampleRate()); 
  //the limits of the frequency separation
  limits[0] =  buffer/200 ; //low frequencies
  limits[1] =  buffer/6 ; //mid frequencies
  limits[2] =  buffer/2 ; //high frequencies (end frequency)
}


void mouseWheel(MouseEvent event) {
  float e = event.getAmount();   //Winkel<---------------------------------------------
  alpha = alpha + e/50;
  alpha1 = alpha;
  //println(e);
  }  
  
  
void keyPressed() {
  final int k = keyCode;
  if (k >47 && k < 58){ ebenen = k - 48; }//tasten 0 bis 9
  //Schwarz auf weiss oder weiss auf schwarz
  else if (k == 66){ backy = 0 ;} //taste b
  else if (k == 87){ backy = 255;  }//taste w
 //Gesamtfaktor 
  else if (k == 70){  //taste f
    if (abs(fak) < 1) {fak = fak + 0.1;}
    else {             fak = fak + 1;}
  }
  else if (k == 68){ //taste d
    if(abs(fak) <= 1 ){fak = fak - 0.1;}
    else{              fak = fak - 1;}  
  }
  //Verschiebung der Mitte
  else if (k== 77) { mittefak = mittefak + 0.1; }//taste m
  else if (k== 78) { mittefak = mittefak - 0.1; }//taste n
  //Breite Pfeile Spitze
  else if (k==72)  {
    if (abs(b1fak) < 1) {b1fak = b1fak + 0.1;}
    else {             b1fak = b1fak + 1;} 
  }//taste h
  else if (k==71)  {
     if(abs(b1fak) <= 1 ){ b1fak = b1fak - 0.1; }
     else{              b1fak = b1fak - 1;}   
  }//taste g
  //Länge Stengel
  else if (k==76)  {lfak = lfak+1;  }//taste l
  else if (k==75)  {lfak = lfak-1; }//taste k
  //breite Stengel
  else if (k==80)  { //taste p
      if(abs(b2fak) < 1 ){ b2fak = b2fak + 0.1; }
      else{              b2fak = b2fak + 1;}   } 
  else if (k==79)  { //taste o
     if(abs(b2fak) <= 1 ){ b2fak = b2fak - 0.1; }
     else{                b2fak = b2fak - 1;   }  
  }
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
    alpha = alpha + PI/180;
    alpha1 = alpha;
  }
  //the angle of the arrows taste down
  else if (k==40) {
    alpha = alpha - PI/180;
    alpha1 = alpha;
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
 else if (k == 521){ alphabool = ! alphabool ;  }
 //67 = c makes the alphafak lower
 else if (k == 67){ alphafak -= 0.1;  }
 //86 = v makes the alphafak higher
 else if (k == 86){alphafak += 0.1;  }
 // Enter key makes the lines colorfull
 else if (k == 10){colorbool = ! colorbool; }
 // 113 = q makes draw balls instead of lines
 else if (k==69){balls= ! balls;}
 println(keyCode);
  }
  
  
void draw() {
  
    if (savebool){
      //saveFrame();
      // Note that #### will be replaced with the frame number. Fancy!
      //beginRecord(PDF, "frame-####.pdf"); 
    } 
  
    //if (mouseButton == LEFT){
    background(backy);
    strokeWeight(strokewidth); //strichbreite
    //}
   lauf+= 0.01;
   beat = 0;
   beatmittel = 0;
   amp = 0;
   beatneu = 0;
   newWindow = FFT.HANN;
   fft.window( newWindow );
   fft.forward( in.mix ); //fourier-Transformation
   for (int kk = 0; kk<3; kk++){
     fmittelalt[kk] = fmittel[kk];
     fmittel[kk] = 0;
     freq[kk] = 1;
   }
   alphafreq = 0;
   //loop through all frequencies in fft.getBand 
   //and all the direct sound signals
   //------------------------------------------------------------------------------------------
   for(int m =0 ; m < buffer; m++)
   {
      //the difference of the gain in left and right side
      beatarray[m] = in.left.get(m) - in.right.get(m);
      //mean value in the buffer area
      beatmittel = beatmittel + beatarray[m];
      //the maximum of the beat-Gain
      beatneu = max(beatneu, abs(in.mix.get(m)));
      //the array
      freqarray[m] =  fft.getBand(m)  ;
      if (0 < m && m < limits[0] ){ //
        fmittel[0] = fmittel[0] + freqarray[m];
        if (freqarray[m] > freqarray[freq[0]]){
          freq[0] = m;
        }
      }
      else if ( limits[0] < m && m < limits[1]    ){
        if (fmittel[1] < freqarray[m]){
          fmittel[1] = freqarray[m];
          if (freqarray[m] > freqarray[freq[1]]){
            freq[1] = m;
          }
        }
        alphafreq = alphafreq + freqarray[m];
      }
      else if (  limits[1] < m && m < limits[2]    ){
        if (fmittel[2] < freqarray[m]){
          fmittel[2] = freqarray[m];
          if (freqarray[m] > freqarray[freq[2]]){
            freq[2] = m;
          }
        }
        //fmittel[2] = fmittel[2] + freqarray[m];
      }     
   }//ende frequenzschleife
   //------------------------------------------------------------------------------------------
   //fmittel[0]= (fmittel[0] + fmittelalt[0])/2;
   //fmittel[1]= (fmittel[1]/floor(buffer/6 - buffer/200 ) + fmittelalt[1] )/2;
   //fmittel[2]= (fmittel[2]/floor(buffer/2 - buffer/6) + fmittelalt[2] )/2;
   distance = abs(beatalt-beatneu)*disfak*fak;
   beatalt = beatneu;
   //println(distance);
   if (randombool) {
     alphafreq = fmittel[1]/100000;
     sign = (noise(lauf)*2 - 1);
     if (sign < 0) {sign = -1;}
     else {sign = 1;}
     alpha += sign*alphafreq;
     alpha1 = alpha;
   }
   //defining the midpoint 
   //-------------------------------------------------
   beat = max(max(beatarray), abs(min(beatarray)));
   beatmittel = 0.5 + beatmittel/buffer/beat*mittefak;
   //rotation-------------------------------------
   rotphi = beat*PI*rotfak;
   //-------------------------------------------------
   mousept[0] = 0;//mouseX + 10;
   mousept[1] = 0; //mouseY + 10;// 0.01;
   //Apply the Parameters for the Arrows
   //--------------------------------------------------
   //set the broughtness of the arrow-head
   if (b1bool) {b1 = fmittel[0]*b1fak*fak*freq[0];}
   //set the length of the arrow-stem
   if (lbool){l = fmittel[1] *lfak*fak*(1+freq[1]/limits[1]*2);}
   //set the broughtness of the arrow-stem
   if (b2bool){b2 = fmittel[2]*b2fak*fak*(1+freq[2]/limits[2]);}
   //set the angle of the arrows automaticly
   if (alphabool){
     //alphafreq = fmittel[1]/10;
     alpha = alphafreq*alphafak;
     alpha1 = alpha;
   }
   //set the asymmetry of the arrows
   mitte = beatmittel;
   //for the nextarrows the ratios will be given as input
   v1 = b1/b2;
   v2 = b1/l;
   //--------------------------------------------------
   newarrows = makeArrow(alpha,b1,b2,l,mitte,alpha1,mousept,rotphi);

   //println(nal);
   //float[][][] arrows = new float[7][2][N];
   //arrows = zeros(7,2,N);
   //newarrows = arrow;
   //arrows.add(arrow);
   //Zeichnen--------------------------------------------------------------------------
   if(balls){
     //printballs();
     printsplines();
   }
   else{
    printarrows(newarrows,alpha,mitte,v1,v2,alpha1,ebenen,colorbool);
   }
    //------------------------------------------------------------------------------------------
  

  //endRecord();
   /*
   M = arrow.length;
   MM = arrow[0].length;
   NN = arrow[0][0].length*2;
  
  for (int n = 0; n<ebenen;n++){ 
    //m = floor(log(n)/log(2))+1;
    //nn = mod(m-1,5) + 1;
    //plot([arrow(:,1,n); arrow(1,1,n)],[arrow(:,2,n); arrow(1,2,n)],farb(nn),'linewidth',width)
    line(mouseX,mouseY , mouseX + y, mouseY + sqrt( pow(r,2) - pow(y,2)));
    */
    
  }
  void mousePressed() {
    //noLoop();
  }
  void mouseReleased() {
    loop();
  }
/*
class HLine { 
  float ypos, speed; 
  HLine (float y, float s) {  
    ypos = y; 
    speed = s; 
  } 
  void update() { 
    ypos += speed; 
    if (ypos > height) { 
      ypos = 0; 
    } 
    line(0, ypos, width, ypos); 
  } 
} */
