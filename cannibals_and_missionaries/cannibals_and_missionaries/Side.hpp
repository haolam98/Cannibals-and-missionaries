//
//  Side.hpp
//  cannibals_and_missionaries
//
//  Created by Hao Lam on 3/4/21.
//

#ifndef Side_hpp
#define Side_hpp

#include <stdio.h>
using namespace std;
class Side {
    int numberOfCannibals;
    int numberOfMissionaries;
    int boatSide;  /* 0 - boat on left side
                      1 - boat on rigt side
                    */
public:
    Side();
    Side(int,int,int );
    int getCannibals();
    int getMissionaries();
    int getBoatSide();
    
};
#endif /* Side_hpp */
**