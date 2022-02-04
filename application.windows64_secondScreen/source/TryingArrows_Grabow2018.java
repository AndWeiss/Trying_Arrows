import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import ddf.minim.analysis.*; 
import ddf.minim.*; 
import papaya.*; 
import ddf.minim.ugens.*; 
import ddf.minim.spi.*; 
import processing.pdf.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class TryingArrows_Grabow2018 extends PApplet {






 // for AudioStream
 //for pdf export

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
float mitte_0 = 0.5f;
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
float mittefak = 0.0f;
float b1fak = 0.6f;
float b2fak = 15;
float lfak = 5;
float alphafreq = 0;
float strokewidth=1;
float sign;
int backy = 0;
float lauf = 0.0f;
float distance = 50.0f;
float beatalt = 0.0f;
float beatneu = 0.0f;
float disfak = 1000;
float rotphi = 0; //is the turning angle
float rotfak = 0;
float alphafak = 0.2f;
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
float[] fmittel = {0.f, 0.f , 0.f} ;
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


public void setup() 
{
  //size(1900, 1000,P2D); //FX2D  or P2D
  
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


public void mouseWheel(MouseEvent event) {
  float e = event.getAmount();   //Winkel<---------------------------------------------
  alpha = alpha + e/50;
  alpha1 = alpha;
  //println(e);
  }  
  
  
public void keyPressed() {
  final int k = keyCode;
  if (k >47 && k < 58){ ebenen = k - 48; }//tasten 0 bis 9
  //Schwarz auf weiss oder weiss auf schwarz
  else if (k == 66){ backy = 0 ;} //taste b
  else if (k == 87){ backy = 255;  }//taste w
 //Gesamtfaktor 
  else if (k == 70){  //taste f
    if (abs(fak) < 1) {fak = fak + 0.1f;}
    else {             fak = fak + 1;}
  }
  else if (k == 68){ //taste d
    if(abs(fak) <= 1 ){fak = fak - 0.1f;}
    else{              fak = fak - 1;}  
  }
  //Verschiebung der Mitte
  else if (k== 77) { mittefak = mittefak + 0.1f; }//taste m
  else if (k== 78) { mittefak = mittefak - 0.1f; }//taste n
  //Breite Pfeile Spitze
  else if (k==72)  {
    if (abs(b1fak) < 1) {b1fak = b1fak + 0.1f;}
    else {             b1fak = b1fak + 1;} 
  }//taste h
  else if (k==71)  {
     if(abs(b1fak) <= 1 ){ b1fak = b1fak - 0.1f; }
     else{              b1fak = b1fak - 1;}   
  }//taste g
  //Länge Stengel
  else if (k==76)  {lfak = lfak+1;  }//taste l
  else if (k==75)  {lfak = lfak-1; }//taste k
  //breite Stengel
  else if (k==80)  { //taste p
      if(abs(b2fak) < 1 ){ b2fak = b2fak + 0.1f; }
      else{              b2fak = b2fak + 1;}   } 
  else if (k==79)  { //taste o
     if(abs(b2fak) <= 1 ){ b2fak = b2fak - 0.1f; }
     else{                b2fak = b2fak - 1;   }  
  }
  //Strichbreite
  else if (k == 39 ){//taste links
    if (strokewidth <= 1){
      strokewidth = abs(strokewidth) + 0.1f; 
    }
    else{ 
        strokewidth = strokewidth+1; }
  } 
  else if (k == 37 ){      //taste rechts
    if (strokewidth <= 1){
      strokewidth = max(abs(strokewidth) - 0.1f,0.1f); 
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
      if(abs(rotfak) < 1 ){ rotfak = rotfak + 0.1f; }
      else{rotfak = rotfak + 1;}   } //taste p
  else if (k==89)  { //y
     if(abs(rotfak) <= 1 ){ rotfak = rotfak - 0.1f; }
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
 else if (k == 67){ alphafak -= 0.1f;  }
 //86 = v makes the alphafak higher
 else if (k == 86){alphafak += 0.1f;  }
 // Enter key makes the lines colorfull
 else if (k == 10){colorbool = ! colorbool; }
 // 113 = q makes draw balls instead of lines
 else if (k==69){balls= ! balls;}
 println(keyCode);
  }
  
  
public void draw() {
  
    if (savebool){
      //saveFrame();
      // Note that #### will be replaced with the frame number. Fancy!
      //beginRecord(PDF, "frame-####.pdf"); 
    } 
  
    //if (mouseButton == LEFT){
    background(backy);
    strokeWeight(strokewidth); //strichbreite
    //}
   lauf+= 0.01f;
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
   beatmittel = 0.5f + beatmittel/buffer/beat*mittefak;
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
  public void mousePressed() {
    //noLoop();
  }
  public void mouseReleased() {
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

  /*%
  %                        1
  %               /                 \
  %            /                       \
  %         /                            \
  %       /                                \
  %     /                                     \
  %  0-----------------6         3----------------2
  %                    |         |
  %                    |         |
  %                    |         |
  %                    |         |
  %                    5---------4
  %*/
  public float[][][] makeArrow(float alpha,float b1,float b2,float l,float mitte,float alpha2, float[] mousept, float rotphi){
  float[][][] arrow = new float[8][2][1]; //letzter Vektor ist Richtungsvektor
  float al, c1, temp;
  float cosphi;
  float sinphi;
  float phi1;
  float A[][] = new float[2][2];
  float[][] A1 = {{0.f, 1.f} , {-1.f, 0.f} }; //{{0., 1.} , {-1., 0.} };
  float[] r1, r2, tt;
  float p0[] = new float[2];
  float p1[] = new float[2];
  float p2[] = new float[2];
  float p3[] = new float[2];
  float p4[] = new float[2];
  float p5[] = new float[2];
  float p6[] = new float[2];
   if (b1 ==  0){
       b1 = 1e-10f;       
     }
  cosphi = cos(rotphi);
  sinphi = sin(rotphi);
  v1 = b1/b2;
  v2 = b1/l;
  arrow[0][0][0] = mousept[0] - b1/2; //0
  arrow[0][1][0]= mousept[1];
  arrow[2][0][0] = mousept[0] + b1/2;
  arrow[2][1][0]= mousept[1];
  
  //%c1 = b1*mitte;
  //%c2 = b1*(1-mitte);
  if (mitte <= 0.5f){
    al = alpha*mitte;
    c1 = b1*mitte; }
  else{
    al = alpha*(1-mitte);
    c1 = b1*(1-mitte);
  }
  
  //must be renewed for rotation
  arrow[1][0][0] = mousept[0] + mitte*b1 -b1/2;
  arrow[1][1][0] = mousept[1] + c1/tan(al);
  
  
  temp = (mitte*b1 - b2/2)/tan(alpha2);
  arrow[3][0][0] = mousept[0]-b1/2 + mitte*b1 + b2/2;
  arrow[3][1][0] = mousept[1]   + temp ;
  arrow[6][0][0] = mousept[0]-b1/2 + mitte*b1 - b2/2;
  arrow[6][1][0] = mousept[1]   + temp ;
  
  
   for(int i =0;i<8;i++){
    arrow[i][0][0] =  cosphi*arrow[i][0][0] - sinphi*arrow[i][1][0];
    arrow[i][1][0] =  sinphi*arrow[i][0][0] + cosphi*arrow[i][1][0];
  }
  
  
  //for the rotation------------------------------
  for (int i =0;i<2;i++){
    p2[i]= arrow[2][i][0];
    p3[i]= arrow[3][i][0];
    p0[i]= arrow[0][i][0];
    p6[i]= arrow[6][i][0];
  }
   phi1 =  PI - alpha;
   A[0][0] = cos(phi1) ;
   A[0][1] = sin(phi1); //sin(phi1);**
   A[1][0] = -sin(phi1);//-sin(phi1);**
   A[1][1] = cos(phi1) ;
   r1 = Mat.sum(p2, Mat.multiply( p0,-1));
   r1 = Mat.divide(r1,b1); //r1/b1;
   b1 = sqrt( pow(r1[0],2) + pow(r1[1],2));
   r2 = Mat.multiply(A1,r1);
   p4 = Mat.sum(p3 , Mat.multiply( r2,  l ));
   p5 = Mat.sum(p6 , Mat.multiply(r2, l ));
   //p1 = mat.sum(mat.sum(p0,mat.multiply(r1,mat.multiply(b1,mitte))), mat.multiply(r2,))  ;
   
   
   
  
  for (int i=0;i<2;i++){ 
    arrow[4][i][0] = p4[i];
    arrow[5][i][0] = p5[i];
  }
   
   
  //--------------------------------------------------
  
  /*------------------------------------------
  arrow[4][0][0] = arrow[3][0][0]; //mitte*b1 + b2/2;
  arrow[4][1][0] = arrow[3][1][0] - l;
  arrow[5][0][0] = arrow[6][0][0];  //mitte*b1 - b2/2;
  arrow[5][1][0] = arrow[6][1][0] - l;
  ------------------------------------------*/
  
  arrow[7][0][0] = 0; //Richtungsvektor
  arrow[7][1][0] = 1; //Richtungsvektor


  return arrow;
}
//import papaya.*;
  /*%
  %                        1
  %               /                 \
  %            /                       \
  %         /                            \
  %       /                                \
  %     /                                     \
  %  0-----------------6         3----------------2
  %                    |         |
  %                    |         |
  %                    |         |
  %                    |         |
  %                    5---------4
  %*/
public float[][][] nextArrow(float arrow[][][],float phi,float mitte,float v1,float v2,float alpha2){
  int M = arrow.length; //Anzahl der Punkte=7 + 1 für den Richtungsvektor also 8
  int MM = arrow[0].length;
  int N = arrow[0][0].length*2;
  float anew[][][] = new float[M][MM][N];
  int nn = 0;
  int fak = 1;
  float at[][]= new float[M][MM];
  float phi1;
  float[][] A1 = {{0.f, 1.f} , {-1.f, 0.f} }; //{{0., 1.} , {-1., 0.} };
  float temp;
  float b1, b2, l;
  float[] r1, r2, tt;
  float p0[] = new float[2];
  float p1[] = new float[2];
  float p2[] = new float[2];
  float p3[] = new float[2];
  float p4[] = new float[2];
  float p5[] = new float[2];
  float p6[] = new float[2];
  float A[][] = new float[2][2];
  //----------------------------------------------------
  for (int n = 0 ; n < N/2; n++){ //Gesamtschleife
  //----------------------------------------------------
     for (int i=0;i<M;i++){
       for (int j=0;j<MM;j++){
         at[i][j] = arrow[i][j][n];   
         //println(M);  
       }
     }
     /*
     println("------------------------------------------");
     println("at Y");
     println(arrow[0][1][n]);
     println(at[0][1]);
     println("MouseY");
     println(mouseY);
     println("at X");
     println(at[0][0]);
     println("MouseX");
     println(mouseX);
     println("------------------------------------------");
     */
     //at = arrow(:,:,n);
     //%linke seite-------------------------------------------------------------
     for (int k = 0;k<2;k++){
       p0[k] = at[0][k];
       p1[k] = at[6][k];
     }
     phi1 =  PI - phi;
     A[0][0] = cos(phi1) ;
     A[0][1] = sin(phi1); //sin(phi1);**
     A[1][0] = -sin(phi1);//-sin(phi1);**
     A[1][1] = cos(phi1) ;
     p2 = Mat.sum(p1 , Mat.multiply(A, Mat.sum(p1 , Mat.multiply(p0,-1))));
     //p0 = Mat.multiply( p0,-1);
     r1 = Mat.sum(p2, Mat.multiply( p0,-1));
     b1 = sqrt( pow(r1[0],2) + pow(r1[1],2));
     //println(b1,  "  " , r1[0],"  " , r1[1]," ",p1[0]," ",p1[1]," ",p3[0]," ",p3[1]  );
     if (b1 ==  0){
       b1 = 1e-10f;       
     }
     r1 = Mat.divide(r1,b1); //r1/b1;
     b2 = b1/v1;
     r2 = Mat.multiply(A1,r1);
     tt = Mat.multiply(r1, mitte*b1 - b2/2);
     temp = sqrt(pow(tt[0],2) + pow(tt[1],2))/tan(alpha2 );//norm(r1.*(mitte*b1 - b2/2))/tan(alpha2));
     p3 = Mat.sum(p0 , Mat.multiply(r1,  mitte*b1 + b2/2));
     p3 = Mat.sum(p3, Mat.multiply(r2,-1*temp));
     p6 = Mat.sum(p0 , Mat.multiply(r1, mitte*b1 -  b2/2));
     //%temp =  norm(r1.*(mitte*b1 -  b2/2))/tan(alpha2);
     p6 = Mat.sum(p6 , Mat.multiply(r2, -1*temp));
     l = b1/v2;
     p4 = Mat.sum(p3 , Mat.multiply( r2,  l ));
     p5 = Mat.sum(p6 , Mat.multiply(r2, l ));
     fak = 1;
     for (int j=0;j<2;j++){
       //if (j == 1){fak = -1;}
       anew[0][j][nn]  = fak*p0[j];
       anew[1][j][nn]  = fak*p1[j];
       anew[2][j][nn]  = fak*p2[j];
       anew[3][j][nn]  = fak*p3[j];
       anew[4][j][nn]  = fak*p4[j];
       anew[5][j][nn]  = fak*p5[j];
       anew[6][j][nn]  = fak*p6[j];
       anew[7][j][nn]  = r2[j];
     }
     //%rechte seite:--------------------------------------------------------
     for (int k = 0;k<2;k++){
       p1[k] = at[3][k];
       p2[k] = at[2][k];
     }
     A[0][0] = cos(phi) ;
     A[0][1] =  sin(phi);
     A[1][0] = -sin(phi);
     A[1][1] = cos(phi) ; //phi1 ??
     p0 = Mat.sum(p1 , Mat.multiply(A, Mat.sum(p2 , Mat.multiply(p1,-1)))); // p2 + [A*[p3 - p2]']';
     r1 = Mat.sum(p2,Mat.multiply( p0,-1)); //p3 - p1;
     b1 = sqrt( pow(r1[0],2) + pow(r1[1],2)); //norm(r1);
     if (b1 ==  0){
       b1 = 1e-10f;       
     }
     r1 =  Mat.divide(r1,b1); //r1./b1;
     r2 = Mat.multiply(A1,r1);//[A1*r1']';
     b2 = b1/v1;
     p3 = Mat.sum(p0, Mat.multiply(r1, mitte*b1 + b2/2));   //p1 + r1.*(mitte*b1 + b2/2);
     p6 = Mat.sum(p0 , Mat.multiply(r1 , mitte*b1 -  b2/2));
     tt = Mat.multiply(r1,mitte*b1 - b2/2) ;
     temp = sqrt(pow(tt[0],2) + pow(tt[1],2))/tan(alpha2);  // norm(r1.*(mitte*b1 - b2/2))/tan(alpha2);
     p3 = Mat.sum(p3, Mat.multiply(r2, -1*temp));
     //%temp =  norm(r1.*(mitte*b1 -  b2/2))/tan(alpha2);
     p6 = Mat.sum(p6, Mat.multiply(r2, -1*temp));
     l = b1/v2;
     p4 = Mat.sum(p3 , Mat.multiply( r2, l));
     p5 = Mat.sum(p6 , Mat.multiply( r2, l));
     fak = 1;
     for (int j=0;j<2;j++){
       //if (j == 1){fak = -1;}
       anew[0][j][nn+1]  = fak*p0[j];
       anew[1][j][nn+1]  = fak*p1[j];
       anew[2][j][nn+1]  = fak*p2[j];
       anew[3][j][nn+1]  = fak*p3[j];
       anew[4][j][nn+1]  = fak*p4[j];
       anew[5][j][nn+1]  = fak*p5[j];
       anew[6][j][nn+1]  = fak*p6[j];
       anew[7][j][nn+1]    = r2[j];
     }
     nn = nn +2;
  }
  return anew;
}
//import papaya.*;
  /*%
  %                        1
  %               /                 \
  %            /                       \
  %         /                            \
  %       /                                \
  %     /                                     \
  %  0-----------------6         3----------------2
  %                    |         |
  %                    |         |
  %                    |         |
  %                    |         |
  %                    5---------4
  %*/
public void printarrows(float newarrows[][][],float alpha,float mitte,float v1,float v2,float alpha1, int ebenen, boolean colorbool){
  float R = 0;
  float G = 255;
  float B = 255;
  
  //nal = newarrows.length;
  N  = 2^(ebenen+1) -1; 
  nal = newarrows.length-1; //=7

  //make colorfull lines
  if (colorbool){
     //der erste Pfeil----------------------------------------------------
    for (int i = 0; i< newarrows[0][0].length;i++){
       for (int j = 0; j< nal;j++){
         jj = j+1;
         if (j == nal-1){  jj = 0;  }
         line( width/2 + newarrows[j][0][i], height/2 -newarrows[j][1][i] , width/2 + newarrows[jj][0][i],height/2 - newarrows[jj][1][i] );               
       }
      }
    //------------------------------------------------------------------
    //alle unterpfeile--------------------------------------------------
    for (int n = 0; n<ebenen;n++){
      newarrows = nextArrow(newarrows,alpha,mitte,v1,v2,alpha1);
      //newarrows = newarrows1;
      //nal = newarrows.length -1 ;
      //println(nal);
      //println("was soll das?");
      R = n*255/ebenen*v1;
      G = 255-v2*20; //255-n*255/ebenen; //100;
      B = 255- n*255/ebenen;
      stroke(R,G,B);
      numarrows = newarrows[0][0].length;
      for (int i = 0; i< numarrows;i++){
       for (int j = 0; j< nal;j++){
         //den Abstand 
         if (2*j < nal){ 
           newarrows[2*j][0][i] =  newarrows[2*j][0][i]  + distance*newarrows[7][0][i]/(ebenen-n);
           newarrows[2*j][1][i] =  newarrows[2*j][1][i]  + distance*newarrows[7][1][i]/(ebenen-n);
           if(2*j+1 < nal){
             newarrows[2*j+1][0][i] =  newarrows[2*j+1][0][i]  + distance*newarrows[7][0][i]/(ebenen-n);
             newarrows[2*j+1][1][i] =  newarrows[2*j+1][1][i]  + distance*newarrows[7][1][i]/(ebenen-n);
           }
         }
         jj = j+1;
         if (j == nal-1){ jj = 0; }
         line(width/2 + newarrows[j][0][i], height/2 -  newarrows[j][1][i] , width/2  + newarrows[jj][0][i], height/2   -  newarrows[jj][1][i] );
    
       }
      }
    }
  }
  //make only white lines
  else{
    stroke(255);
     //der erste Pfeil----------------------------------------------------
    for (int i = 0; i< newarrows[0][0].length;i++){
       for (int j = 0; j< nal;j++){
         jj = j+1;
         if (j == nal-1){  jj = 0;  }
         line( width/2 + newarrows[j][0][i], height/2 -newarrows[j][1][i] , width/2 + newarrows[jj][0][i],height/2 - newarrows[jj][1][i] );               
       }
      }
    //------------------------------------------------------------------
    //alle unterpfeile--------------------------------------------------
    for (int n = 0; n<ebenen;n++){
      newarrows = nextArrow(newarrows,alpha,mitte,v1,v2,alpha1);
      //newarrows = newarrows1;
      //nal = newarrows.length -1 ;
      //println(nal);
      //println("was soll das?");
      numarrows = newarrows[0][0].length;
      for (int i = 0; i< numarrows;i++){
       for (int j = 0; j< nal;j++){
         //den Abstand 
         if (2*j < nal){ 
           newarrows[2*j][0][i] =  newarrows[2*j][0][i]  + distance*newarrows[7][0][i]/(ebenen-n);
           newarrows[2*j][1][i] =  newarrows[2*j][1][i]  + distance*newarrows[7][1][i]/(ebenen-n);
           if(2*j+1 < nal){
             newarrows[2*j+1][0][i] =  newarrows[2*j+1][0][i]  + distance*newarrows[7][0][i]/(ebenen-n);
             newarrows[2*j+1][1][i] =  newarrows[2*j+1][1][i]  + distance*newarrows[7][1][i]/(ebenen-n);
           }
         }
         jj = j+1;
         if (j == nal-1){ jj = 0; }
         line(width/2 + newarrows[j][0][i], height/2 -  newarrows[j][1][i] , width/2  + newarrows[jj][0][i], height/2   -  newarrows[jj][1][i] );
    
       }
      }
    }
  }
}
//import papaya.*;
  /*%
  %                        1
  %               /                 \
  %            /                       \
  %         /                            \
  %       /                                \
  %     /                                     \
  %  0-----------------6         3----------------2
  %                    |         |
  %                    |         |
  %                    |         |
  %                    |         |
  %                    5---------4
  %*/
public void printballs(){
  float R = 0;
  float G = 255;
  float B = 255;
  
  //nal = newarrows.length;
  N  = 2^(ebenen+1) -1; 
  nal = newarrows.length-1; //=7

  //make colorfull lines
  if (colorbool){
     //der erste Pfeil----------------------------------------------------
    for (int i = 0; i< newarrows[0][0].length;i++){
       for (int j = 0; j< nal;j++){
         jj = j+1;
         if (j == nal-1){  jj = 0;  }
         ellipse( width/2 + newarrows[j][0][i], height/2 -newarrows[j][1][i] , v2,v1 );               
       }
      }
    //------------------------------------------------------------------
    //alle unterpfeile--------------------------------------------------
    for (int n = 0; n<ebenen;n++){
      newarrows = nextArrow(newarrows,alpha,mitte,v1,v2,alpha1);
      //newarrows = newarrows1;
      //nal = newarrows.length -1 ;
      //println(nal);
      //println("was soll das?");
      R = n*255/ebenen*v1;
      G = 255-v2*20; //255-n*255/ebenen; //100;
      B = 255- n*255/ebenen;
      stroke(R,G,B);
      numarrows = newarrows[0][0].length;
      for (int i = 0; i< numarrows;i++){
       for (int j = 0; j< nal;j++){
         //den Abstand 
         if (2*j < nal){ 
           newarrows[2*j][0][i] =  newarrows[2*j][0][i]  + distance*newarrows[7][0][i]/(ebenen-n);
           newarrows[2*j][1][i] =  newarrows[2*j][1][i]  + distance*newarrows[7][1][i]/(ebenen-n);
           if(2*j+1 < nal){
             newarrows[2*j+1][0][i] =  newarrows[2*j+1][0][i]  + distance*newarrows[7][0][i]/(ebenen-n);
             newarrows[2*j+1][1][i] =  newarrows[2*j+1][1][i]  + distance*newarrows[7][1][i]/(ebenen-n);
           }
         }
         jj = j+1;
         if (j == nal-1){ jj = 0; }
         ellipse(width/2 + newarrows[j][0][i], height/2 -  newarrows[j][1][i], v2,v1);
    
       }
      }
    }
  }
  //make only white lines
  else{
    stroke(255);
     //der erste Pfeil----------------------------------------------------
    for (int i = 0; i< newarrows[0][0].length;i++){
       for (int j = 0; j< nal;j++){
         jj = j+1;
         if (j == nal-1){  jj = 0;  }
         ellipse( width/2 + newarrows[j][0][i], height/2 -newarrows[j][1][i] , v1,v2 );                
       }
      }
    //------------------------------------------------------------------
    //alle unterpfeile--------------------------------------------------
    for (int n = 0; n<ebenen;n++){
      newarrows = nextArrow(newarrows,alpha,mitte,v1,v2,alpha1);
      //newarrows = newarrows1;
      //nal = newarrows.length -1 ;
      //println(nal);
      //println("was soll das?");
      numarrows = newarrows[0][0].length;
      for (int i = 0; i< numarrows;i++){
       for (int j = 0; j< nal;j++){
         //den Abstand 
         if (2*j < nal){ 
           newarrows[2*j][0][i] =  newarrows[2*j][0][i]  + distance*newarrows[7][0][i]/(ebenen-n);
           newarrows[2*j][1][i] =  newarrows[2*j][1][i]  + distance*newarrows[7][1][i]/(ebenen-n);
           if(2*j+1 < nal){
             newarrows[2*j+1][0][i] =  newarrows[2*j+1][0][i]  + distance*newarrows[7][0][i]/(ebenen-n);
             newarrows[2*j+1][1][i] =  newarrows[2*j+1][1][i]  + distance*newarrows[7][1][i]/(ebenen-n);
           }
         }
         jj = j+1;
         if (j == nal-1){ jj = 0; }
         ellipse(width/2 + newarrows[j][0][i], height/2 -  newarrows[j][1][i],v1,v2);
    
       }
      }
    }
  }
}
//import papaya.*;
  /*%
  %                        1
  %               /                 \
  %            /                       \
  %         /                            \
  %       /                                \
  %     /                                     \
  %  0-----------------6         3----------------2
  %                    |         |
  %                    |         |
  %                    |         |
  %                    |         |
  %                    5---------4
  %*/
public void printsplines(){
  float R = 0;
  float G = 255;
  float B = 255;
  
  //nal = newarrows.length;
  N  = 2^(ebenen+1) -1; 
  nal = newarrows.length; //=7

  //make colorfull lines
  if (colorbool){
     //der erste Pfeil----------------------------------------------------
    beginShape();
    for (int i = 0; i< newarrows[0][0].length;i++){
       for (int j = 0; j< nal;j++){         
         curveVertex( width/2 + newarrows[j][0][i], height/2 -newarrows[j][1][i] );               
       }
      }
    endShape();
    //------------------------------------------------------------------
    //alle unterpfeile--------------------------------------------------
    for (int n = 0; n<ebenen;n++){
      newarrows = nextArrow(newarrows,alpha,mitte,v1,v2,alpha1);
      //newarrows = newarrows1;
      //nal = newarrows.length -1 ;
      //println(nal);
      //println("was soll das?");
      R = n*255/ebenen*v1;
      G = 255-v2*20; //255-n*255/ebenen; //100;
      B = 255- n*255/ebenen;
      stroke(R,G,B);
      numarrows = newarrows[0][0].length;
      for (int i = 0; i< numarrows;i++){
       beginShape();
       for (int j = 0; j< nal;j++){
         //den Abstand 
         if (2*j < nal){ 
           newarrows[2*j][0][i] =  newarrows[2*j][0][i]  + distance*newarrows[7][0][i]/(ebenen-n);
           newarrows[2*j][1][i] =  newarrows[2*j][1][i]  + distance*newarrows[7][1][i]/(ebenen-n);
           if(2*j+1 < nal){
             newarrows[2*j+1][0][i] =  newarrows[2*j+1][0][i]  + distance*newarrows[7][0][i]/(ebenen-n);
             newarrows[2*j+1][1][i] =  newarrows[2*j+1][1][i]  + distance*newarrows[7][1][i]/(ebenen-n);
           }
         }
         curveVertex(width/2 + newarrows[j][0][i], height/2 -  newarrows[j][1][i]);
    
       }
       endShape();   
      }
    }
    
  }
  //make only white lines
  else{
    stroke(255);
     //der erste Pfeil----------------------------------------------------
    for (int i = 0; i< newarrows[0][0].length;i++){
       for (int j = 0; j< nal;j++){
         jj = j+1;
         if (j == nal-1){  jj = 0;  }
         ellipse( width/2 + newarrows[j][0][i], height/2 -newarrows[j][1][i] , v1,v2 );                
       }
      }
    //------------------------------------------------------------------
    //alle unterpfeile--------------------------------------------------
    for (int n = 0; n<ebenen;n++){
      newarrows = nextArrow(newarrows,alpha,mitte,v1,v2,alpha1);
      //newarrows = newarrows1;
      //nal = newarrows.length -1 ;
      //println(nal);
      //println("was soll das?");
      numarrows = newarrows[0][0].length;
      for (int i = 0; i< numarrows;i++){
       for (int j = 0; j< nal;j++){
         //den Abstand 
         if (2*j < nal){ 
           newarrows[2*j][0][i] =  newarrows[2*j][0][i]  + distance*newarrows[7][0][i]/(ebenen-n);
           newarrows[2*j][1][i] =  newarrows[2*j][1][i]  + distance*newarrows[7][1][i]/(ebenen-n);
           if(2*j+1 < nal){
             newarrows[2*j+1][0][i] =  newarrows[2*j+1][0][i]  + distance*newarrows[7][0][i]/(ebenen-n);
             newarrows[2*j+1][1][i] =  newarrows[2*j+1][1][i]  + distance*newarrows[7][1][i]/(ebenen-n);
           }
         }
         jj = j+1;
         if (j == nal-1){ jj = 0; }
         ellipse(width/2 + newarrows[j][0][i], height/2 -  newarrows[j][1][i],v1,v2);
    
       }
      }
    }
  }
}
  public void settings() {  fullScreen(FX2D, 2); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "TryingArrows_Grabow2018" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
