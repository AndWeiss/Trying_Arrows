float[] getdist(float a, float b){
  float[] results = new float[2];
      results[0] = a*2;
      results[1] = b*2;
  if (a % b < abs(a % b - b)){
    results[0] = (a % b);
    results[1] =  1;
  }
  else{
  results[0] =  abs((a % b) - b); 
  results[1] = -1 ;
  }
  return results;
}
