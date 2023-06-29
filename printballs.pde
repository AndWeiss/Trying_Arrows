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
void printballs(float newarrows[][][],float alpha,float mitte,float v1,float v2,float alpha1, int ebenen, boolean colorbool){
  float R = 0;
  float G = 255;
  float B = 255;
  
  //nal = newarrows.length;
  N  = 2^(ebenen+1) -1; 
  nal = newarrows.length-1; //=7
    //stroke(255);
     //der erste Pfeil----------------------------------------------------
    for (int i = 0; i< newarrows[0][0].length;i++){
       for (int j = 0; j< nal;j++){
         jj = j+1;
         if (j == nal-1){  jj = 0;  }
         ellipse( newarrows[j][0][i], -newarrows[j][1][i] ,3+ v1*(2.5-mitte),3+v1*(1.5+mitte));                
       }
      }
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
      //newarrows = newarrows1;
      //nal = newarrows.length -1 ;
      //println(nal);
      //println("was soll das?");
      numarrows = newarrows[0][0].length;
      for (int i = 0; i< numarrows;i++){
       for (int j = 0; j< nal;j++){
         // add the distance
           newarrows[j][0][i] =  newarrows[j][0][i]  + distance*newarrows[7][0][i]/(ebenen-n);
           newarrows[j][1][i] =  newarrows[j][1][i]  + distance*newarrows[7][1][i]/(ebenen-n);
           ellipse(newarrows[j][0][i], -newarrows[j][1][i], 3+v1*(2.5-mitte),3+v1*(1.5+mitte));
    
       }
      }
    }
}
