#include <stdio.h>
#include <stdlib.h>
int main()
{
    float r2 = 0;   
    float LO = 10;
    float HI = 20;
    float total = 0;
    float arr [100];
    int renglon = 1;
    for(int i =0; i<100; i++){        
        r2 =  LO + static_cast <float> (rand()) /( static_cast <float> (RAND_MAX/(HI-LO)));
        arr[i] = r2;
        total+=r2;
        printf("%2.3f\t",arr[i]);
      
        if(renglon%10==0) printf("\n");
        renglon++;           
    }
    
    printf("%2.3f\n",total/100);
    
    //var_x = E(X - mu)^2
    
 
    
    
}
