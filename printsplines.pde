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
void printsplines(float newarrows[][][],float alpha,float mitte,float v1,float v2,float alpha1, int ebenen, boolean colorbool){
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
  //make only white/black lines
  else{
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
}
