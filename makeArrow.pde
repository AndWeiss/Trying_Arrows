
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
  float[][][] makeArrow(float alpha,float b1,float b2,float l,float mitte,float alpha2, float[] mousept, float rotphi){
  float[][][] arrow = new float[8][2][1]; //letzter Vektor ist Richtungsvektor
  float al, c1, temp;
  float cosphi;
  float sinphi;
  float phi1;
  float A[][] = new float[2][2];
  float[][] A1 = {{0., 1.} , {-1., 0.} }; //{{0., 1.} , {-1., 0.} };
  float[] r1, r2, tt;
  float p0[] = new float[2];
  float p1[] = new float[2];
  float p2[] = new float[2];
  float p3[] = new float[2];
  float p4[] = new float[2];
  float p5[] = new float[2];
  float p6[] = new float[2];
   if (b1 ==  0){
       b1 = 1e-10;       
     }
  cosphi = cos(rotphi);
  sinphi = sin(rotphi);
  // v1 = b1/b2;
  // v2 = b1/l;
  arrow[0][0][0] = mousept[0] - b1/2; //0
  arrow[0][1][0]= mousept[1];
  arrow[2][0][0] = mousept[0] + b1/2;
  arrow[2][1][0]= mousept[1];
  
  //%c1 = b1*mitte;
  //%c2 = b1*(1-mitte);
  if (mitte <= 0.5){
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
