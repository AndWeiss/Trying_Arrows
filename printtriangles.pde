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
void printtriangles(float newarrows[][][],float alpha,float mitte,float v1,float v2,float alpha1, int ebenen, boolean colorbool){
  float R = 0;
  float G = 255;
  float B = 255;
  
  //nal = newarrows.length;
  N  = 2^(ebenen+1) -1; 
  nal = newarrows.length-1; //=7


     //der erste Pfeil----------------------------------------------------
    for (int i = 0; i< newarrows[0][0].length;i++){
       for (int j = 0; j< nal;j++){
         jj = j+1;
         if (j == nal-1){  jj = 0;  }
         triangle( newarrows[j][0][i], -newarrows[j][1][i] ,  v2, -v1, v1, -v2);
       }
      }
    //------------------------------------------------------------------
    //alle unterpfeile--------------------------------------------------
    for (int n = 0; n<ebenen;n++){
      newarrows = nextArrow(newarrows,alpha,mitte,v1,v2,alpha1);
      //make colorfull lines
      if (colorbool){
        R = n*255/ebenen*v1;
        G = 255-v2*20; //255-n*255/ebenen; //100;
        B = 255- n*255/ebenen;
        stroke(R,G,B);
      }
      numarrows = newarrows[0][0].length;
      for (int i = 0; i< numarrows;i++){
       //for (int j = 0; j< nal;j++){
         //den Abstand 
         newarrows[1][0][i] =  newarrows[1][0][i]  + distance*newarrows[0][0][i]/(ebenen-n);
         newarrows[1][1][i] =  newarrows[1][1][i]  + distance*newarrows[0][1][i]/(ebenen-n);
         //
         newarrows[4][0][i] =  newarrows[4][0][i]  + distance*newarrows[0][0][i]/(ebenen-n);
         newarrows[4][1][i] =  newarrows[4][1][i]  + distance*newarrows[0][1][i]/(ebenen-n);
         //
         newarrows[5][0][i] =  newarrows[5][0][i]  + distance*newarrows[0][0][i]/(ebenen-n);
         newarrows[5][1][i] =  newarrows[5][1][i]  + distance*newarrows[0][1][i]/(ebenen-n);
         //
         triangle(newarrows[1][0][i], -newarrows[1][1][i], newarrows[2][0][i], -newarrows[2][1][i], newarrows[5][0][i],  -newarrows[5][1][i]);
    
       //}
      }
    }
  }
