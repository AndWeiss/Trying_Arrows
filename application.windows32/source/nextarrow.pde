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
float[][][] nextArrow(float arrow[][][],float phi,float mitte,float v1,float v2,float alpha2){
  int M = arrow.length; //Anzahl der Punkte=7 + 1 für den Richtungsvektor also 8
  int MM = arrow[0].length;
  int N = arrow[0][0].length*2;
  float anew[][][] = new float[M][MM][N];
  int nn = 0;
  int fak = 1;
  float at[][]= new float[M][MM];
  float phi1;
  float[][] A1 = {{0., 1.} , {-1., 0.} }; //{{0., 1.} , {-1., 0.} };
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
       b1 = 1e-10;       
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
       b1 = 1e-10;       
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
