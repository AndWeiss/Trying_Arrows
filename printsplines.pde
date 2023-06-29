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
  nal = newarrows.length; // = 8 number of vertexes =7, why??
  float [] dist_pi = getdist(alpha,PI);
  if (dist_pi[0] < 0.4){
        // println(alpha);
        alpha += dist_pi[1]*0.2;
        alpha1 = alpha;
        // println("----------------------------------------------------");
        // println(alpha);
   }

    //der erste Pfeil----------------------------------------------------
    beginShape();
    for (int i = 0; i< newarrows[0][0].length;i++){
       for (int j = 0; j< nal;j++){         
         curveVertex( newarrows[j][0][i],  -newarrows[j][1][i] );       
       }
    }
    //endShape(CLOSE);
    //------------------------------------------------------------------
    //alle unterpfeile--------------------------------------------------
    for (int n = 0; n<ebenen;n++){
        //make colorfull lines
      if (colorbool){
          R = n*255/ebenen*v1;
          G = 255-v2*20; //255-n*255/ebenen; //100;
          B = 255- n*255/ebenen;
          stroke(R,G,B);
        }
      newarrows = nextArrow(newarrows,alpha,mitte,v1,v2,alpha1);
      numarrows = newarrows[0][0].length;
      for (int i = 0; i< numarrows;i++){
       //beginShape();
       for (int j = 0; j<nal-5;j++){
         //add the distance 
         newarrows[j][0][i] =  newarrows[j][0][i]    + distance*newarrows[7][0][i]/(ebenen-n);
         newarrows[j][1][i] =  newarrows[j][1][i]    + distance*newarrows[7][1][i]/(ebenen-n);
         curveVertex(newarrows[j][0][i],  -newarrows[j][1][i]);
    
       }
       //endShape(CLOSE);   
      }
    }
    endShape(CLOSE);
}
